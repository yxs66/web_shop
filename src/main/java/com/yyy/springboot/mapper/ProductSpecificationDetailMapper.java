package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitys.ProductSpecificationDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductSpecificationDetailMapper extends BaseMapper<ProductSpecificationDetail> {

    @Select("select * from product_specification_detail where ps_id=#{id}")
    @Results({
            @Result(column = "ps_id",property = "productSpecificationId")
    })
    List<ProductSpecificationDetail> selectProductSpecificationDetailByPs_id(@Param("id") Long ps_id);

    @Select({
            "<script>",
                "select",
                "psd.name",
                "from product_specification_detail psd",
                "where id in",
                "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                "</foreach>",
            "</script>"
    })
    List<String> selectProductSpecificationDetailNameByPsdId(@Param("ids") List<Long> ids);

    @Select({
            "<script>",
            "select psd.id,psd.name",
            "from product_specification_detail psd",
            "where psd.id in",
                "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
                "#{id}",
                "</foreach>",
            "</script>"
    })
    List<ProductSpecificationDetail> selectProductSpecificationDetailByPsdId(@Param("ids") List<Long> ids);
}
