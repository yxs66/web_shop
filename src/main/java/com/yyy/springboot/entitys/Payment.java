package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("payment")
public class Payment {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//支付交易号

    @TableField("order_master_id")
    @NotNull(message = "Payment.orderMasterId.null")
    private Long orderMasterId; //订单编号

    @NotNull(message = "Payment.status.null")
    private Byte status;//支付状态 0：待支付 1：支付成功 2：支付失败

    @NotNull(message = "Payment.amount.null")
    private BigDecimal amount;//支付金额

}
