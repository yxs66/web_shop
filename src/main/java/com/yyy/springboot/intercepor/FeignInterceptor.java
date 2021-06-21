package com.yyy.springboot.intercepor;

import com.iotechina.domain.User;
import com.yyy.springboot.util.ShareThreadLocal;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Deacription 全局feign拦截器
 * @Author yxs
 * @Date 2021/4/30 16:15
 * @Version 1.0
 **/
//@Configuration
public class FeignInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("000000"+request.getHeader("token"));
        requestTemplate.header("token", request.getHeader("token"));



    }
}
