package com.yyy.springboot.service;


import com.yyy.springboot.dto.ProductAndBrandDTO;
import com.yyy.springboot.dto.TypeBrandSpecificationDTO;
import com.yyy.springboot.entitys.ProductType;
import com.yyy.springboot.entitys.ProductTypeBrandMid;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface ProductTypeBrandMidService {

    void insertProductTypeBrandMid(@Valid ProductTypeBrandMid productTypeBrandMid);
}
