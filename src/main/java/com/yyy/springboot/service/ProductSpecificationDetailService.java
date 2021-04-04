package com.yyy.springboot.service;

import com.yyy.springboot.entitys.ProductSpecificationDetail;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
@Validated
public interface ProductSpecificationDetailService {
    List<ProductSpecificationDetail> selectProductSpecificationDetails();

    ProductSpecificationDetail selectProductSpecificationDetailById(Long id);

    List<ProductSpecificationDetail> selectProductSpecificationDetailBySpecificationId(Long specificationId);

    void insertProductSpecificationDetail(@Valid ProductSpecificationDetail productSpecificationDetail);

    void deleteProductSpecificationDetailById(Long id);

    void updateProductSpecificationDetailById(ProductSpecificationDetail productSpecificationDetail);
}
