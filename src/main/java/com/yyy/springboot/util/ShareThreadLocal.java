package com.yyy.springboot.util;

import org.springframework.stereotype.Component;

/**
 * @Deacription 定义一个线程域，存放登录用户id
 * @Author yxs
 * @Date 2021/4/25 6:21
 * @Version 1.0
 **/
@Component
public class ShareThreadLocal<T> {
    private final ThreadLocal<T> tl = new ThreadLocal<>();

    public void set(T userId) {
        tl.set(userId);
    }

    public T get() {
        return tl.get();
    }

    public void remove() {
        tl.remove();
    }
}
