package com.yyy.springboot.util;

public enum ResultEnum {
    SUCCESS(200,"成功"),
    UNKNOW_ERROR(-1, "未知错误"),
    VALIDATED_EXCEPTION(401, "参数验证异常"),
    CONVERTER_EXCEPTION(402,"类型转换异常");

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
