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
@TableName("order_detail")
public class OrderDetail {
    @TableId(type = IdType.AUTO)
    private Long id;//订单详细编号

    @TableField("order_id")
    @NotNull(message = "OrderDetail.orderId.null")
    private Long orderId;//订单编号

    @TableField("product_id")
    @NotNull(message = "OrderDetail.productId.null")
    private Long productId;//商品编号

    @TableField("product_name")
    @NotBlank(message = "OrderDetail.productName.null")
    private String productName;//商品名称

    @TableField("product_specification")
    @NotBlank(message = "OrderDetail.productSpecification.null")
    private String productSpecification;//商品规格

    @TableField("product_num")
    @NotNull(message = "OrderDetail.productNum.null")
    private int productNum;//商品数量

    @TableField("product_amount")
    @NotNull(message = "OrderDetail.productAmount.null")
    private BigDecimal productAmount;//商品金额

    @TableField("product_image")
    @NotBlank(message = "OrderDetail.productImage.null")
    private String productImage;//商品图片

}
