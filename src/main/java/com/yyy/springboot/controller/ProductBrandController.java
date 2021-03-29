package com.yyy.springboot.controller;

import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.entitys.ProductType;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.entitys.User;
import com.yyy.springboot.service.ProductBrandService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productBrands")
public class ProductBrandController {
    @Autowired
    private ProductBrandService productBrandService;

    @PostMapping
    public Result<Integer> insertProductBrand(@Validated @RequestBody ProductBrand productBrand) {
        productBrandService.insertProductBrand(productBrand);
        return ResultUtil.success();
    }

    @GetMapping
    public Result<List<ProductBrand>> selectProductBrand() {
        List<ProductBrand> productBrands = productBrandService.selectProductBrand();
        if(productBrands==null || productBrands.size()==0)
            return ResultUtil.success();
        else
            return ResultUtil.success(productBrands);
    }

    @GetMapping("/{productTypeId}")
    public Result<List<ProductBrand>> selectProductBrandByProductTypeId(@PathVariable("productTypeId") Long productTypeId) {
        List<ProductBrand> productBrands = productBrandService.selectProductBrandByProductTypeId(productTypeId);
        if(productBrands==null || productBrands.size()==0)
            return ResultUtil.success();
        else
            return ResultUtil.success(productBrands);
    }
}
