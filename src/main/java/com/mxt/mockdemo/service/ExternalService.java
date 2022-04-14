package com.mxt.mockdemo.service;

import com.mxt.mockdemo.entity.Goods;

import java.util.List;

/**
 * @Author Simple
 * @Create 2022/4/14 13:23
 */
public interface ExternalService {
    /**
     * 执行方法
     * @return
     */
    Goods executeGoodsServiceMethod(int id);

    /**
     * 方法
     * @return
     */
    List<Goods> executeSelfMethod(int id);
}
