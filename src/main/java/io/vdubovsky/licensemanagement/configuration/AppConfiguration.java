package io.vdubovsky.licensemanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfiguration {

    @Bean
    public ExecutorService inputProcessorExecutorService(){
        return Executors.newCachedThreadPool();
    }
}
