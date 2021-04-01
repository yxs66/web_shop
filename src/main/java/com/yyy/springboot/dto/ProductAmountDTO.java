package com.yyy.springboot.dto;

import com.yyy.springboot.entitys.ProductRepertoryMid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class ProductAmountDTO {
    private Long id;
    private Long productId;
    private Integer amount;
    private List<ProductRepertoryMid> productRepertoryMids;

}
