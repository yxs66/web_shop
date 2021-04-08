package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.ProductRepertoryMid;
import org.apache.ibatis.annotations.Select;

public interface ProductRepertoryMidMapper extends BaseMapper<ProductRepertoryMid> {

    @Select("select count(*) from product_repertory pr " +
            "left join product_repertory_mid prm " +
            "on pr.id=prm.product_repertory_id " +
            "where pr.product_id=#{productId} and prm.psd_id is null")
    Integer selectProductRepertoryMidNullCountByProductId(Long productId);
}
