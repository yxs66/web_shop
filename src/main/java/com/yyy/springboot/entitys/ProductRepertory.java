package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_repertory")
public class ProductRepertory {
    @TableId(type = IdType.AUTO)
    private Long id;//商品库存编号

    @TableField("product_id")
    @NotNull(message = "ProductRepertory.productId.null")
    private Long productId;//商品编号

    @NotNull(message = "ProductRepertory.amount.null")
    private BigDecimal amount;//商品金额

    @NotNull(message = "ProductRepertory.num.null")
    private Integer num;//商品数量
}
