package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.User;

import java.util.List;

public interface ProductService {
    List<Product> selectProduct();

    Product selectProductById(Long id);

    List<Product> selectProductByProductTypeIdAndProductBranId(Long productTypeId,Long productBrandId);

    void insertProduct(Product product);

    void deleteProductById(Long id);

    void updateProductById(Product product);
}
