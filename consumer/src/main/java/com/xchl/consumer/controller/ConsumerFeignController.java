package com.xchl.consumer.controller;

import com.xchl.consumer.serviceClient.GoodsClientService;
import com.xchl.springcloud.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ConsumerFeignController {
    @Autowired
    GoodsClientService goodsClientService;

    @RequestMapping("consumer/feign/goods/insert")
    public String insert(Goods goods) {
        return goodsClientService.insert(goods);
    }
    @RequestMapping("consumer/feign/goods/list")
    public List<Goods> list() {
        return goodsClientService.list();
    }
    @RequestMapping("consumer/feign/goods/get/{goodsId}")
    public Goods getGoods(@PathVariable Long goodsId) {
        return goodsClientService.getGoods(goodsId);
    }
    @RequestMapping("consumer/feign/goods/name/{goodsName}")
    public Goods getGoodsByName(@PathVariable String goodsName) {
        return goodsClientService.getGoodsByName(goodsName);
    }

}
