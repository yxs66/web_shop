package com.yyy.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyy.springboot.entitis.Test1;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper extends BaseMapper<Test1> {

    @Select("select id,user_id,click,count(click) as num from test group by user_id,click")
    List<Test1> select();
}
