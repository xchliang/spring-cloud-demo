package com.xchl.springcloud.producer.service.impl;

import com.xchl.springcloud.model.Goods;
import com.xchl.springcloud.producer.mapper.GoodsMapper;
import com.xchl.springcloud.producer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Goods getGoodsById(Long id) {
        return goodsMapper.getGoodsById(id);
    }

    @Override
    public Goods getGoodsByName(String goodsName) {
        return goodsMapper.getGoodsByName(goodsName);
    }

    @Override
    public int insert(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public List<Goods> list() {
        return goodsMapper.list();
    }

}
