package com.yyy.springboot.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 商品库存回滚redis实体类
 * @Author yyy
 * @CreateDate 2021/6/9
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RepertoryDifference {
    private Long productRepertoryId; //库存表id
    private Integer num; // 库存数量
}
