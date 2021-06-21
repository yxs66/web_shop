package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import sun.rmi.runtime.Log;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_master")
@Accessors(chain = true)
public class OrderMaster {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//订单编号

    @NotNull(message = "OrderMaster.status.null")
    private Byte status;//订单状态  0:待支付 1：已支付 2：已过期 3：已退款 4：待收货 5：已完成

    @TableField("product_total")
    @NotNull(message = "OrderMaster.productTotal.null")
    private Integer productTotal;//商品件数

    @TableField("user_openid")
    @NotNull(message = "OrderMaster.userId.null")
    private Long userId;//购买者openid

    @NotBlank(message = "OrderMaster.userAddressId.null")
    @TableField("user_address_id")
    private Long userAddressId;//用户地址id

    @TableField("product_total_amount")
    @NotNull(message = "OrderMaster.productTotalAmount.null")
    private BigDecimal productTotalAmount;

    private BigDecimal discount;//优惠

    private BigDecimal carriage;//运费

    @NotNull(message = "OrderMaster.amount.null")
    private BigDecimal amount;//支付金额
}
