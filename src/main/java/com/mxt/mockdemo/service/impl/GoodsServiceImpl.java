package com.mxt.mockdemo.service.impl;

import com.mxt.mockdemo.dao.GoodsDao;
import com.mxt.mockdemo.entity.Goods;
import com.mxt.mockdemo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Simple
 * @Create 2022/4/14 13:13
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Goods> getGoodsByOwner(Long owner) {
        return goodsDao.getGoodsByOwner(owner);
    }

    @Override
    public Goods getGoodsById(int id) {
        return goodsDao.getGoodsById(id);
    }
}
