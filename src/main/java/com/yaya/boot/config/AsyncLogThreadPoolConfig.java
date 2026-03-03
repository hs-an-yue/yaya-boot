package com.yaya.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration //配置文件
@EnableAsync //开启异步
public class AsyncLogThreadPoolConfig {

    /**
     * 必须定义专门的线程池，避免与业务共用导致相互干扰
     * 注意:
     *  1. 线程池的基础配置，在初始配置时无论怎么计算，往往都不完美
     *  2. 建议在生产环境中通过 Spring Boot Actuator 监控线程池的 activeCount（活跃线程数）和 queueSize（当前队列长度）。如果队列长期处于半满状态，就需要考虑增加核心线程数了
     */
    @Bean("logExecutor")
    public Executor logExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //获取CPU核数
        int n = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(n);        // 核心线程数 和CPU核数相同
        executor.setMaxPoolSize(2*n);       // 最大线程数 CPU的2倍
        executor.setQueueCapacity(500);     // 队列大小根据内存调整,建议不要超过 2000
        executor.setThreadNamePrefix("LogAsync-");
        // 拒绝策略：队列满了由调用者线程执行（保证日志不丢失）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
