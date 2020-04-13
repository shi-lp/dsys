package com.dsys.goods.mapper;

import com.dsys.simp.bean.SKUSpecValue;

public interface SKUSpecValueMapper {
    int deleteByPrimaryKey (String id);

    int insert (SKUSpecValue record);

    int insertSelective (SKUSpecValue record);

    SKUSpecValue selectByPrimaryKey (String id);

    int updateByPrimaryKeySelective (SKUSpecValue record);

    int updateByPrimaryKey (SKUSpecValue record);
}