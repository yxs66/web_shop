package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//商品编号

    @Length(min = 1,max = 20,message = "Product.name长度需要在1和20之间")
    @NotBlank(message = "Product.name.null")
    private String name;//商品名称

    @TableField("brand_id")
    @NotNull(message = "Product.brandId.null")
    private Integer brandId;//品牌编号

    @TableField("type_id")
    @NotNull(message = "Product.typeId.null")
    private Integer typeId;//类型编号

    @NotBlank(message = "Product.image.null")
    private String image;

    @TableField("user_id")
    @NotNull(message = "Product.userId.null")
    @JsonIgnore
    private Long userId;
}
