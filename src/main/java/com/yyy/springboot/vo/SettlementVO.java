package com.yyy.springboot.vo;

import com.yyy.springboot.entitys.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description 结算页面
 * @Author yyy
 * @CreateDate 2021/6/11
 * @Version 1.0
 */
@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SettlementVO {

    private BigDecimal productTotalAmount;//商品总价格

    private BigDecimal discount;//优惠

    private BigDecimal carriage;//运费

    private BigDecimal amount;//支付金额

    private UserAddress userAddress;//用户地址

    private List<SettlementProductVO> settlementProductVOS; //商品信息

}
