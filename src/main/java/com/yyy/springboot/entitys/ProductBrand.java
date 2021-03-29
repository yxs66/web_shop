package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("product_brand")
public class ProductBrand {
    @TableId(type = IdType.AUTO)
    private Long id;//品牌编号

    @Length(min = 1,max = 20,message = "productBrand.name长度需要在1和20之间")
    @NotBlank(message = "productBrand.name.null")
    private String name;//品牌名称

}
