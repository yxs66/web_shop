package com.yyy.springboot.service;

import com.yyy.springboot.entitys.User;

import java.util.List;

public interface UserService {
    List<User> selectUsers();

    User selectUserById(Long id);

    void insertUser(User user);

    void deleteUserById(Long id);

    void updateUserById(User user);
}
