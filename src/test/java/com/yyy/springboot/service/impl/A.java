package com.yyy.springboot.service.impl;

import com.yyy.springboot.entitis.Test1;
import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.mapper.TestMapper;
import com.yyy.springboot.service.ProductBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private TestMapper testMapper;

    @Test
    public void test1(){
        List<Test1> user = testMapper.select();
        System.out.println(user);
        HashMap<Integer,List<Test1>> hashMap = new HashMap();
        user.stream().forEach(x -> {
            if (hashMap.containsKey(x.getUserId())) {
                List<Test1> strings = hashMap.get(x.getUserId());
                Test1 test = new Test1();
                test.setClick(x.getClick());
                test.setNum(x.getNum());
                strings.add(test);
            } else {
                List<Test1> l = new ArrayList<>();
                Test1 test = new Test1();
                test.setClick(x.getClick());
                test.setNum(x.getNum());
                l.add(test);
                hashMap.put(x.getUserId(), l);
            }
//            return hashMap;
        });
        hashMap.get(2777).forEach(System.out::println);



        System.out.println(hashMap);
    }
}
