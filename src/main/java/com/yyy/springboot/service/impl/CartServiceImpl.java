package com.yyy.springboot.service.impl;

import com.yyy.springboot.dto.ProductSpecificationIdDTO;
import com.yyy.springboot.entitys.Cart;
import com.yyy.springboot.entitys.ProductSpecificationDetail;
import com.yyy.springboot.exception.MySQLException;
import com.yyy.springboot.mapper.CartMapper;
import com.yyy.springboot.mapper.ProductSpecificationDetailMapper;
import com.yyy.springboot.service.CartService;
import com.yyy.springboot.util.ResultUtil;
import com.yyy.springboot.util.ShareThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/5/4 17:11
 * @Version 1.0
 **/
@Service
@Slf4j
@RefreshScope
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ShareThreadLocal<Long> shareThreadLocal;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductSpecificationDetailMapper productSpecificationDetailMapper;

    @Value("${cart_prefix}")
    private String prefix = "cart_";

    @Override
    public List<Cart> carts() {
        HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
        Long userId = shareThreadLocal.get();

        // 获取用户购物车里所有商品（key，value）
        Map<Object, Object> entries = operations.entries(prefix + userId);
        // 获取用户购物车里的key
        Set<Object> keys = operations.keys(prefix + userId);
        // 去除重复key
        keys = keys.stream().map(x -> {
            String key = (String) x;
            return key.split(":")[0];
        }).collect(Collectors.toSet());

        // 赋值num
        return keys.stream().map(x -> {
            Cart cart = (Cart) entries.get(x + ":info");
            cart.setNum((Integer) entries.get(x + ":num"));
            return cart;
        }).collect(Collectors.toList());
    }

    @Override
    /**
     * @Description
     * @Date 2021/5/4 18:33
     * @Param [cart]
     * @return void
     * shareThreadLocal.get() : 用户id
     */
    public void addCart(ProductSpecificationIdDTO productSpecificationIdDTO) {
        HashOperations<String, Object, Object> operations = redisTemplate.opsForHash();
        Long userId = shareThreadLocal.get();
        String cartId = getCartId(productSpecificationIdDTO.getSpecificationId(), productSpecificationIdDTO.getProductId());
        // 判断redis是否有对应购物车商品
        if (operations.hasKey(prefix + userId, cartId + ":info")) {
            // 有则增加数量
            operations.increment(prefix + userId, cartId + ":num", 1);
            return;
        }
        // 得到商品信息
        Cart cart = cartMapper.selectCartByPsdIds(productSpecificationIdDTO.getProductId(), productSpecificationIdDTO.getSpecificationId(), productSpecificationIdDTO.getSpecificationId().size());
        if (ObjectUtils.isEmpty(cart)) {
            throw new MySQLException(ResultUtil.productNonexistent()); //商品不存在
        }
        List<ProductSpecificationDetail> productSpecificationDetails = productSpecificationDetailMapper.selectProductSpecificationDetailByPsdId(productSpecificationIdDTO.getSpecificationId());
        cart.setSpecificationId(productSpecificationDetails.stream().map(ProductSpecificationDetail::getId).collect(Collectors.toList()));
        cart.setSpecificationName(productSpecificationDetails.stream().map(ProductSpecificationDetail::getName).collect(Collectors.toList()));
        // 存储商品信息和数量
        operations.put(prefix + userId, cartId + ":info", cart);
        operations.put(prefix + userId, cartId + ":num", 1);
    }

    @Override
    public void delCart(List<ProductSpecificationIdDTO> productSpecificationIdDTOS) {
        List<String> cartIds = new ArrayList<>();
        productSpecificationIdDTOS.forEach(x -> {
                    cartIds.add(getCartId(x.getSpecificationId(), x.getProductId()) + ":info");
                    cartIds.add(getCartId(x.getSpecificationId(), x.getProductId()) + ":num");
                }
        );
        redisTemplate.opsForHash().delete(prefix + shareThreadLocal.get(), cartIds.toArray(new String[cartIds.size()]));
    }

    @Override
    /**
     * @Description
     * @Date 2021/5/4 20:11
     * @Param [cartIdDTO, type] type: 0 表示减少数量 1 表示增加数量
     * @return void
     */
    public void inOrDecrementCartNum(ProductSpecificationIdDTO productSpecificationIdDTO, Byte type) {
        if (type != 0 && type != 1) return;

        Long userId = shareThreadLocal.get();
        String cartId = getCartId(productSpecificationIdDTO.getSpecificationId(), productSpecificationIdDTO.getProductId());
        // 判断是否有该商品在购物车
        if (redisTemplate.opsForHash().hasKey(prefix + userId, cartId + ":num")) {
            // 建立一个会话
            redisTemplate.execute(new SessionCallback<Object>() {
                @Override
                public Object execute(RedisOperations redisOperations) throws DataAccessException {
                    List result = null;
                    do {
                        // 当该key被其它客户端改变时,则会中断当前的操作
                        redisOperations.watch(prefix + userId);
                        Integer num = (Integer) redisOperations.opsForHash().get(prefix + userId, cartId + ":num");
                        redisOperations.multi(); //开启事务
                        if (type == 0 && num > 1) {
                            redisOperations.opsForHash().increment(prefix + userId, cartId + ":num", -1);
                        } else if (type == 1) {
                            redisOperations.opsForHash().increment(prefix + userId, cartId + ":num", 1);
                        } else { // num=1时退出循环
                            result = redisOperations.exec(); //提交事务,如果key被改变,则result为null或是size==0
                            redisOperations.unwatch();
                            break;
                        }
                        result = redisOperations.exec();
                        redisOperations.unwatch();
                    } while (result.size() == 0); //如果失败则重试
                    return null;
                }
            });
        }
    }

    /**
     * @Description 拼接cartId ，主要作为hash key
     * @Date 2021/5/5 7:58
     * @Param
     * @return
     */
    public String getCartId(List<Long> specificationId, Long productId) {
        String cartSpecificationId = specificationId.stream().map(String::valueOf).collect(Collectors.joining(","));
        return productId + "_" + cartSpecificationId;
    }
}
