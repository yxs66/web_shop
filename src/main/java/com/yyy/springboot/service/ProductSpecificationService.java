package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductSpecification;

import java.util.List;

public interface ProductSpecificationService {
    List<ProductSpecification> selectProductSpecifications();

    ProductSpecification selectProductSpecificationById(Long id);

    List<ProductSpecification> selectProductSpecificationByProductId(Long productId);

    void insertProductSpecification(ProductSpecification productSpecification);

    void deleteProductSpecificationById(Long id);

    void updateProductSpecificationById(ProductSpecification productSpecification);
}
