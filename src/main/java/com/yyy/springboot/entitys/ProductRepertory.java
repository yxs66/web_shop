package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_repertory")
public class ProductRepertory {
    private Long id;//商品库存编号
    @TableField("product_id")
    private String productId;//商品编号
    private Integer amount;//商品金额
    private Integer num;//商品数量
}
