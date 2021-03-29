package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> selectProductType();

    ProductType selectProductTypeById(Long id);

    void insertProductType(ProductType productType);

    void deleteProductTypeById(Long id);

    void updateProductTypeById(ProductType productType);
}
