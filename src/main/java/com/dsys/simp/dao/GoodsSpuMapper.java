package com.dsys.simp.dao;

import com.dsys.simp.bean.GoodsSpu;

public interface GoodsSpuMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsSpu record);

    int insertSelective(GoodsSpu record);

    GoodsSpu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsSpu record);

    int updateByPrimaryKey(GoodsSpu record);
}