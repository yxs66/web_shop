package com.yyy.springboot.controller;

import com.yyy.springboot.entitys.ProductSpecification;
import com.yyy.springboot.entitys.ProductSpecificationDetail;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.service.ProductSpecificationDetailService;
import com.yyy.springboot.service.ProductSpecificationService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productSpecificationDetail")
public class ProductSpecificationDetailController {

    @Autowired
    private ProductSpecificationDetailService productSpecificationDetailService;

    @PostMapping
    public Result<Integer> insertProductSpecificationDetail(@Validated @RequestBody ProductSpecificationDetail productSpecificationDetail) {
        productSpecificationDetailService.insertProductSpecificationDetail(productSpecificationDetail);
        return ResultUtil.success();
    }

    @GetMapping
    public Result<List<ProductSpecificationDetail>> selectProductSpecificationDetails() {
        List<ProductSpecificationDetail> productSpecificationDetails = productSpecificationDetailService.selectProductSpecificationDetails();
        if(productSpecificationDetails==null || productSpecificationDetails.size()==0)
            return ResultUtil.success();
        else
            return ResultUtil.success(productSpecificationDetails);
    }
}
