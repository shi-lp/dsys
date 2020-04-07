package com.dsys.simp.dao;

import com.dsys.simp.bean.SpuSpec;

public interface SpuSpecMapper {
    int deleteByPrimaryKey(String id);

    int insert(SpuSpec record);

    int insertSelective(SpuSpec record);

    SpuSpec selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SpuSpec record);

    int updateByPrimaryKey(SpuSpec record);
}