package com.yyy.springboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yyy.springboot.config.Update;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.entitys.User;
import com.yyy.springboot.entitys.UserAddress;
import com.yyy.springboot.service.UserAddressService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/userAddresses")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @GetMapping
    public Result<List<UserAddress>> selectUserAddresses() {
        List<UserAddress> userAddresses = userAddressService.selectUserAddress();
        if(userAddresses==null || userAddresses.size()==0)
            return ResultUtil.success();
        else
            return ResultUtil.success(userAddresses);
    }

    @GetMapping("/{id}")
    public Result<UserAddress> selectUserAddressById(@PathVariable("id") Long id) {
        UserAddress userAddress = userAddressService.selectUserAddressById(id);
        if(userAddress==null)
            return ResultUtil.success();
        else
            return ResultUtil.success(userAddress);
    }

    @GetMapping("/userId/{id}")
    public Result<List<UserAddress>> selectUserAddressByUserId(@PathVariable("id") Long userId) {
        List<UserAddress> userAddresses = userAddressService.selectUserAddressByUserId(userId);
        if(userAddresses==null || userAddresses.size()==0)
            return ResultUtil.success();
        else
            return ResultUtil.success(userAddresses);
    }

    @GetMapping("/def/{userId}")
    public Result<UserAddress> selectUserAddressByDef(@PathVariable("userId") Long userId) {
        UserAddress userAddress = userAddressService.selectUserAddressByDef(userId);
        if(userAddress==null)
            return ResultUtil.success();
        else
            return ResultUtil.success(userAddress);
    }

    @PostMapping
    public Result<Integer> insertUserAddress(@Validated @RequestBody UserAddress userAddress) {
        userAddressService.insertUserAddress(userAddress);
        return ResultUtil.success();
    }

    @DeleteMapping("/{id}")
    public Result<Integer> deleteUserAddressById(@PathVariable("id") Long id) {
        userAddressService.deleteUserAddressById(id);
        return ResultUtil.success();
    }

    @PutMapping
    public Result<Integer> updateUserAddressById(@Validated @RequestBody UserAddress userAddress) {
        userAddressService.updateUserAddressById(userAddress);
        return ResultUtil.success();
    }
}
