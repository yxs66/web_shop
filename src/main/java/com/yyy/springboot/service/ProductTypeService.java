package com.yyy.springboot.service;

import com.yyy.springboot.dto.ProductAndBrandDTO;
import com.yyy.springboot.entitys.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> selectProductType();

    ProductType selectProductTypeById(Integer id);

    void insertProductType(ProductType productType);

    void deleteProductTypeById(Integer id);

    void updateProductTypeById(ProductType productType);

    ProductAndBrandDTO selectProductAndBrandDtoByProductTypeId(Integer id);
}
