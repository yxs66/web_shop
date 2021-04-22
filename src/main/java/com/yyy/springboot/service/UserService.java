package com.yyy.springboot.service;

import com.yyy.springboot.dto.UserDTO;
import com.yyy.springboot.entitys.User;

import java.util.List;

public interface UserService {
    public UserDTO selectUsers(long current, long size);

    User selectUserById(Long id);

    void insertUser(User user);

    void deleteUserById(Long id);

    void updateUserById(User user);

    List<User> selectUserByUsernames(String username);
}
