package com.xchl.consumer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xchl.consumer.serviceClient.GoodsRestService;
import com.xchl.springcloud.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ConsumerRibbonController {
    /*
     * Ribbon负载均衡，默认轮询方式
     * restTemplate的bean上使用@LoadBalanced注解，启用Ribbon负载均衡
     * restTemplate通过应用名访问服务，会从服务列表中选择服务地址进行访问
     */
    @Autowired
    private RestTemplate restTemplate;

    //封装restTemplate调用接口方法，使用hystrix熔断
    @Autowired
    private GoodsRestService goodsRestService;

    static String URL_PREFIX = "http://producer-A";//product-A微服务应用名称spring.application.name

    @RequestMapping("consumer/goods/insert")
    public String insert(Goods goods) {
        //post提交请求goods对象
        return restTemplate.postForObject(URL_PREFIX+"/goods/insert", goods, String.class);
    }
    @RequestMapping("consumer/goods/list")
    public List<Goods> list() {
        return restTemplate.getForObject(URL_PREFIX + "/goods/list", List.class);
    }

    @RequestMapping("consumer/goods/get/{goodsId}")
    public Goods getGoods(@PathVariable Long goodsId) {
        return restTemplate.getForObject(URL_PREFIX + "/goods/get/" + goodsId, Goods.class);
    }

    @RequestMapping("consumer/goods/name/{goodsName}")
    public Goods getGoodsByName(HttpServletRequest request, @PathVariable String goodsName) {
        System.out.println("获取商品。。。");
        //封装接口，restTemplate调用服务接口
        Goods goods = goodsRestService.getGoodsByName(goodsName);
        try {
            System.out.println("获取商品："+new ObjectMapper().writeValueAsString(goods));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return goods;
    }


}
