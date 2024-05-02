package com.javatechie.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public NewTopic newTopic(){
        return new NewTopic("javatechie-demo-1", 3,(short) 1);
    }
}
