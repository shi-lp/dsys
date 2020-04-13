package com.dsys.goods.mapper;

import com.dsys.simp.bean.GoodsBrand;

public interface GoodsBrandMapper {
    int deleteByPrimaryKey (String id);

    int insert (GoodsBrand record);

    int insertSelective (GoodsBrand record);

    GoodsBrand selectByPrimaryKey (String id);

    int updateByPrimaryKeySelective (GoodsBrand record);

    int updateByPrimaryKey (GoodsBrand record);
}