package com.yyy.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author yyy
 * @CreateDate 2021/6/10
 * @Version 1.0
 */
@SpringBootTest
@EnableScheduling
public class ScheduleTest {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Test
    public void test(){
//        threadPoolTaskScheduler.schedule(()->{
//            System.out.println(new Date());
//        },triggerContext ->{
////            Date date = new CronTrigger("0/5 * * * * ?").nextExecutionTime(triggerContext);
//            Date from = Date.from(LocalDateTime.now().plusSeconds(10).atZone(ZoneId.systemDefault()).toInstant());
//            System.out.println("-----------"+from);
//            return from;
//        });
        threadPoolTaskScheduler.setPoolSize(1);
        threadPoolTaskScheduler.schedule(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date());

        }, Date.from(LocalDateTime.now().plusSeconds(10).atZone(ZoneId.systemDefault()).toInstant()));
        threadPoolTaskScheduler.schedule(() -> {
            System.out.println(new Date()+"---");
        }, Date.from(LocalDateTime.now().plusSeconds(10).atZone(ZoneId.systemDefault()).toInstant()));

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusSeconds(10);
        Date from = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(now);
        System.out.println(from);
        System.out.println(localDateTime);
    }
}
