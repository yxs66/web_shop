package com.yyy.springboot.service;

import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.dto.ProductDetailDTO;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.vo.ProductVO;

import java.util.List;

public interface ProductService {
    List<Product> selectProduct();

    Product selectProductById(Long id);

    List<Product> selectProductByProductTypeIdAndProductBranId(Integer productTypeId, Integer productBrandId);

    List<ProductVO> selectProductVoByProductUserId(Long userId);

    ProductDTO selectProductDetailDTOByProductId(Long id);

    /**
     * @param ProductId 商品id
     * @param psdId     详细规格表id
     * @return
     */
    ProductAmountDTO selectProductAmountDTOByProductId(Long ProductId, Long[] psdId);

    List<List<Long>> selectProductRepertoryMidPsdIdsByProductId(Long productId);

    void insertProduct(Product product);

    void insertProductDetailDTO(ProductDetailDTO productDetailDTO);

    void deleteProductById(Long id);

    void updateProductById(Product product);

    void insertProductSpecificationDetailByProductId(Long productId, ProductDetailDTO productDetailDTO);

    ProductVO selectProductSpecificationVOByProductId(Long productId);
}
