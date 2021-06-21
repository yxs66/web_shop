package com.yyy.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description 订单状态  0:待支付 1：已支付 2：已过期 3：已退款 4：待收货 5：已完成
 * @Author yyy
 * @CreateDate 2021/6/8
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    TO_BE_PAID(Byte.valueOf("0"), "待支付"),
    HAVE_PAID(Byte.valueOf("1"),"已支付"),
    EXPIRED(Byte.valueOf("2"), "已过期"),
    REFUND(Byte.valueOf("3"), "已退款"),
    WAIT_FOR_RECEIVING(Byte.valueOf("4"), "待收货"),
    COMPLETED(Byte.valueOf("5"), "已完成");

    private final Byte status;
    private final String description;

}
