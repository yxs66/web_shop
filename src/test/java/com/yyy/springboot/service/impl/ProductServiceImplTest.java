package com.yyy.springboot.service.impl;

import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void test1(){
//        ProductDTO productDetailDTO = productService.selectProductDetailDTOByProductId(1376559188662059009L);
//        System.out.println(productDetailDTO);

        ProductAmountDTO productAmountDTO = productService.selectProductAmountDTOByProductId(1376559188662059009L, new Long[]{1L});
        System.out.println(productAmountDTO);

    }
}