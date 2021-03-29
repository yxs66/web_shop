package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductBrand;

import java.util.List;

public interface ProductBrandService {
    List<ProductBrand> selectProductBrand();

    ProductBrand selectProductBrandById(Long id);

    void insertProductBrand(ProductBrand productBrand);

    void deleteProductBrandById(Long id);

    void updateProductBrandById(ProductBrand productBrand);
}
