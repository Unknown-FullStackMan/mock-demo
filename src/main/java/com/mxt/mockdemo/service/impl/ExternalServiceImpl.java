package com.mxt.mockdemo.service.impl;

import com.mxt.mockdemo.entity.Goods;
import com.mxt.mockdemo.service.ExternalService;
import com.mxt.mockdemo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Simple
 * @Create 2022/4/14 13:24
 */
@Service
public class ExternalServiceImpl implements ExternalService {

    @Autowired
    private GoodsService goodsService;

    @Override
    public Goods executeGoodsServiceMethod(int id) {
        return goodsService.getGoodsById(id);
    }

    @Override
    public List<Goods> executeSelfMethod(int id) {
        return goodsService.getGoodsByOwner(Long.valueOf(id));
    }
}
