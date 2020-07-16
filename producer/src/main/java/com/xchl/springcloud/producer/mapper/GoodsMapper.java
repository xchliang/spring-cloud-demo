package com.xchl.springcloud.producer.mapper;

import com.xchl.springcloud.model.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// @Mapper 或者在启动类上使用@MapperScan注解
public interface GoodsMapper {
    int insert(Goods goods);

    @Select("SELECT * FROM goods WHERE id = #{id}")
    Goods getGoodsById(Long id);

    Goods getGoodsByName(String goodsName);

    List<Goods> list();

}
