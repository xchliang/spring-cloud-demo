package com.xchl.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @EnableDiscoveryClient
 *  服务消费者也是 eureka的使用者，即eureka客户端
 *  使用@EnableEurekaClient或@EnableDiscoveryClient
 *  @EnableEurekaClient 适用于Eureka作注册中心
 *  @EnableDiscoveryClient 不但支持Eureka，还支持其它注册中心
 *
 * @EnableFeignClients
 *  启动fegin，扫描指定包下@FeignClient注解的类
 */
@SpringBootApplication(scanBasePackages={"com.xchl.consumer"})
@EnableDiscoveryClient
@EnableFeignClients("com.xchl.consumer.serviceClient")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
