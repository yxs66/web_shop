package com.yyy.springboot.service;

import com.yyy.springboot.dto.ProductDetailDTO;
import com.yyy.springboot.entitys.ProductSpecificationDetail;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Validated
public interface ProductSpecificationDetailService {
    AtomicInteger count=new AtomicInteger(0);

    List<ProductSpecificationDetail> selectProductSpecificationDetails();

    ProductSpecificationDetail selectProductSpecificationDetailById(Long id);

    List<ProductSpecificationDetail> selectProductSpecificationDetailBySpecificationId(Long specificationId);

    void insertProductSpecificationDetail(@Valid ProductSpecificationDetail productSpecificationDetail);

    void isExistProductSpecificationDetail(Collection<Long> keys,Collection<String> collection);

    void deleteProductSpecificationDetailById(Long id);

    void updateProductSpecificationDetailById(ProductSpecificationDetail productSpecificationDetail);

}
