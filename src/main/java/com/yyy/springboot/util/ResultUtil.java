package com.yyy.springboot.util;

import com.yyy.springboot.entitys.Result;

public class ResultUtil {

    public static <T> Result<T> success(){
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }
    public static <T> Result<T> success(T data){
        return new Result<T>().setCode(ResultEnum.SUCCESS.getCode()).setMsg(ResultEnum.SUCCESS.getMsg()).setDate(data);
    }

    public static <T> Result<T> validatedException(String message){
        return new Result<T>((ResultEnum.VALIDATED_EXCEPTION.getCode()), message);
    }

    public static <T> Result<T> typeConverterException(){
        return new Result<T>(ResultEnum.CONVERTER_EXCEPTION.getCode(),ResultEnum.CONVERTER_EXCEPTION.getMsg());
    }

    public static <T> Result<T> unKnowException(){
        return new Result<T>(ResultEnum.UNKNOW_ERROR.getCode(),ResultEnum.UNKNOW_ERROR.getMsg());
    }

    public static <T> Result<T> result(int code, String msg){
        return new Result<T>(code, msg);
    }

    public static <T> Result<T> result(int code, String msg,T data){
        return new Result<T>(code, msg, data);
    }
}
