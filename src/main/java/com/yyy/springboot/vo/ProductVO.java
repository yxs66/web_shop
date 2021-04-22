package com.yyy.springboot.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yyy.springboot.dto.ProductSpecificationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/17 23:37
 * @Version 1.0
 **/
@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

        @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
        @JsonSerialize(using = ToStringSerializer.class)
        private Long id;//商品编号

        @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
        private String name;//商品名称

        @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
        private String brandName;//品牌名称

        @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
        private String typeName;//类型名称

        @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
        private String image;

        @JsonInclude(JsonInclude.Include.NON_NULL)//空字段不返回
        List<ProductRepertoryVO> productRepertoryVOs;

}
