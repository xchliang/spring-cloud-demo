package com.xchl.consumer.serviceClient;

import com.xchl.springcloud.model.Goods;
import org.springframework.stereotype.Component;

import java.util.List;

//Feign熔断处理类实现客户端接口，用@Component注解bean加入到容器中，
// 否则无法找到熔断处理类的实例，报错
@Component
public class GoodsClientServiceFallback implements GoodsClientService{
    @Override
    public String insert(Goods goods) {
        return null;
    }

    @Override
    public List<Goods> list() {
        return null;
    }

    @Override
    public Goods getGoods(Long goodsId) {
        return new Goods("查找ID="+goodsId+"为空！Feign客户端熔断！",1);
    }

    @Override
    public Goods getGoodsByName(String goodsName) {
        return null;
    }
}
