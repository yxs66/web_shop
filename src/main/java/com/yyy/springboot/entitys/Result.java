package com.yyy.springboot.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String msg;
    private T date;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
