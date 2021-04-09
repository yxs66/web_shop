package com.yyy.springboot.exception;


import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.util.ResultEnum;

import java.sql.SQLException;

public class MySQLException extends RuntimeException{
    private Integer code;
    public MySQLException(Result result){
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
