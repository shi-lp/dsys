package com.dsys.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.bean.goods.GoodsCategory1;
import com.dsys.api.service.goods.IGoodsCategory1Service;
import com.dsys.common.util.StringUtils;
import com.dsys.goods.mapper.GoodsCategory1Mapper;
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
public class GoodsCategory1ServiceImpl extends ServiceImpl<GoodsCategory1Mapper, GoodsCategory1> implements IGoodsCategory1Service{
    
    @Autowired
    private GoodsCategory1Mapper goodsCategory1Mapper;
    
    @Override
    public boolean addGoodsCategory (GoodsCategory1 goodsCategory){
        int insert = goodsCategory1Mapper.insert(goodsCategory);
        return StringUtils.insertReturn(insert);
    }
    
    @Override
    public List<GoodsCategory> getCateList (){
        
        return goodsCategory1Mapper.selectCateList();
    }
    
    @Override
    public String getMaxCode (){
        return null;
    }
}
