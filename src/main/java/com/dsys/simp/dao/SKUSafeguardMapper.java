package com.dsys.simp.dao;

import com.dsys.simp.bean.SKUSafeguard;

public interface SKUSafeguardMapper {
    int deleteByPrimaryKey(String id);

    int insert(SKUSafeguard record);

    int insertSelective(SKUSafeguard record);

    SKUSafeguard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SKUSafeguard record);

    int updateByPrimaryKey(SKUSafeguard record);
}