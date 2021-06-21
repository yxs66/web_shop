package com.yyy.springboot.service;

import com.yyy.springboot.dto.ProductSpecificationIdDTO;
import com.yyy.springboot.entitys.Cart;

import java.util.List;


public interface CartService {

    List<Cart> carts();

    void addCart(ProductSpecificationIdDTO productSpecificationIdDTO);

    void delCart(List<ProductSpecificationIdDTO> productSpecificationIdDTOS);

    void inOrDecrementCartNum(ProductSpecificationIdDTO productSpecificationIdDTO, Byte type);


}
