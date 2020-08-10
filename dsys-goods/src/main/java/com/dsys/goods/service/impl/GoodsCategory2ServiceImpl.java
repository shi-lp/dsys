package com.dsys.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.bean.goods.GoodsCategory2;
import com.dsys.api.service.goods.IGoodsCategory2Service;
import com.dsys.common.util.StringUtils;
import com.dsys.goods.mapper.GoodsCategory2Mapper;
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
public class GoodsCategory2ServiceImpl extends ServiceImpl<GoodsCategory2Mapper, GoodsCategory2> implements IGoodsCategory2Service{
    
    @Autowired
    private GoodsCategory2Mapper goodsCategory2Mapper;
    
    @Override
    public boolean addGoodsCategory (GoodsCategory2 goodsCategory){
        int insert = goodsCategory2Mapper.insert(goodsCategory);
        return StringUtils.insertReturn(insert);
    }
    
    @Override
    public List<GoodsCategory> getCateList (String parentCode){
        return goodsCategory2Mapper.selectCateList(parentCode);
    }
    
    @Override
    public String getMaxCode (String parentCode){
        return null;
    }
}
