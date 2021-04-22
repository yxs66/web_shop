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

import javax.validation.groups.Default;
import java.time.LocalTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{current}/{size}")
    public Result<UserDTO> selectUsers(@PathVariable("current") long current, @PathVariable("size") long size) {
        UserDTO userDTO = userService.selectUsers(current, size);
        if(ObjectUtils.isEmpty(userDTO))
            return ResultUtil.success();
        else
            return ResultUtil.success(userDTO);
    }

    @GetMapping("/{id}")
    public Result<User> selectUserById(@PathVariable("id") Long id) {
        User user = userService.selectUserById(id);
        if(ObjectUtils.isEmpty(user))
            return ResultUtil.success();
        else
            return ResultUtil.success(user);
    }

    @GetMapping("/username/{username}")
    public Result<List<User>> selectUserByUsernames(@PathVariable("username") String username) {
        List<User> users = userService.selectUserByUsernames(username);
        if(CollectionUtils.isEmpty(users))
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
    public Result<Integer> updateUserById(@Validated({Update.class,Default.class}) @RequestBody User user) {
        userService.updateUserById(user);
        return ResultUtil.success();
    }

}
