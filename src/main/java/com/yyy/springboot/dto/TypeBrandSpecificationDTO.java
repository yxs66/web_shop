package com.yyy.springboot.dto;


import com.yyy.springboot.entitys.ProductBrand;
import com.yyy.springboot.entitys.ProductSpecification;
import com.yyy.springboot.entitys.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TypeBrandSpecificationDTO {

    private List<ProductType> productTypes;

    private List<ProductBrand> productBrands;

    private List<ProductSpecification> productSpecifications;

}
