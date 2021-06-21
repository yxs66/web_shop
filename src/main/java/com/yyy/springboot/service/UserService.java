package com.yyy.springboot.service;

import com.yyy.springboot.dto.UserDTO;
import com.yyy.springboot.entitys.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    public UserDTO selectUsers(long current, long size);

    User selectUserById(Long id);

    void insertUser(User user);

    void deleteUserById(Long id);

    void updateUserById(User user);

    List<User> selectUserByUsernames(String username);

    Map<String,String> login(Map<String,String> map,HttpServletRequest request);

    User getInfo(String token);

    void logout(HttpServletRequest request);
}
