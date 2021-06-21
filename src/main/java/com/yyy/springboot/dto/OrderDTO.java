package com.yyy.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description 下单前端传递的参数
 * @Author yyy
 * @CreateDate 2021/6/10
 * @Version 1.0
 */
@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    List<ProductSpecificationIdDTO> productSpecificationIdDTOS;
    Long userAddressId;
    Long orderId;
}
