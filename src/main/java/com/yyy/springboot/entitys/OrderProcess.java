package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_process")
public class OrderProcess {

    @TableId(type = IdType.AUTO)
    private Long id;//订单流程id

    @TableField("order_master_id")
    @NotNull(message = "OrderProcess.orderMasterId.null")
    private Long orderMasterId;//订单流程id

    @NotNull(message = "OrderProcess.status.null")
    private Byte status;//订单流程状态 0:待付款 1：已付款 2：待发货 3：已发货 4：待签收 5：已签收 6：已完结 7：已取消 8：已退款

}
