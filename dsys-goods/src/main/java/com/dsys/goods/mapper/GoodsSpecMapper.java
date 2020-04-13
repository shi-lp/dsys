package com.dsys.goods.mapper;

import com.dsys.simp.bean.GoodsSpec;

public interface GoodsSpecMapper {
    int deleteByPrimaryKey (String id);

    int insert (GoodsSpec record);

    int insertSelective (GoodsSpec record);

    GoodsSpec selectByPrimaryKey (String id);

    int updateByPrimaryKeySelective (GoodsSpec record);

    int updateByPrimaryKey (GoodsSpec record);
}