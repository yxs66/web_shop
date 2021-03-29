package com.yyy.springboot.controller;

import com.yyy.springboot.config.Update;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.entitys.User;
import com.yyy.springboot.service.ProductService;
import com.yyy.springboot.service.UserService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Result<Integer> insertUser( @RequestBody Product product) {
        productService.insertProduct(product);
        return ResultUtil.success();
    }


}
