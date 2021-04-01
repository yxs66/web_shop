package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.entitys.Product;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    ProductDTO selectProductDetailDTOByProductId(Long id);

    List<ProductAmountDTO> selectProductAmountDTOByProductId(Long productId);

}
