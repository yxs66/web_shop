package com.yyy.springboot.service;

import com.yyy.springboot.entitys.ProductSpecification;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface ProductSpecificationService {
    List<ProductSpecification> selectProductSpecifications();

    ProductSpecification selectProductSpecificationById(Long id);

    List<ProductSpecification> selectProductSpecificationByProductId(Long productId);

    void insertProductSpecification(@Valid ProductSpecification productSpecification);

    void deleteProductSpecificationById(Long id);

    void updateProductSpecificationById(ProductSpecification productSpecification);
}
