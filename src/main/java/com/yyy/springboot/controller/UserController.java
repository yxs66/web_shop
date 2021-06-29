package com.yyy.springboot.controller;

import com.yyy.springboot.config.Update;
import com.yyy.springboot.dto.UserDTO;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.entitys.User;
import com.yyy.springboot.service.UserService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.groups.Default;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody Map<String, String> map, HttpServletRequest request) {
        Map<String, String> result = userService.login(map, request);
        if (result.size() == 0)
            return ResultUtil.loginFail();
        else
            return ResultUtil.success(result);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        userService.logout(request);
        return ResultUtil.success();
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getInfo(@RequestHeader("token") String token) {
        User info = userService.getInfo(token);
        if (ObjectUtils.isEmpty(info))
            return ResultUtil.tokenInvalidate();
        else {
            Map<String, Object> result = new HashMap<>();
            result.put("name", info.getName());
            result.put("avatar", "user/avatar.jpg");
            result.put("member", info.getMember());
            result.put("birthday", info.getBirthday());
            return ResultUtil.success(result);
        }
    }

    @GetMapping("/{current}/{size}")
    public Result<UserDTO> selectUsers(@PathVariable("current") long current, @PathVariable("size") long size) {
        UserDTO userDTO = userService.selectUsers(current, size);
        if (ObjectUtils.isEmpty(userDTO))
            return ResultUtil.success();
        else
            return ResultUtil.success(userDTO);
    }

    @GetMapping("/{id}")
    public Result<User> selectUserById(@PathVariable("id") Long id) {
        User user = userService.selectUserById(id);
        if (ObjectUtils.isEmpty(user))
            return ResultUtil.success();
        else
            return ResultUtil.success(user);
    }

    @GetMapping("/username/{username}")
    public Result<List<User>> selectUserByUsernames(@PathVariable("username") String username) {
        List<User> users = userService.selectUserByUsernames(username);
        if (CollectionUtils.isEmpty(users))
            return ResultUtil.success();
        else
            return ResultUtil.success(users);
    }

    @PostMapping
    public Result<Integer> insertUser(@Validated({Default.class}) @RequestBody User user) {
        userService.insertUser(user);
        return ResultUtil.success();
    }

    @DeleteMapping("/{id}")
    public Result<Integer> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResultUtil.success();
    }

    @PutMapping
    public Result<Integer> updateUserById(@Validated({Update.class}) @RequestBody User user) {
        userService.updateUserById(user);
        return ResultUtil.success();
    }

}
