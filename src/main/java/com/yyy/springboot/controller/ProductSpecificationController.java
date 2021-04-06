package com.yyy.springboot.controller;

import com.yyy.springboot.config.Insert;
import com.yyy.springboot.dto.ProductDetailDTO;
import com.yyy.springboot.entitys.ProductSpecification;
import com.yyy.springboot.entitys.ProductType;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.service.ProductSpecificationService;
import com.yyy.springboot.service.ProductTypeService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productSpecification")
public class ProductSpecificationController {
    @Autowired
    private ProductSpecificationService productSpecificationService;

    @PostMapping
    public Result<Integer> insertProductSpecification(@Validated @RequestBody ProductSpecification productSpecification) {
        productSpecificationService.insertProductSpecification(productSpecification);
        return ResultUtil.success();
    }

    @GetMapping
    public Result<List<ProductSpecification>> selectProductSpecifications() {
        List<ProductSpecification> productSpecifications = productSpecificationService.selectProductSpecifications();
        if(productSpecifications==null || productSpecifications.size()==0)
            return ResultUtil.success();
        else
            return ResultUtil.success(productSpecifications);
    }


}
