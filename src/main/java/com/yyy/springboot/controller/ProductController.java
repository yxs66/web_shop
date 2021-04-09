package com.yyy.springboot.controller;

import com.sun.xml.internal.bind.v2.TODO;
import com.yyy.springboot.config.Insert;
import com.yyy.springboot.config.Update;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.dto.ProductDetailDTO;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.Result;
import com.yyy.springboot.service.ProductService;
import com.yyy.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    批量插入商品
    @PostMapping
    public Result<Integer> insertProduct(@Validated(Insert.class) @RequestBody ProductDetailDTO productDetailDTO) {
        productService.insertProductDetailDTO(productDetailDTO);
        return ResultUtil.success();
    }

    //指定商品id插入商品规格
    @PostMapping("/{productId}")
    public Result<Integer> insertProductSpecificationDetailByProductId(@PathVariable("productId") Long productId,@Validated(Default.class) @RequestBody ProductDetailDTO productDetailDTO) {
        productService.insertProductSpecificationDetailByProductId(productId,productDetailDTO);
        return ResultUtil.success();
    }

    //通过用户id查找商品
    @GetMapping("/userId/{userId}")
    public Result<List<Product>> selectProductByProductUserId(@PathVariable(value = "userId") Long userId) {
        List<Product> products = productService.selectProductByProductUserId(userId);
        if (CollectionUtils.isEmpty(products))
            return ResultUtil.success();
        else
            return ResultUtil.success(products);
    }


    @GetMapping(value = {"/typeAndBrandId/{TypeId}/{BrandId}", "/typeAndBrandId/{TypeId}"})//required = false 不是必须的字段
    public Result<List<Product>> selectProductByProductTypeIdAndProductBranId(@PathVariable(value = "TypeId",required = true) Integer productTypeId, @PathVariable(value = "BrandId", required = false) Integer productBrandId) {
        List<Product> products = productService.selectProductByProductTypeIdAndProductBranId(productTypeId, productBrandId);
        if (CollectionUtils.isEmpty(products))
            return ResultUtil.success();
        else
            return ResultUtil.success(products);
    }
    @GetMapping("/{id}")
    public Result<ProductDTO> selectProductDetailDTOByProductId(@PathVariable(value = "id") Long id){
        ProductDTO productDTO = productService.selectProductDetailDTOByProductId(id);
        if (ObjectUtils.isEmpty(productDTO))
            return ResultUtil.success();
        else
            return ResultUtil.success(productDTO);
    }

    @GetMapping("/{productId}/{psdId}")
    public Result<ProductAmountDTO> selectProductAmountDTOByProductId(@PathVariable(value = "productId") Long productId, @PathVariable(value = "psdId") Long[] psdId){
        ProductAmountDTO productAmountDTO = productService.selectProductAmountDTOByProductId(productId, psdId);
        if (ObjectUtils.isEmpty(productAmountDTO))
            return ResultUtil.success();
        else
            return ResultUtil.success(productAmountDTO);
    }

    @GetMapping("/specificationDetailId/{productId}")
    public Result<List<List<Long>>> selectProductRepertoryMidPsdIdsByProductId(@PathVariable(value = "productId") Long productId) {
        List<List<Long>> lists = productService.selectProductRepertoryMidPsdIdsByProductId(productId);
        if (CollectionUtils.isEmpty(lists))
            return ResultUtil.success();
        else
            return ResultUtil.success(lists);
    }
}
