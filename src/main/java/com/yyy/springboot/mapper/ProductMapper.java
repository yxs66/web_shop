package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.dto.ProductAmountDTO;
import com.yyy.springboot.dto.ProductDTO;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.vo.ProductVO;
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

    @Select("select p.id,p.name,pb.name brand_name,pt.name type_name,p.image from product p join product_brand pb on pb.id=p.brand_id join product_type pt on pt.id=p.type_id where user_id=#{userId} ")
    List<ProductVO> selectProductVoByProductUserId(Long userId);

//    @Select("select pr.id,pr.amount,pr.num,psd.name from product_repertory pr left join product_repertory_mid prm on pr.id=prm.product_repertory_id left join product_specification_detail psd on prm.psd_id=psd.id  where pr.product_id=(select p.id from product p where p.id=#{productId})")
//    @Select("select pr.id,pr.amount,pr.num from product_repertory pr left join product_repertory_mid prm on pr.id=prm.product_repertory_id where pr.product_id=(select p.id from product p where p.id=#{productId})")
//    @Select("select p.id,pr.id as pr_id,pr.amount,pr.num from product p left join product_repertory pr on p.id=pr.product_id left join product_repertory_mid prm on pr.id=prm.product_repertory_id where p.id=#{productId}")
    ProductVO selectProductSpecificationVOByProductId(Long productId);

}
