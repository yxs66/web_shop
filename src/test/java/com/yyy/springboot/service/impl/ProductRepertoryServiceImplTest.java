package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.ProductRepertory;
import com.yyy.springboot.service.ProductRepertoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductRepertoryServiceImplTest {
    @Autowired
    public ProductRepertoryService productRepertoryService;

    @Test
    public void test(){
        ProductRepertory productRepertory = productRepertoryService.selectProductRepertoryByPsdIds(Arrays.asList(1, 3));
        System.out.println(productRepertory);
    }
}