package com.yyy.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyy.springboot.dto.UserDTO;
import com.yyy.springboot.entitys.User;
import com.yyy.springboot.mapper.UserMapper;
import com.yyy.springboot.service.UserService;
import com.yyy.springboot.util.ShareThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RefreshScope
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Value("${token_cache_time}")
    private int tokenCacheTime;//单位分钟

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ShareThreadLocal<Long> shareThreadLocal;

    @Override
    public UserDTO selectUsers(long current, long size) {
        IPage<User> userIPage = userMapper.selectPage(new Page<>(current, size), null);
        UserDTO userDTO = new UserDTO()
                .setCurrent(userIPage.getCurrent())
                .setPages(userIPage.getPages())
                .setSize(userIPage.getSize())
                .setTotal(userIPage.getTotal())
                .setUserList(userIPage.getRecords());
        return userDTO;
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void insertUser(User user) {
        user.setId(null);
        userMapper.insert(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void updateUserById(User user) {
        user.setId(shareThreadLocal.get());
        user.setMember(null);
        userMapper.updateById(user);
    }

    @Override
    public List<User> selectUserByUsernames(String username) {
        return userMapper.selectList(new QueryWrapper<User>().like("username", username));
    }

    @Override
    public Map<String, String> login(Map<String, String> map, HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token != null)
            redisTemplate.delete("user_"+token);
        User user = userMapper.selectOne(new QueryWrapper<User>().allEq(map));
        HashMap<String, String> hashMap = new HashMap<>();
        if (!ObjectUtils.isEmpty(user)) {
            String uuid = UUID.randomUUID().toString();
            hashMap.put("token", uuid);
            redisTemplate.opsForValue().set("user_" + uuid, user,Duration.ofMinutes(tokenCacheTime));
        }
        return hashMap;
    }

    public User getInfo(String token) {
        return (User) redisTemplate.opsForValue().get("user_" + token);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token != null)
            redisTemplate.delete("user_"+token);
    }
}
