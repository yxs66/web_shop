package com.yyy.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Deacription 用于添加删除购物车
 * @Author yxs
 * @Date 2021/5/4 17:04
 * @Version 1.0
 **/
@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecificationIdDTO {
    private Long productId;
    private List<Long> specificationId;
    //商品数量
    private Integer productNum;
}
