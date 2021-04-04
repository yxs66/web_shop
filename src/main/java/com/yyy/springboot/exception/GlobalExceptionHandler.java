package com.yyy.springboot.exception;

import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(TypeConverterException.class)
    public @ResponseBody Result handlerTypeConverterException(TypeConverterException e){
        log.error("",e);
        return ResultUtil.typeConverterException();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //结合Controller中@Validated使用
    public @ResponseBody Result handlerMethodArgumentNotValidExceptionException(MethodArgumentNotValidException e){
        log.error("",e);
        StringBuilder stringBuilder = new StringBuilder();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();//获取抛出异常的所有错误
        errors.forEach(p -> {
            FieldError fieldError = (FieldError) p;
            stringBuilder.append(fieldError.getDefaultMessage()).append(";");
        });

        return ResultUtil.validatedException(stringBuilder.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody Result handlerConstraintViolationException(ConstraintViolationException e){
        log.error("", e);
        return ResultUtil.validatedException(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public @ResponseBody Result handlerException(Exception e) {
        log.error("",e);
        return ResultUtil.unKnowException();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody Result handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("",e);
        return ResultUtil.typeConverterException();
    }

    @ExceptionHandler(SQLInsertException.class)
    public @ResponseBody Result handlerSQLInsertException(SQLInsertException e){
        log.error("", e);
        return ResultUtil.result(e.getCode(), e.getMessage());
    }

}
