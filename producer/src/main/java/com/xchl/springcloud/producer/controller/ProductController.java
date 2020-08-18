package com.xchl.springcloud.producer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xchl.springcloud.model.Goods;
import com.xchl.springcloud.producer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    GoodsService goodsService;

    /* 插入数据，@RequestBody接收商品数据，只能用post请求才能从body中获取数据
     注意：方法返回基本数据类型Integer、int、Boolean、boolean时，
     @RestController会将结果会变成<Boolean>true</Boolean>
     可改为字符串"true"、"123" */
    @RequestMapping("goods/insert")
    public String insert(HttpServletRequest request, @RequestBody Goods goods) {
        goods.setRemark(request.getLocalPort() + "");
        int insert = goodsService.insert(goods);
        return (insert > 0) + "";
    }
    /*服务熔断，fallbackMethod指定方法出现异常时，将要调用的方法
    处理方法的返回值和参数要一致
    HystrixCommandProperties类中定义了各种属性
    */
    @HystrixCommand(commandProperties ={
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50")
    } ,fallbackMethod = "listFallback")
    @RequestMapping("goods/list")
    public List<Goods> list(HttpServletRequest request) {
        return goodsService.list();
    }
    public List<Goods> listFallback(HttpServletRequest request) {
        ArrayList<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Goods("listFallback 请求失败！",1));
        return goodsList;
    }

    /*服务熔断，fallbackMethod指定方法出现异常时，将要调用的方法
    处理方法的返回值和参数要一致
    HystrixCommandProperties类中定义了各种属性
    */
    @HystrixCommand(commandProperties ={
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50")
    } ,fallbackMethod = "getFallback")
    @RequestMapping("goods/get/{goodsId}")
    public Goods get(HttpServletRequest request, @PathVariable Long goodsId) {
        Goods goods = goodsService.getGoodsById(goodsId);
        goods.getGoodsName();
        if(goods==null){
            throw new RuntimeException("get方法查询ID="+goodsId+"为空");
        }
        //附加端口号，方便查看使用哪个服务提供者
        goods.setGoodsName(goods.getGoodsName() + request.getLocalPort());
        return goods;
    }
    //get方法的服务熔断方法
    public Goods getFallback(HttpServletRequest request, @PathVariable Long goodsId) {
        return new Goods("ID="+goodsId+"查询结果为空！@HystrixCommand",1);
    }

    @RequestMapping("goods/name/{goodsName}")
    public Goods getGoodsByName(HttpServletRequest request, @PathVariable String goodsName) {
        Goods goods = goodsService.getGoodsByName(goodsName);
        if(goods!=null)
            goods.setGoodsName(goods.getGoodsName() + request.getLocalPort());
        return goods;
    }

    /**
     * 服务提供者
     */
    @RequestMapping("services/list")
    public List<String> services() {
        //fetch-registry: true时，才可获取服务
        List<String> services = discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    /**
     * 服务提供者
     */
    @RequestMapping("instances/list")
    public List<ServiceInstance> instances() {
        //fetch-registry: true时，才可获取服务
        List<ServiceInstance> instances = discoveryClient.getInstances("producer");
        System.out.println(instances);
        return instances;
    }

}
