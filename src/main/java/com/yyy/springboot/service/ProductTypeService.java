package com.yyy.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyy.springboot.dto.ProductAndBrandDTO;
import com.yyy.springboot.dto.TypeBrandSpecificationDTO;
import com.yyy.springboot.entitys.ProductType;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface ProductTypeService extends IService<ProductType> {
    List<ProductType> selectProductType();

    ProductType selectProductTypeById(Integer id);

    void insertProductType(@Valid ProductType productType);

    void deleteProductTypeById(Integer id);

    void updateProductTypeById(ProductType productType);

    ProductAndBrandDTO selectProductAndBrandDtoByProductTypeId(Integer id);

    TypeBrandSpecificationDTO selectTypeBrandSpecificationDTO();

}