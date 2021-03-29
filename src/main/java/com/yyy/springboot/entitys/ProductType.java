package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_type")
public class ProductType {
    @TableId(type = IdType.AUTO)
    private Long id;//分类编号

    @NotBlank(message = "productBrand.name.null")
    @Length(min = 1,max = 20,message = "name长度需要在1和20之间")
    private String name;//分类名称
}
