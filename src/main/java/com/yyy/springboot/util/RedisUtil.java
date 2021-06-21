package com.yyy.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @description: 操作Redis工具类
 * @author: yyy
 * @createDate: 2021/6/8
 * @version: 1.0
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key  键
     * @param time 时间(秒)
     * @return boolean
     * @Description 指定缓存失效时间
     * @Date 4:07 2021/6/8
     */
    public Boolean setExpireKey(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * @Description 指定缓存不失效
     * @Date 5:14 2021/6/10
     * @param key  键
     * @return java.lang.Boolean
     */
    public Boolean setPersistKey(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * @param key key 键 不能为null
     * @return long 时间(秒) 返回0代表为永久有效
     * @Description 根据key 获取过期时间
     * @Date 4:10 2021/6/8
     */
    public Long getExpireTime(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @param key key 键
     * @return java.lang.Boolean true 存在 false不存在
     * @Description 判断key是否存在
     * @Date 4:14 2021/6/8
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * @param key
     * @Description 删除一个缓存
     * @Date 4:22 2021/6/8
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * @param key
     * @Description 删除多个缓存
     * @Date 4:22 2021/6/8
     */
    public void dels(List<String> key) {
        redisTemplate.delete(key);
    }

    /**
     * @param key 键
     * @return java.lang.Object 值
     * @Description 普通缓存获取
     * @Date 4:19 2021/6/8
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @param key   键
     * @param value 值
     * @return boolean true成功 false失败
     * @Description 普通缓存放入
     * @Date 4:20 2021/6/8
     */
    public boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return boolean true成功 false 失败
     * @Description 普通缓存放入并设置时间
     * @Date 4:21 2021/6/8
     */
    public boolean set(String key, Object value, long time) {
        if (time > 0)
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        else
            set(key, value);
        return true;
    }

    /**
     * @param key
     * @param value
     * @return java.lang.Object
     * @Description 将给定 key 的值设为 value ，并返回 key 的旧值 (old value)，当 key 存在但不是字符串类型时，返回一个错误，当key不存在时，返回nil。
     * @Date 20:01 2021/6/9
     */
    public Object getSet(String key, Object value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }


    /**
     * LUA脚本 获取并删除
     */
    private static final String LUA_SCRIPT_GET_AND_DELETE =
            "local current = redis.call('get', KEYS[1]);\n" +
                    "if (current) then\n" +
                    "    redis.call('del', KEYS[1]);\n" +
                    "end\n" +
                    "return current;";

    public Object getDel(String key) {
        DefaultRedisScript<Object> redisScript = new DefaultRedisScript<>(LUA_SCRIPT_GET_AND_DELETE, Object.class);
        return redisTemplate.execute(redisScript, Collections.singletonList(key));
    }
}
