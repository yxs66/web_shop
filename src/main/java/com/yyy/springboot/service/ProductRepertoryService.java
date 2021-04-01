package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductRepertory;

import java.util.List;

public interface ProductRepertoryService {
    List<ProductRepertory> selectProductRepertory();

    ProductRepertory selectProductRepertoryById(Long id);

    void insertProductRepertory(ProductRepertory productRepertory);

    void deleteProductRepertoryById(Long id);

    void updateProductRepertoryById(ProductRepertory productRepertory);
}
