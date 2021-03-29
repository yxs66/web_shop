package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.User;

import java.util.List;

public interface ProductService {
    List<Product> selectProduct();

    Product selectProductById(Long id);

    void insertProduct(Product product);

    void deleteProductById(Long id);

    void updateProductById(Product product);
}
