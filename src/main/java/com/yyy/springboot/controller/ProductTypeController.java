package com.yyy.springboot.controller;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductType;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.entitys.User;
import com.yyy.springboot.service.ProductService;
import com.yyy.springboot.service.ProductTypeService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productTypes")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @PostMapping
    public Result<Integer> insertProductType(@Validated @RequestBody ProductType productType) {
        productTypeService.insertProductType(productType);
        return ResultUtil.success();
    }

    @GetMapping
    public Result<List<ProductType>> selectProductType() {
        List<ProductType> productTypes = productTypeService.selectProductType();
        if(productTypes==null || productTypes.size()==0)
            return ResultUtil.success();
        else
            return ResultUtil.success(productTypes);
    }
}
