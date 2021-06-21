package com.yyy.springboot.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yyy.springboot.dto.ProductSpecificationIdDTO;
import com.yyy.springboot.vo.OrderVO;
import com.yyy.springboot.entitys.*;
import com.yyy.springboot.exception.MyException;
import com.yyy.springboot.exception.MySQLException;
import com.yyy.springboot.mapper.OrderMasterMapper;
import com.yyy.springboot.mapper.ProductSpecificationDetailMapper;
import com.yyy.springboot.service.OrderDetailService;
import com.yyy.springboot.service.OrderMasterService;
import com.yyy.springboot.service.ProductRepertoryService;
import com.yyy.springboot.service.UserAddressService;
import com.yyy.springboot.util.OrderStatusEnum;
import com.yyy.springboot.util.RedisUtil;
import com.yyy.springboot.util.ResultUtil;
import com.yyy.springboot.util.ShareThreadLocal;
import com.yyy.springboot.vo.SettlementProductVO;
import com.yyy.springboot.vo.SettlementVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.BooleanUtils;
import org.apache.ibatis.annotations.Param;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ShareThreadLocal<Long> shareThreadLocal;
    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private ProductSpecificationDetailMapper productSpecificationDetailMapper;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductRepertoryService repertoryService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private ProductRepertoryService productRepertoryService;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${redisson_prefix}")
    private String redissonPrefix = "redisson_";

    @Value("${order_prefix}")
    private String orderPrefix = "order_";

    @Value("${order_expire_time}")
    private Integer expireTime = 30;

    @Override
    public OrderMaster selectOrderMasterById(Long id) {
        return orderMasterMapper.selectById(id);
    }

    @Override
    public void updateProductTotalAmountById(BigDecimal productTotalAmount,Long orderId) {
        orderMasterMapper.update(null,
                new LambdaUpdateWrapper<OrderMaster>()
                        .eq(OrderMaster::getId, orderId)
                        .set(OrderMaster::getProductTotalAmount, productTotalAmount));
    }

    @Override
    @Transactional
    /**
     *@Description 下订单
     *@Date 23:15 2021/6/7
     *@Param [productSpecificationIdDTO] 商品id和规格id
     *@Return void
     **/
    public void insertOrderMaster(List<ProductSpecificationIdDTO> productSpecificationIdDTOS, Long userAddressId) {
        //检测重复点击
        doubleOrder(productSpecificationIdDTOS);

        List<String> joinIds = new ArrayList<>();
        productSpecificationIdDTOS.forEach(productSpecificationIdDTO -> {
            if (productSpecificationIdDTO.getProductNum() <= 0) {
                throw new MyException(ResultUtil.productNumIllegal()); //商品数量不合法
            }
            joinIds.add(getJoinId(productSpecificationIdDTO.getSpecificationId(), productSpecificationIdDTO.getProductId()));//拼接后key 例子:productId_41,42
        });

        RedissonMultiLock multiLock = null;

        List<RepertoryDifference> repertoryDifferences = new ArrayList<>();

        try {
            // redis分布式锁
            RLock[] locks = new RLock[joinIds.size()];
            for (int i = 0; i < joinIds.size(); i++) {
                locks[i]=redissonClient.getLock(redissonPrefix + joinIds.get(i));
            }
            // 联锁
            multiLock = new RedissonMultiLock(locks);
            // waitTime获取锁最长等待时间30s
            if (!multiLock.tryLock(30, TimeUnit.SECONDS)){
                throw new MyException(ResultUtil.placeOrderFail());
            }
            // 查询商品是否有库存 有则进行扣减
            for (ProductSpecificationIdDTO specificationIdDTO : productSpecificationIdDTOS) {
                Integer productNum = specificationIdDTO.getProductNum();
                ProductRepertory productRepertory = repertoryService.selectProductRepertoryByPsdIds(specificationIdDTO.getSpecificationId());
                if (ObjectUtils.isEmpty(productRepertory)) {
                    throw new MySQLException(ResultUtil.productNonexistent());//商品不存在
                }
                if (productRepertory.getNum() <= 0 || productRepertory.getNum() < productNum) {
                    redisUtil.del(genderOrderHashKey(productSpecificationIdDTOS));//删除重复下单检测
                    throw new MySQLException(ResultUtil.productUnderStock());//库存不足
                }
                Long productRepertoryId = productRepertory.getId();

                // 保存库存表id与库存
                repertoryDifferences.add(new RepertoryDifference(productRepertoryId, productNum));

                // 扣减商品库存
                repertoryService.subtractProductRepertoryNumById(productRepertoryId, productNum);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 解锁
            try {
//                assert multiLock != null;
//                if(multiLock.isLocked()){//判断是否锁住
//                    if(multiLock.isHeldByCurrentThread()){//是否是当前执行线程锁
                        multiLock.unlock();
//                    }
//                }
            } catch (IllegalMonitorStateException e) {
                log.info("锁已被释放");
                log.info("", e);
            }
        }

        // 插入数据库主订单信息
        BigDecimal productTotalAmount = new BigDecimal("0"); // BigDecimal 尽量使用String
        OrderMaster orderMaster = new OrderMaster()
                .setStatus(OrderStatusEnum.TO_BE_PAID.getStatus()) // 订单状态 0:待付款 1：待收货 2：已完成 3：已取消 5：已退款
                .setProductTotal(productSpecificationIdDTOS.size()) // 商品总件数
                .setProductTotalAmount(productTotalAmount) // 商品总价格
                .setUserAddressId(userAddressId) // 订单地址表Id
                .setUserId(shareThreadLocal.get()); // 用户Id
        orderMasterMapper.insert(orderMaster);

        // 插入数据库订单详细信息
        for (ProductSpecificationIdDTO productSpecificationIdDTO : productSpecificationIdDTOS) {

            // 得到订单详情信息
            OrderDetail orderDetail = orderMasterMapper.selectOrderDetailByPsdIds(productSpecificationIdDTO.getProductId(), productSpecificationIdDTO.getSpecificationId(), productSpecificationIdDTO.getSpecificationId().size());

            // 获取商品规格并设置到订单详情中
            orderDetail.setProductSpecification(productSpecificationDetailMapper.selectProductSpecificationDetailByPsdId(productSpecificationIdDTO.getSpecificationId())
                    .stream()
                    .map(ProductSpecificationDetail::getName)
                    .collect(Collectors.joining(";")));

            // 设置订单详情Id和商品数量
            orderDetail.setOrderId(orderMaster.getId())
                    .setProductNum(productSpecificationIdDTO.getProductNum());

            // 插入数据库
            orderDetailService.insertOrderDetail(orderDetail);

            // 汇总订单总价格
            productTotalAmount = productTotalAmount.add(orderDetail.getProductAmount());
        }

        // 更新主订单总价格
        updateProductTotalAmountById(productTotalAmount,orderMaster.getId());

        /**
         * 订单超时库存回滚  订单作为key，当超时时调用监听器 {@link com.yyy.springboot.listener.KeyExpiredListener}
         */
        redisUtil.set(orderPrefix + orderMaster.getId(), "", expireTime);
        redisUtil.set(orderPrefix + orderMaster.getId() + "value", repertoryDifferences);

        // 订单超时库存回滚 调用定时器
        oderTaskScheduler(orderMaster.getId());

        // 调用支付方法
        paid(orderMaster.getId(), genderOrderHashKey(productSpecificationIdDTOS));

    }

    /*
     * @Description 判断是否重复下单
     * @Date 2:35 2021/6/10
     * @param
     * @return void
     */
    public void doubleOrder(List<ProductSpecificationIdDTO> productSpecificationIdDTOS) {
        String joinKey = genderOrderHashKey(productSpecificationIdDTOS);

        //setIfAbsent:仅当 key 不存在，将 key 的值设为 value 并返回1,若存在，不做动作，返回0
        if (!BooleanUtils.isTrue(redisTemplate.opsForValue().setIfAbsent(joinKey, "", expireTime, TimeUnit.SECONDS))) { // 支付完成或订单超时或商品库存不足删除key
            //存在
            throw new MyException(ResultUtil.doubleOrder()); // 重复下单异常
        }
    }

    /**
     * @param orderId 订单id
     * @Description 开启定时器，到达指定时间进行库存回滚
     * @Date 7:46 2021/6/10
     */
    public void oderTaskScheduler(Long orderId) {
        //开启定时任务
        threadPoolTaskScheduler.schedule(() -> {
            //查询数据库订单支付状态是否为待支付
            Byte status = selectOrderStatusByOrderId(orderId);
            Object key = redisUtil.get("orderPrefix" + orderId);
            if (OrderStatusEnum.TO_BE_PAID.getStatus().equals(status) && key != null) {//待支付并且是存在key，说明key消失监听器还没触发
                //删除key
                redisUtil.del("orderPrefix" + orderId);

                @SuppressWarnings("unchecked")
                List<RepertoryDifference> repertoryDifferences = (ArrayList<RepertoryDifference>) redisUtil.getDel(key + "value");

                // 回滚库存
                repertoryDifferences.forEach(x -> {
                    productRepertoryService.addProductRepertoryNumById(x.getProductRepertoryId(), x.getNum());
                });
                log.info("定时器执行->当前过期的订单ID:" + key);
            }
        }, Date.from(LocalDateTime.now().plusSeconds(expireTime).atZone(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * @param productSpecificationIdDTOS 商品id、规格id、商品数量
     * @Description 生成redis的拼接字符串hashKey 例子：userId_productId_productSpecification;productSpecification_productNum
     * @Date 3:13 2021/6/10
     */
    public String genderOrderHashKey(List<ProductSpecificationIdDTO> productSpecificationIdDTOS) {
        StringBuilder joinKey = new StringBuilder(String.valueOf(shareThreadLocal.get()));
        productSpecificationIdDTOS.forEach(x -> {
            String productSpecification = x.getSpecificationId().stream().map(String::valueOf).collect(Collectors.joining(";"));
            joinKey.append("_").append(x.getProductId()).append("_").append(productSpecification).append("_").append(x.getProductNum());
        });
        return joinKey.toString();
    }

    /**
     * @param orderId 订单id
     * @Description 支付
     * @Date 1:51 2021/6/10
     */
    public void paid(Long orderId, String doubleKey) {
        //判断订单是否过期超时
        Boolean tag = redisUtil.setPersistKey(orderPrefix + orderId);// 设置永久不过期，用于防止支付时突然过期调用了库存回滚
        if (!tag) {
            //不存在该key，证明超时失效了
            throw new MyException(ResultUtil.orderExpired());
        }

        // TODO　调用支付接口

        //更新订单为已支付
        updateOrderStatusByOrderId(orderId, OrderStatusEnum.HAVE_PAID.getStatus());

        //支付成功，删除库存回滚键值对和重复下单键值对
        redisUtil.dels(Arrays.asList(orderPrefix + orderId, orderPrefix + orderId + "value", doubleKey));
    }

    @Transactional
    @Override
    public void orderPay(Long orderId, List<ProductSpecificationIdDTO> productSpecificationIdDTOS) {
        paid(orderId, genderOrderHashKey(productSpecificationIdDTOS));
    }

    @Override
    public void updateOrderStatusByOrderId(Long orderId, Byte status) {
        orderMasterMapper.update(null, new UpdateWrapper<OrderMaster>().eq("id", orderId).set("status", status));
    }

    @Override
    public Byte selectOrderStatusByOrderId(Long orderId) {
        return orderMasterMapper.selectOrderStatusByOrderId(orderId);
    }

    /**
     * @Description 查询订单通过userId和订单status
     * @Date 2:39 2021/6/11
     * @param status 订单状态
     * @return java.util.List<com.yyy.springboot.vo.OrderVO>
     */

    public List<OrderVO> selectOrderVOByUserIdAndStatus(Byte status){
        return orderMasterMapper.selectOrderVOByUserIdAndStatus(shareThreadLocal.get(), status);
    }

    /**
     * @Description 查询订单通过userId
     * @Date 2:40 2021/6/11
     * @return java.util.List<com.yyy.springboot.vo.OrderVO>
     */
    public List<OrderVO> selectOrderVOByUserId(){
        return orderMasterMapper.selectOrderVOByUserId(shareThreadLocal.get());
    }

    /**
     * @Description 获取结算界面数据
     * @Date 1:15 2021/6/11
     * @return com.yyy.springboot.vo.SettlementVO
     */
    @Override
    @SentinelResource("haha")
    public SettlementVO selectSettlement(List<ProductSpecificationIdDTO> productSpecificationIdDTOS){
        List<SettlementProductVO> settlementProductVOS = new ArrayList<>();
        BigDecimal productTotalAmount = new BigDecimal(0); //商品总价格
        BigDecimal carriage = new BigDecimal(0); //运费
        BigDecimal discount = new BigDecimal(0); //优惠
        for (ProductSpecificationIdDTO productSpecificationIdDTO : productSpecificationIdDTOS) {
            //获取结算商品的信息
            OrderDetail orderDetail = orderMasterMapper.selectOrderDetailByPsdIds(productSpecificationIdDTO.getProductId(), productSpecificationIdDTO.getSpecificationId(), productSpecificationIdDTO.getSpecificationId().size());
            SettlementProductVO settlementProductVO = new SettlementProductVO()
                    .setProductId(orderDetail.getProductId())
                    .setProductAmount(orderDetail.getProductAmount())
                    .setProductName(orderDetail.getProductName())
                    .setProductImage(orderDetail.getProductImage())
                    .setProductSpecification(productSpecificationDetailMapper.selectProductSpecificationDetailByPsdId(productSpecificationIdDTO.getSpecificationId())
                            .stream()
                            .map(ProductSpecificationDetail::getName)
                            .collect(Collectors.joining(";")))
                    .setProductNum(productSpecificationIdDTO.getProductNum());
            //添加结算商品的信息
            settlementProductVOS.add(settlementProductVO);
            //计算商品总价格
            productTotalAmount = productTotalAmount.add(settlementProductVO.getProductAmount());
        }

        //用户默认地址，没有时为空
        UserAddress userAddress = userAddressService.selectUserAddressByDef();

        return new SettlementVO()
                .setSettlementProductVOS(settlementProductVOS)
                .setCarriage(new BigDecimal(0))
                .setDiscount(new BigDecimal(0))
                .setUserAddress(userAddress)
                .setProductTotalAmount(productTotalAmount)
                .setAmount(productTotalAmount.subtract(carriage).subtract(discount));
    }

    /**
     * @return
     * @Description 拼接Id ，主要作为redis锁的 key
     * @Date 2021/5/5 7:58
     * @Param
     */
    public String getJoinId(List<Long> specificationId, Long productId) {
        String cartSpecificationId = specificationId.stream().map(String::valueOf).collect(Collectors.joining(","));
        return productId + "_" + cartSpecificationId;
    }
}
