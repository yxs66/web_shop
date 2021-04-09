package com.yyy.springboot.util;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.Result;

public class ResultUtil {

    public static <T> Result<T> success(){
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }
    public static <T> Result<T> success(T data){
        return new Result<T>().setCode(ResultEnum.SUCCESS.getCode()).setMsg(ResultEnum.SUCCESS.getMsg()).setData(data);
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

    public static <T> Result<T> repeatProductFail(){
        return new Result<T>(ResultEnum.REPEAT_PRODUCT_FAIL.getCode(), ResultEnum.REPEAT_PRODUCT_FAIL.getMsg());
    }

    public static <T> Result<T> productParamIllegal(){
        return new Result<T>(ResultEnum.PRODUCT_PARAM_ILLEGAL.getCode(), ResultEnum.PRODUCT_PARAM_ILLEGAL.getMsg());
    }

    public static <T> Result<T> illegalOperationInParam(){
        return new Result<T>(ResultEnum.ILLEGAL_OPERATION_IN_PARAM.getCode(), ResultEnum.ILLEGAL_OPERATION_IN_PARAM.getMsg());
    }

    public static <T> Result<T> result(int code, String msg){
        return new Result<T>(code, msg);
    }

    public static <T> Result<T> result(int code, String msg,T data){
        return new Result<T>(code, msg, data);
    }
}
