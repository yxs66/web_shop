package com.yyy.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyy.springboot.entitys.ProductSpecification;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface ProductSpecificationService extends IService<ProductSpecification> {
    List<ProductSpecification> selectProductSpecifications();

    ProductSpecification selectProductSpecificationById(Long id);

    List<ProductSpecification> selectProductSpecificationByProductId(Long productId);

    void insertProductSpecification(@Valid ProductSpecification productSpecifications);

    void deleteProductSpecificationById(Long id);

    void updateProductSpecificationById(ProductSpecification productSpecification);
}
