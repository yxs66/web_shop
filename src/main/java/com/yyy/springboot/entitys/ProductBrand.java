package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yyy.springboot.config.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_brand")
public class ProductBrand {
    @TableId(type = IdType.AUTO)
    private Long id;//品牌编号

    @TableField("product_type_id")
    @NotNull(message = "ProductBrand.productTypeId.null")
    private Long productTypeId;//分类编号

    @Length(min = 1,max = 20,message = "ProductBrand.name长度需要在1和20之间")
    @NotBlank(message = "ProductBrand.name.null")
    private String name;//品牌名称



}
