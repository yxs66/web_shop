package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.ProductRepertory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;

public interface  ProductRepertoryMapper extends BaseMapper<ProductRepertory> {

//    @Select("select pr.id,pr.product_id,pr.amount,pr.num from product_repertory pr " +
//            "where pr.id=(select prm.product_repertory_id from product_repertory_mid prm " +
//            "where prm.psd_id in (1,2) " +
//            "group by prm.product_repertory_id " +
//            "having count(prm.product_repertory_id)=2)")

    @Select({
            "<script>",
            "select pr.id,pr.product_id,pr.amount,pr.num from product_repertory pr ",
            "where pr.id=(select prm.product_repertory_id from product_repertory_mid prm ",
                        "where prm.psd_id in",
                            "<foreach collection='psdIds' item='id' open='(' separator=',' close=')'>",
                            "#{id}",
                            "</foreach>",
                        "group by prm.product_repertory_id ",
                        "having count(prm.product_repertory_id)=2)",
            "</script>"
    })
    public ProductRepertory selectProductRepertoryByPsdIds(@Param("psdIds") List<Integer> psdIds);
}
