package com.yyy.springboot.service;

import com.yyy.springboot.entitys.ProductSpecificationDetail;

import java.util.List;

public interface ProductSpecificationDetailService {
    List<ProductSpecificationDetail> selectProductSpecificationDetails();

    ProductSpecificationDetail selectProductSpecificationDetailById(Long id);

    List<ProductSpecificationDetail> selectProductSpecificationDetailBySpecificationId(Long specificationId);

    void insertProductSpecificationDetail(ProductSpecificationDetail productSpecificationDetail);

    void deleteProductSpecificationDetailById(Long id);

    void updateProductSpecificationDetailById(ProductSpecificationDetail productSpecificationDetail);
}
