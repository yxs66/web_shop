package com.yyy.springboot.exception;

import com.yyy.springboot.entitys.Result;

/**
 * @Description
 * @Author yyy
 * @CreateDate 2021/6/9
 * @Version 1.0
 */
public class MyException extends RuntimeException{
    private Integer code;
    public MyException(Result result){
        super(result.getMsg());
        this.code = result.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
