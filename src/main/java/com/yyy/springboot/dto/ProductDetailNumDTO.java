package com.yyy.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailNumDTO {

    private Map<String,String> productSpecificationDetail;//商品详细规格 {颜色：白色}
    private BigDecimal amount;//商品价格
    private Integer num;//商品数量

}
