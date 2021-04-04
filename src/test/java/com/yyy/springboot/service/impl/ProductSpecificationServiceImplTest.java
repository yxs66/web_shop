package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.entitys.ProductSpecification;
import com.yyy.springboot.service.ProductSpecificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductSpecificationServiceImplTest {
    @Autowired
    ProductSpecificationService service;
    @Test
    public void test1(){
        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setProductId(1376559188662059009L);
        productSpecification.setName("版本");
        service.insertProductSpecification(productSpecification);
        System.out.println(productSpecification.getId());
    }
}