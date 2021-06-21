package com.yyy.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description 结算页面商品
 * @Author yyy
 * @CreateDate 2021/6/11
 * @Version 1.0
 */
@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SettlementProductVO {
    private Long productId;//商品编号

    private String productName;//商品名称

    private String productSpecification;//商品规格

    private int productNum;//商品数量

    private BigDecimal productAmount;//商品金额

    private String productImage;//商品图片
}
