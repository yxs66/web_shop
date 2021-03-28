package com.yyy.springboot.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import sun.management.MethodInfo;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data //get set toString
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    //ASSIGN_ID:IdentifierGenerator.nextId 雪花算法  ASSIGN_UUID:IdentifierGenerator.nextUUID  UUID
    @TableId(value = "openid",type = IdType.ASSIGN_ID)
    private Long id;
    @NotBlank(message = "user.name.null") //用于controller传参验证 ，表示该字段不能为null或''
    private String name;
    private String phone;
    private String address;
    private LocalDate birthday;
}
