package com.yyy.springboot.service;

import com.yyy.springboot.entitys.ProductRepertoryMid;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface ProductRepertoryMidService {
    List<ProductRepertoryMid> selectProductRepertoryDetail();

    ProductRepertoryMid selectProductRepertoryDetailById(Long id);

    void insertProductRepertoryDetail(@Valid ProductRepertoryMid productRepertoryDetail);

    void deleteProductRepertoryDetailById(Long id);

    void updateProductRepertoryDetailById(ProductRepertoryMid productRepertoryDetail);

    Integer selectProductRepertoryMidNullCountByProductId(Long productId);
}
