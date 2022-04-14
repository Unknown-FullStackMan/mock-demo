package com.mxt.mockdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxt.mockdemo.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Simple
 * @Create 2022/4/14 14:17
 */
@Mapper
public interface GoodsDao extends BaseMapper<Goods> {

    /**
     * 根据Owner查询goods信息
     * @param owner
     * @return
     */
    List<Goods> getGoodsByOwner(@Param("owner") Long owner);

    /**
     * 根据ID查询goods信息
     * @return
     */
    Goods getGoodsById(@Param("id") int id);
}
