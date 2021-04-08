package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductRepertory;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface ProductRepertoryService {
    List<ProductRepertory> selectProductRepertory();

    ProductRepertory selectProductRepertoryById(Long id);

    ProductRepertory selectProductRepertoryByProductId(Long productId);

    void insertProductRepertory(@Valid ProductRepertory productRepertory);

    void deleteProductRepertoryById(Long id);

    void updateProductRepertoryById(ProductRepertory productRepertory);

}
