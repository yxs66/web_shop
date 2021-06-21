package com.yyy.springboot.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/29 14:12
 * @Version 1.0
 **/
@Configuration
public class MyRestTemplate {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
