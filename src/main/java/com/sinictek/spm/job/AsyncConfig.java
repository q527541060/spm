package com.sinictek.spm.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/***
 *  异步托管多线程配置类 AsyncConfig
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    private int corePoolSize= 10;
    private int maxPoolSize= 200;
    private int queueCapacity= 10;

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return  executor;
    }
}
