package com.yyy.springboot.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyy.springboot.util.ResultEnum;
import com.yyy.springboot.exception.TypeConverterException;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class WebLogAspect {

    @Autowired
    private ObjectMapper objectMapper;

    //..表示包及子包 该方法代表controller层的所有方法
    @Pointcut("execution(public * com.yyy.springboot.controller..*.*(..)) || execution(public * com.yyy.springboot.remote.controller..*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取skywalking traceId
        log.info("traceId:"+TraceContext.traceId());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Signature signature = joinPoint.getSignature();

        log.info("--------------request---------------");
        log.info("URL:" + request.getRequestURI());
        log.info("HTTP_METHOD:" + request.getMethod());
        log.info("OBJECT_METHOD:" + signature.getDeclaringTypeName() + "." + signature.getName()); //获取类方法（全名）
        log.info("IP:" + request.getRemoteAddr());

        String[] parameterNames = ((MethodSignature) signature).getParameterNames();
        Object[] parameterValue = joinPoint.getArgs();
        StringBuilder stringBuilder = new StringBuilder();

        int paramLength = null == parameterNames ? 0 : parameterNames.length;
        if (paramLength == 0) {
            stringBuilder.append("PARAMETER:{} ");
        } else {
            stringBuilder.append("PARAMETER:[");
            try {
                for (int i = 0; i < paramLength - 1; i++) {
                    if (parameterValue[i] instanceof MultipartFile) {
                        stringBuilder.append(parameterNames[i] + "=" + objectMapper.writeValueAsString(((MultipartFile) parameterValue[i]).getOriginalFilename())).append(",");
                    } else
                        stringBuilder.append(parameterNames[i] + "=" + objectMapper.writeValueAsString(parameterValue[i])).append(",");
                }
                if (parameterValue[parameterNames.length - 1] instanceof HttpServletRequest) {
                    stringBuilder.append("]");
                } else
                    stringBuilder.append(parameterNames[parameterNames.length - 1] + "=" + objectMapper.writeValueAsString(parameterValue[parameterNames.length - 1])).append("]");
            } catch (JsonProcessingException e) {
                throw new TypeConverterException(ResultEnum.CONVERTER_EXCEPTION);
            }
        }
        log.info(stringBuilder.toString());
    }

    @AfterReturning(pointcut = "pointcut()", returning = "ret")
    public void doAfterReturning(Object ret) {
        log.info("--------------response---------------");
        try {
            log.info("RETURN:" + objectMapper.writeValueAsString(ret));
        } catch (JsonProcessingException e) {
            throw new TypeConverterException(ResultEnum.CONVERTER_EXCEPTION);
        }
    }
}

