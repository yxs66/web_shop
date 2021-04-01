package com.yyy.springboot.service;

import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.entitys.Product;

import java.util.List;

public interface ProductService {
    List<Product> selectProduct();

    Product selectProductById(Long id);

    List<Product> selectProductByProductTypeIdAndProductBranId(Integer productTypeId,Integer productBrandId);

    ProductDTO selectProductDetailDTOByProductId(Long id);

    /**
     *
     * @param ProductId 商品id
     * @param psdId 详细规格表id
     * @return
     */
    ProductAmountDTO selectProductAmountDTOByProductId(Long ProductId, Long [] psdId);

    void insertProduct(Product product);

    void deleteProductById(Long id);

    void updateProductById(Product product);
}
