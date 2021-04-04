package com.yyy.springboot.service;

import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductBrand;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
@Validated // 必加
public interface ProductBrandService {
    List<ProductBrand> selectProductBrand();

    ProductBrand selectProductBrandById(Integer id);

    List<ProductBrand> selectProductBrandByProductTypeId(Integer productTypeId);

    //Service层面进行校验@Valid
    void insertProductBrand(@Valid ProductBrand productBrand);

    void deleteProductBrandById(Integer id);

    void updateProductBrandById(ProductBrand productBrand);
}
