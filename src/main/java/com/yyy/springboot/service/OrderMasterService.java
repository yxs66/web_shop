package com.yyy.springboot.service;

import com.yyy.springboot.dto.ProductSpecificationIdDTO;
import com.yyy.springboot.vo.OrderVO;
import com.yyy.springboot.entitys.OrderMaster;
import com.yyy.springboot.vo.SettlementVO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderMasterService {
    OrderMaster selectOrderMasterById(Long id);

    void insertOrderMaster(List<ProductSpecificationIdDTO> productSpecificationIdDTOS,Long userAddressId);

    void updateProductTotalAmountById(BigDecimal productTotalAmount, Long orderId);

    SettlementVO selectSettlement(List<ProductSpecificationIdDTO> productSpecificationIdDTO);

    void updateOrderStatusByOrderId(Long orderId, Byte status);

    Byte selectOrderStatusByOrderId(Long orderId);

    void orderPay(Long orderId,List<ProductSpecificationIdDTO> productSpecificationIdDTOS);

    List<OrderVO> selectOrderVOByUserIdAndStatus(Byte status);

    List<OrderVO> selectOrderVOByUserId();
}
