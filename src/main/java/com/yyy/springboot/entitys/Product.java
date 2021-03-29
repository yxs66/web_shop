package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;//商品编号
    private String name;//商品名称
    @TableField("brand_id")
    private Integer brandId;//品牌编号
    private Integer type;//类型编号
}
