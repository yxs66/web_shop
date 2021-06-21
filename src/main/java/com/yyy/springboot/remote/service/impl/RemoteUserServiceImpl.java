package com.yyy.springboot.remote.service.impl;

import com.yyy.springboot.remote.service.RemoteUserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author yyy
 * @CreateDate 2021/6/13
 * @Version 1.0
 */
//@Component //必须加，用于给FeignClient.fallback用
public class RemoteUserServiceImpl implements RemoteUserService {
    @Override
    public Map<String, Object> login(Map<String, String> map) {
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("login", "sentinel配置openFeign---->login错误啦");
        return objectObjectHashMap;
    }

    @Override
    public Object getUserInfo(String token) {
        return null;
    }

    @Override
    public Object getAllUser(long currentPageNum, long pageSize) {
        return null;
    }
}
