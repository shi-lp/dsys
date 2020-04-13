package com.dsys.goods.mapper;

import com.dsys.simp.bean.SpecValue;

public interface SpecValueMapper {
    int deleteByPrimaryKey (String id);

    int insert (SpecValue record);

    int insertSelective (SpecValue record);

    SpecValue selectByPrimaryKey (String id);

    int updateByPrimaryKeySelective (SpecValue record);

    int updateByPrimaryKey (SpecValue record);
}