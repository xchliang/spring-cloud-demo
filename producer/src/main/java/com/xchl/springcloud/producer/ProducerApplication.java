package com.xchl.springcloud.producer;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;


/*
@EnableEurekaClient //或者@EnableDiscoveryClient
@EnableHystrix //或者@EnableCircuitBreaker 开启hystrix服务熔断机制
@SpringCloudApplication包含了：@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
 */
@SpringCloudApplication
@MapperScan("com.xchl.springcloud.producer.mapper")
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
