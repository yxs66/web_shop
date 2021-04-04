package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.service.ProductBrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductBrandServiceImplTest {

    @Autowired
    private ProductBrandService productBrandService;

    @Test
    public void test1(){
        ProductBrand productBrand = new ProductBrand();
        productBrand.setImage("1");
        productBrand.setName("dd");
        productBrandService.insertProductBrand(productBrand);
    }

}