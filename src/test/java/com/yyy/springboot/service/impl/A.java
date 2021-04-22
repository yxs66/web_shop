package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.service.ProductBrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/22 3:04
 * @Version 1.0
 **/
@SpringBootTest
public class A {
    @Autowired
    private ProductBrandService productBrandService;

    @Test
    public void test(){
        ProductBrand productBrand = new ProductBrand();
        productBrand.setImage("1");
        productBrand.setName("dd");
        productBrandService.insertProductBrand(productBrand);
    }

}
