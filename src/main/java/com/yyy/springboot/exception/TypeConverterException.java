package com.yyy.springboot.exception;

import com.yyy.springboot.entitys.ResultEnum;

public class TypeConverterException extends RuntimeException {

    private Integer code;

    public TypeConverterException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
