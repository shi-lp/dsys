package com.dsys.goods.mapper;

import com.dsys.simp.bean.Shop;

public interface ShopMapper {
    int deleteByPrimaryKey (String id);

    int insert (Shop record);

    int insertSelective (Shop record);

    Shop selectByPrimaryKey (String id);

    int updateByPrimaryKeySelective (Shop record);

    int updateByPrimaryKey (Shop record);
}