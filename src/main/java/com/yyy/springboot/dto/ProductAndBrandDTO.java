package com.yyy.springboot.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yyy.springboot.entitys.Product;
import com.yyy.springboot.entitys.ProductBrand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //get set toString
@NoArgsConstructor
public class ProductAndBrandDTO {
    private List<ProductBrand> productBrands;
    private List<Product> products;
}
