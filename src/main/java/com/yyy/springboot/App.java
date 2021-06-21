package com.yyy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.yyy.springboot.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@ComponentScan(basePackages = {"com.iotechina.domain","com.yyy.springboot"})
@EnableScheduling //开启定时器
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
