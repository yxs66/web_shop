package com.yyy.springboot.controller;

import com.yyy.springboot.dto.OrderDTO;
import com.yyy.springboot.dto.ProductSpecificationIdDTO;
import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.service.OrderMasterService;
import com.yyy.springboot.util.ResultUtil;
import com.yyy.springboot.vo.OrderVO;
import com.yyy.springboot.vo.SettlementProductVO;
import com.yyy.springboot.vo.SettlementVO;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderMasterService orderMasterService;

    @PostMapping
    public Result<String> insertOrder(@RequestBody OrderDTO orderDTO) {
        orderMasterService.insertOrderMaster(orderDTO.getProductSpecificationIdDTOS(), orderDTO.getUserAddressId());
        return ResultUtil.success();
    }

    @PostMapping("/pay")
    public Result<String> orderPay(@RequestBody OrderDTO orderDTO){
        orderMasterService.orderPay(orderDTO.getOrderId(),orderDTO.getProductSpecificationIdDTOS());
        return ResultUtil.success();
    }

    @GetMapping("/settlement")
    public Result<SettlementVO> selectSettlement(@RequestBody List<ProductSpecificationIdDTO> productSpecificationIdDTOS){
        SettlementVO settlementVO = orderMasterService.selectSettlement(productSpecificationIdDTOS);
        if (ObjectUtils.isEmpty(settlementVO))
            return ResultUtil.success();
        else
            return ResultUtil.success(settlementVO);
    }

    @GetMapping("/status/{status}")
    @Trace  // 使用 @Trace注解，则此方法会被加入到追踪链中
    public Result<List<OrderVO>> selectOrderByUserIdAndStatus(@PathVariable("status") Byte status){
        //在被追踪的方法中自定义 tag (在追踪连的详细信息里显示)
        ActiveSpan.tag("my_tag", "my_value");
        List<OrderVO> orderVOS = orderMasterService.selectOrderVOByUserIdAndStatus(status);
        if (CollectionUtils.isEmpty(orderVOS))
            return ResultUtil.success();
        else
            return ResultUtil.success(orderVOS);
    }

    @GetMapping
    public Result<List<OrderVO>> selectOrderByUserId(){
        List<OrderVO> orderVOS = orderMasterService.selectOrderVOByUserId();
        if (CollectionUtils.isEmpty(orderVOS))
            return ResultUtil.success();
        else
            return ResultUtil.success(orderVOS);
    }

}
