package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyy.springboot.config.Update;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import sun.management.MethodInfo;

import javax.validation.constraints.*;
import java.time.LocalDate;
/*
* @Null  被注释的元素必须为null
@NotNull  被注释的元素不能为null
@AssertTrue  被注释的元素必须为true
@AssertFalse  被注释的元素必须为false
@Min(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Size(max,min)  被注释的元素的大小必须在指定的范围内。
@Digits(integer,fraction)  被注释的元素必须是一个数字，其值必须在可接受的范围内
@Past  被注释的元素必须是一个过去的日期
@Future  被注释的元素必须是一个将来的日期
@Pattern(value) 被注释的元素必须符合指定的正则表达式。
@Email 被注释的元素必须是电子邮件地址
@Length 被注释的字符串的大小必须在指定的范围内
@NotEmpty  被注释的字符串必须非空
@Range  被注释的元素必须在合适的范围内
**/
@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    //ASSIGN_ID:IdentifierGenerator.nextId 雪花算法  ASSIGN_UUID:IdentifierGenerator.nextUUID  UUID
    @TableId(value = "openid",type = IdType.ASSIGN_ID)
    @NotNull(message = "user.id.null",groups = {Update.class})//groups用于@Validated指定组进行验证  不设置默认是Default组
    private Long id;
    @NotBlank(message = "user.name.null") //用于controller传参验证 ，表示该字段不能为null或''  @NotBlank只应用于字符串
    private String name;
    @NotBlank(message = "user.phone.null")
    private String phone;
    @NotBlank(message = "user.address.null")
    private String address;
//    @JsonFormat(pattern = "yyy/MM/dd")
    private LocalDate birthday;
}
