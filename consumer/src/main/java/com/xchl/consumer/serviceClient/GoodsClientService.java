package com.xchl.consumer.serviceClient;

import com.xchl.springcloud.model.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/* @FeignClient
name指定微服务名称，用于服务发现，即服务端配置spring.application.name
name和value互为别名，用法相同
contextId：指定bean的名字，比如同一微服务下提供的商品、用户服务就可以使用不同的contextId
url属性一般用于调试程序，允许我们手动指定@FeignClient调用的地址。
*/
@FeignClient(name="producer-A", contextId="goodsClientService")
public interface GoodsClientService {

    @RequestMapping("goods/insert")
    public String insert(@RequestBody Goods goods);

    @RequestMapping("goods/list")
    public List<Goods> list();

    @RequestMapping("goods/get/{goodsId}")
    public Goods getGoods(@PathVariable Long goodsId);

    @RequestMapping("goods/name/{goodsName}")
    public Goods getGoodsByName(@PathVariable String goodsName);
}
