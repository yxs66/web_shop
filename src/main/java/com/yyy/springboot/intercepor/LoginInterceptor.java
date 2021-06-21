package com.yyy.springboot.intercepor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.entitys.User;
//import com.iotechina.domain.User;
import com.yyy.springboot.util.ResultUtil;
import com.yyy.springboot.util.ShareThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/25 3:46
 * @Version 1.0
 **/
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ShareThreadLocal<Long> shareThreadLocal;


    /*进入controller方法前执行*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Token");
        log.info("Token:" + token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        if (token == null) {
            Result<Object> result = ResultUtil.validatedException("无token请求头");
            PrintWriter writer = response.getWriter();
            writer.println(objectMapper.writeValueAsString(result));
            return false;
        } else {
            User user = (User) redisTemplate.opsForValue().get("user_" + token);
            if (ObjectUtils.isEmpty(user)) {
                Result<Object> result = ResultUtil.tokenInvalidate();
                PrintWriter writer = response.getWriter();
                writer.println(objectMapper.writeValueAsString(result));
                return false;
            }
            shareThreadLocal.set(user.getId());
            return true;
        }

        //*******************remote**************************//

      /*  String token = request.getHeader("Token");
        log.info("Token:" + token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        if (token == null) {
            Result<Object> result = ResultUtil.validatedException("无token请求头");
            PrintWriter writer = response.getWriter();
            writer.println(objectMapper.writeValueAsString(result));
            return false;
        } else {
            User user = (User) redisTemplate.opsForValue().get(token+"currentUser");
            System.out.println(user);
            if (ObjectUtils.isEmpty(user)) {
                Result<Object> result = ResultUtil.tokenInvalidate();
                PrintWriter writer = response.getWriter();
                writer.println(objectMapper.writeValueAsString(result));
                return false;
            }
            shareThreadLocal.set(Long.valueOf(user.getId()));
            System.out.println(Long.valueOf(user.getId()));
            return true;
        }*/
    }

    /*调用完controller之后，试图渲染之前*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /*整个完成之后，通常用于资源清理*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //程序运行结束之后，删除线程
        shareThreadLocal.remove();
    }

}
