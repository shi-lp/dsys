package com.dsys.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.bean.goods.GoodsCategory3;
import com.dsys.api.service.goods.IGoodsCategory3Service;
import com.dsys.common.util.StringUtils;
import com.dsys.goods.mapper.GoodsCategory3Mapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: GoodsCategory1ServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 11:27
 */
@Service
public class GoodsCategory3ServiceImpl extends ServiceImpl<GoodsCategory3Mapper, GoodsCategory3> implements IGoodsCategory3Service{
    
    @Autowired
    private GoodsCategory3Mapper goodsCategory3Mapper;
    
    @Override
    public boolean addGoodsCategory (GoodsCategory3 goodsCategory){
        int insert = goodsCategory3Mapper.insert(goodsCategory);
        return StringUtils.insertReturn(insert);
    }
    
    @Override
    public List<GoodsCategory> getCateList (String parentCode){
        return goodsCategory3Mapper.selectCateList(parentCode);
    }
    
    @Override
    public String getMaxCode (String parentCode){
        return null;
    }
}
