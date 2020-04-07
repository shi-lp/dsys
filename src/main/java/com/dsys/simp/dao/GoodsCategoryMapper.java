package com.dsys.simp.dao;

import com.dsys.simp.bean.GoodsCategory;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);
}