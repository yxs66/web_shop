package com.yyy.springboot.mapper;

import com.yyy.springboot.entitys.Cart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/5/4 21:56
 * @Version 1.0
 **/
public interface CartMapper {

    @Select("<script>" +
            "select p.id productId,p.name productName,p.image,pr.amount from product p " +
            "left join product_repertory pr on p.id= pr.product_id " +
            "left join product_repertory_mid prm on pr.id=prm.product_repertory_id " +
            "where  p.id=#{productId} and prm.psd_id in" +
            "<foreach collection='psdIds' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "group by pr.id having count(pr.id)=#{psdIdsLength}" +
            "</script>"
    )
    Cart selectCartByPsdIds(@Param("productId") Long productId, @Param("psdIds") List<Long> psdIds, @Param("psdIdsLength")Integer psdIdsLength);
}
