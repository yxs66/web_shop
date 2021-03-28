package com.yyy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yyy.springboot.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }



}
