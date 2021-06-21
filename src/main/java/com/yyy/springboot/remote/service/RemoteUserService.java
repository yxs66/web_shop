package com.yyy.springboot.remote.service;

import com.yyy.springboot.remote.service.impl.RemoteUserServiceImpl;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

//@FeignClient(value = "role-service",fallback = RemoteUserServiceImpl.class)
@FeignClient(value = "role-service")
public interface RemoteUserService {

    @PostMapping("/user/normal/login")
    Map<String, Object> login(Map<String, String> map);

    @GetMapping("/user/normal/getUserInfo")
    Object getUserInfo(@RequestHeader("token") String token);

    @GetMapping("/user/normal/findAll/{currentPageNum}/{pageSize}")
    Object getAllUser(@PathVariable("currentPageNum") long currentPageNum,@PathVariable("pageSize") long pageSize);
}
