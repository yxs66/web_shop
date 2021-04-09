package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.entitys.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    ProductDTO selectProductDetailDTOByProductId(Long id);

    List<ProductAmountDTO> selectProductAmountDTOByProductId(Long productId);

    @Select("<script> select p.id,p.name,p.brand_id,p.type_id,p.image,p.user_id,pr.id prId,min(pr.amount) amount,pr.num " +
            "from product p " +
            "left join product_repertory pr " +
            "on p.id = pr.product_id " +
                "<where>" +
                    "p.type_id=#{productTypeId} " +
                    "<if test='productBrandId!=null'>" +
                    "and p.brand_id=#{productBrandId}" +
                    "</if>" +
                " group by p.id " +
                "</where>" +
            "</script>")
    List<Product> selectProductByProductTypeIdAndProductBranId(@Param("productTypeId") Integer productTypeId, @Param("productBrandId") Integer productBrandId);
}
