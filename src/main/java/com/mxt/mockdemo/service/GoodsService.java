package com.mxt.mockdemo.service;

import com.mxt.mockdemo.entity.Goods;

import java.util.List;

/**
 * @Author Simple
 * @Create 2022/4/14 11:16
 */
public interface GoodsService {

    /**
     * 获取Owner的goods列表
     * @param owner
     * @param owner
     * @return
     */
    List<Goods> getGoodsByOwner(Long owner);

    /**
     * 获取goods信息
     * @param id
     * @return
     */
    Goods getGoodsById(int id);

}
