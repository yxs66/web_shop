package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyy.springboot.entitys.User;
import com.yyy.springboot.mapper.UserMapper;
import com.yyy.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void insertUser(User user) {
        user.setId(null);
        userMapper.insert(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateUserById(User user) {
        userMapper.updateById(user);
    }
}
