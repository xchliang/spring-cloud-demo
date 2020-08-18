package com.xchl.consumer.serviceClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xchl.springcloud.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoodsRestService{
    private static String URL_PREFIX = "http://producer-A";
    @Autowired
    private RestTemplate restTemplate;

    //在调用接口的方法上使用注解，熔断
    @HystrixCommand(fallbackMethod = "getGoodsFallback")
    public Goods getGoodsByName(String goodsName) {
        Goods g = restTemplate.getForObject(URL_PREFIX + "/goods/name/"+goodsName, Goods.class);
        if (g == null) {
            throw new RuntimeException("查找goodsName="+goodsName+"为空！异常！");
        }
        return g;
    }
    public Goods getGoodsFallback(String goodsName) {
        return new Goods("查找goodsName="+goodsName+"为空！restTemplate 消费者熔断！",1);
    }
}
