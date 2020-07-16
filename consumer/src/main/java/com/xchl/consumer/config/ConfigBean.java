package com.xchl.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    //  @LoadBalanced 负载均衡，调用IP地址可以变成应用名称spring.application.name
    // http://xx.xx.xx.xx:8080改为http://producerA
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
