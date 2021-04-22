package com.yyy.springboot.dto;

import com.yyy.springboot.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/4/14 20:14
 * @Version 1.0
 **/
@Data //get set toString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {
    private Long current;
    private Long size;
    private Long pages;
    private Long total;
    private List<User> userList;
}
