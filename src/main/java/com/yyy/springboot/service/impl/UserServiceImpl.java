package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyy.springboot.dto.UserDTO;
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
    public UserDTO selectUsers(long current, long size) {
        IPage<User> userIPage = userMapper.selectPage(new Page<>(current, size), null);
        UserDTO userDTO = new UserDTO()
                .setCurrent(userIPage.getCurrent())
                .setPages(userIPage.getPages())
                .setSize(userIPage.getSize())
                .setTotal(userIPage.getTotal())
                .setUserList(userIPage.getRecords());
        return userDTO;
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

    @Override
    public List<User> selectUserByUsernames(String username) {
        return userMapper.selectList(new QueryWrapper<User>().like("username", username));
    }
}
