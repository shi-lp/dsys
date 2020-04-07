package com.dsys.simp.dao;

import com.dsys.simp.bean.GoodsSKU;

public interface GoodsSKUMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsSKU record);

    int insertSelective(GoodsSKU record);

    GoodsSKU selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsSKU record);

    int updateByPrimaryKey(GoodsSKU record);
}