package com.yyy.springboot.remote.controller;

import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.remote.service.RemoteUserService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Deacription 远程访问Controller
 * @Author yxs
 * @Date 2021/4/30 13:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/remoteUser")
public class RemoteUserController {

    @Autowired
    private RemoteUserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String,String> map){
        Map<String, Object> login = userService.login(map);
        return ResultUtil.success(login);
    }

    @GetMapping("/getUserInfo")
    public Result<Object> getUserInfo(@RequestHeader("token") String token) {
        Object userInfo = userService.getUserInfo(token);
        return ResultUtil.success(userInfo);
    }

    @GetMapping("/getAllUser/{currentPageNum}/{pageSize}")
    public Result<Object> getAllUser(@PathVariable("currentPageNum") long currentPageNum,@PathVariable("pageSize") long pageSize) {
        Object allUser = userService.getAllUser(currentPageNum, pageSize);
        return ResultUtil.success(allUser);
    }
}
