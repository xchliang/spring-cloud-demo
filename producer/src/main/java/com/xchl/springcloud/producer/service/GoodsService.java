package com.xchl.springcloud.producer.service;

import com.xchl.springcloud.model.Goods;

import java.util.List;

public interface GoodsService {
    Goods getGoodsById(Long id);
    Goods getGoodsByName(String goodsName);
    int insert(Goods goods);
    List<Goods> list();
}
