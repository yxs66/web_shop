package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.yyy.springboot.config.Update;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_address")
@Accessors(chain = true)
public class UserAddress {
    @TableId(type = IdType.AUTO)
    private Long id;//用户地址id

    @TableField("user_openid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串
    private Long userOpenId;//用户id

    @Length(min = 2,max = 6,message = "UserAddress.name长度需要在2和6之间")
    @NotBlank(message = "UserAddress.name.null")
    private String name;//名字

    @Length(min = 5,max = 11,message = "UserAddress.phone长度需要在5和11之间")
    @NotBlank(message = "UserAddress.phone.null")
    private String phone;//手机号

    @Length(min = 5,max = 100,message = "UserAddress.address长度需要在5和100之间")
    @NotBlank(message = "UserAddress.address.null")
    private String address;//地址

    @TableField("def")
    @Max(value = 1,message = "UserAddress.def需要小于等于1")
    private Byte def;//默认地址

}
