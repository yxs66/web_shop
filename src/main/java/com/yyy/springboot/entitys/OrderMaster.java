package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import sun.rmi.runtime.Log;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_master")
public class OrderMaster {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//订单编号

    @NotNull(message = "OrderMaster.status.null")
    private Byte status;//订单状态 0:待付款 1：待收货 2：已完成 3：已取消 5：已退款

    @TableField("product_total")
    @NotNull(message = "OrderMaster.productTotal.null")
    private Integer productTotal;//商品件数

    @TableField("user_openid")
    @NotNull(message = "OrderMaster.userId.null")
    private Long userId;//购买者openid

    @NotBlank(message = "OrderMaster.userAddressId.null")
    @TableField("user_address_id")
    private String userAddressId;//用户地址id
}
