package com.yyy.springboot.controller;

import com.yyy.springboot.config.Update;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.service.ProductService;
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
    public Result<Integer> insertProduct(@Validated @RequestBody Product product) {
        productService.insertProduct(product);
        return ResultUtil.success();
    }

    @GetMapping(value = {"/typeAndBrandId/{TypeId}/{BrandId}", "/typeAndBrandId/{TypeId}"})//required = false 不是必须的字段
    public Result<List<Product>> selectProductByProductTypeIdAndProductBranId(@PathVariable(value = "TypeId",required = true) Integer productTypeId, @PathVariable(value = "BrandId", required = false) Integer productBrandId) {
        List<Product> products = productService.selectProductByProductTypeIdAndProductBranId(productTypeId, productBrandId);
        if (products == null || products.size() == 0)
            return ResultUtil.success();
        else
            return ResultUtil.success(products);
    }
    @GetMapping("/{id}")
    public Result<ProductDTO> selectProductDetailDTOByProductId(@PathVariable(value = "id") Long id){
        ProductDTO productDTO = productService.selectProductDetailDTOByProductId(id);
        if (productDTO==null)
            return ResultUtil.success();
        else
            return ResultUtil.success(productDTO);
    }

    @GetMapping("/{id}/{psdId}")
    public Result<ProductAmountDTO> selectProductAmountDTOByProductId(@PathVariable(value = "id") Long productId, @PathVariable(value = "psdId") Long[] psdId){
        ProductAmountDTO productAmountDTO = productService.selectProductAmountDTOByProductId(productId, psdId);
        if (productAmountDTO==null)
            return ResultUtil.success();
        else
            return ResultUtil.success(productAmountDTO);
    }

    @GetMapping("/specificationDetailId/{id}")
    public Result<List<List<Long>>> selectProductRepertoryMidPsdIdsByProductId(@PathVariable(value = "id") Long productId) {
        List<List<Long>> lists = productService.selectProductRepertoryMidPsdIdsByProductId(productId);
        if (lists==null||lists.size()==0)
            return ResultUtil.success();
        else
            return ResultUtil.success(lists);
    }
}
