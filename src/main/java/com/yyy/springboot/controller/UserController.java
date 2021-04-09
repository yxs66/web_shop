package com.yyy.springboot.controller;

import com.yyy.springboot.config.Update;
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
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Result<List<User>> selectUsers() {
        List<User> users = userService.selectUsers();
        if(CollectionUtils.isEmpty(users))
            return ResultUtil.success();
        else
            return ResultUtil.success(users);
    }

    @GetMapping("/{id}")
    public Result<User> selectUserById(@PathVariable("id") Long id) {
        User user = userService.selectUserById(id);
        if(ObjectUtils.isEmpty(user))
            return ResultUtil.success();
        else
            return ResultUtil.success(user);
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
