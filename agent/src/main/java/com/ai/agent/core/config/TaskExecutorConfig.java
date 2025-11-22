package com.ai.agent.core.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class TaskExecutorConfig {

    @Value("${job.pool_size}")
    private int poolSize;

    @Value("${job.max_pool_size}")
    private int maxPoolSize;

    @Value("${job.max_queue_size}")
    private int maxQueueSize;

    @Value("${job.thread_workers_prefix}")
    private String threadWorkersPrefix;

    @Bean(name = "agentExec")
    public ThreadPoolTaskExecutor llmExecutor() {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        // Set available threads for execution
        taskExecutor.setCorePoolSize(poolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);

        // Queue first architecture
        taskExecutor.setQueueCapacity(maxQueueSize);

        // For logging purposes
        taskExecutor.setThreadNamePrefix(threadWorkersPrefix);

        // Graceful shutdown
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);

        // Redirect it to the controller thread if busy
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // Return the executor with config
        taskExecutor.initialize();
        return taskExecutor;
    }
}
