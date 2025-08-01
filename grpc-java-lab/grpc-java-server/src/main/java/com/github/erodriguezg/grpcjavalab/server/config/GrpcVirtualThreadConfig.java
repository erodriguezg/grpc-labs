package com.github.erodriguezg.grpcjavalab.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class GrpcVirtualThreadConfig {

    @Bean
    public ExecutorService grpcServerExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

}