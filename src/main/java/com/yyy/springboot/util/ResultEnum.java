package com.yyy.springboot.util;

public enum ResultEnum {
    SUCCESS(200,"成功"),
    UNKNOW_ERROR(-1, "未知错误"),
    VALIDATED_EXCEPTION(401, "参数验证异常"),
    CONVERTER_EXCEPTION(402,"类型转换异常"),

    REPEAT_PRODUCT_FAIL(501,"添加重复的商品失败"),
    PRODUCT_PARAM_ILLEGAL(502, "商品参数不合法"),
    PRODUCT_UNDER_STOCK(503, "商品库存不足"),
    PRODUCT_NONEXISTENT(504, "商品不存在"),
    PRODUCT_NUM_ILLEGAL(505,"商品数量不合法"),
    ORDER_EXPIRED(506, "订单超时失效"),
    DOUBLE_ORDER(507, "不可重复下单哦"),
    PLACE_ORDER_FAIL(508,"人数较多，稍微等等!!!"),

    ILLEGAL_OPERATION_IN_PARAM(403,"非法操作之入参不匹配"),

    FILETOOLARGE(601, "文件超过指定大小"),

    LOGINFAIL(403, "登录失败"),
    TOKENINVALIDATE(404, "token失效");


    private final Integer code;
    private final String msg;

    private ResultEnum(Integer code, String msg){
        this.code=code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
