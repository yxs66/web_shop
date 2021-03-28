package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.User;
import com.yyy.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Test
    public void test1(){
        List<User> users = userService.selectUsers();
        System.out.println(users);

    }
}