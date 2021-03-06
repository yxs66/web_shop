package com.yyy.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yyy.springboot.dto.ProductAndBrandDTO;
import com.yyy.springboot.dto.TypeBrandSpecificationDTO;
import com.yyy.springboot.entitys.*;
import com.yyy.springboot.service.ProductTypeService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
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
        if(CollectionUtils.isEmpty(productTypes))
            return ResultUtil.success();
        else
            return ResultUtil.success(productTypes);
    }

    @GetMapping("/{productTypeId}")
    public Result<ProductAndBrandDTO> selectProductAndBrandDtoByProductTypeId(@PathVariable("productTypeId") Integer productTypeId) {
        ProductAndBrandDTO productAndBrandDto = productTypeService.selectProductAndBrandDtoByProductTypeId(productTypeId);
        if(ObjectUtils.isEmpty(productAndBrandDto))
            return ResultUtil.success();
        else
            return ResultUtil.success(productAndBrandDto);
    }

    @GetMapping("/typeBrandSpecification")
    public Result<TypeBrandSpecificationDTO> selectTypeBrandSpecificationDTO(){
        TypeBrandSpecificationDTO typeBrandSpecificationDTO = productTypeService.selectTypeBrandSpecificationDTO();
        if(ObjectUtils.isEmpty(typeBrandSpecificationDTO))
            return ResultUtil.success();
        else
            return ResultUtil.success(typeBrandSpecificationDTO);
    }

}
