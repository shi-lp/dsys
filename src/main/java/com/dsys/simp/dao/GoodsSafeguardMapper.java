package com.dsys.simp.dao;

import com.dsys.simp.bean.GoodsSafeguard;

public interface GoodsSafeguardMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsSafeguard record);

    int insertSelective(GoodsSafeguard record);

    GoodsSafeguard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsSafeguard record);

    int updateByPrimaryKey(GoodsSafeguard record);
}