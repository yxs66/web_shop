package com.yyy.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Description 配置线程池任务调度器的线程池
 * @Author yyy
 * @CreateDate 2021/6/10
 * @Version 1.0
 */
@Configuration
public class ThreadPoolTaskSchedulerConfig {

    @Value("${task_scheduler.pool_size}")
    private Integer poolSize = 10;

    @Value("${task_scheduler.thread_name_prefix}")
    private String prefix = "task-";

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(poolSize);
        threadPoolTaskScheduler.setThreadNamePrefix(prefix);
        return threadPoolTaskScheduler;
    }
}
