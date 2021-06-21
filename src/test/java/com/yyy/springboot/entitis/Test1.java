package com.yyy.springboot.entitis;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Deacription
 * @Author yxs
 * @Date 2021/5/7 3:14
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("test")
public class Test1 {
    @TableId
    private Integer id;
    @TableField("user_id")
    private Integer userId;
    private String click;
    private Integer num;
}
