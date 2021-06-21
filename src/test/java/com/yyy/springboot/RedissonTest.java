package com.yyy.springboot;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class RedissonTest {

    @Autowired
    RedissonClient redissonClient;

    public int index=0;
    @Test
    public void test() throws InterruptedException {
        long old = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(1000);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RLock lock = redissonClient.getLock("1");
                lock.lock();
                try {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "----" + (index++));
                } catch (Exception e) {
                    System.out.println(e);
                }finally {
                    lock.unlock();
                    countDownLatch.countDown();
                }
            }
        };
        for (int i = 0; i < 1000; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();
        System.out.println(index);
        System.out.println(System.currentTimeMillis()-old);
    }
}
