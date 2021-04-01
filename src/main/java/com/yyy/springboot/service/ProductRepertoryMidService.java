package com.yyy.springboot.service;

import com.yyy.springboot.entitys.ProductRepertoryMid;

import java.util.List;

public interface ProductRepertoryMidService {
    List<ProductRepertoryMid> selectProductRepertoryDetail();

    ProductRepertoryMid selectProductRepertoryDetailById(Long id);

    void insertProductRepertoryDetail(ProductRepertoryMid productRepertoryDetail);

    void deleteProductRepertoryDetailById(Long id);

    void updateProductRepertoryDetailById(ProductRepertoryMid productRepertoryDetail);
}
