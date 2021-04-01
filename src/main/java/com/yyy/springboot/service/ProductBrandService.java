package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductBrand;

import java.util.List;

public interface ProductBrandService {
    List<ProductBrand> selectProductBrand();

    ProductBrand selectProductBrandById(Integer id);

    List<ProductBrand> selectProductBrandByProductTypeId(Integer productTypeId);

    void insertProductBrand(ProductBrand productBrand);

    void deleteProductBrandById(Integer id);

    void updateProductBrandById(ProductBrand productBrand);
}
