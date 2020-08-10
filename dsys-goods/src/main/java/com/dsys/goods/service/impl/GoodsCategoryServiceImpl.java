package com.dsys.goods.service.impl;

import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.bean.goods.GoodsCategory1;
import com.dsys.api.bean.goods.GoodsCategory2;
import com.dsys.api.bean.goods.GoodsCategory3;
import com.dsys.api.service.goods.IGoodsCategory1Service;
import com.dsys.api.service.goods.IGoodsCategory2Service;
import com.dsys.api.service.goods.IGoodsCategory3Service;
import com.dsys.api.service.goods.IGoodsCategoryService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.util.Constants;
import java.util.ArrayList;
import java.util.List;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: GoodsCategoryServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 0:59
 */
@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService{
    
    
    @Autowired
    private IGoodsCategory1Service goodsCategory1Service;
    
    @Autowired
    private IGoodsCategory2Service goodsCategory2Service;
    
    @Autowired
    private IGoodsCategory3Service goodsCategory3Service;
    
    @Reference
    private IUidService uidService;
    
    @Override
    public boolean addGoodsCategory (GoodsCategory goodsCategory){
        boolean save = false;
        if(Constants.CATE_LEVEL_ONE.equals(goodsCategory.getLevelNo())){
            GoodsCategory1 goodsCategory1 = (GoodsCategory1)goodsCategory;
            goodsCategory1.setId(uidService.getUid());
            save = goodsCategory1Service.save(goodsCategory1);
        }else if(Constants.CATE_LEVEL_TWO.equals(goodsCategory.getLevelNo())){
            GoodsCategory2 goodsCategory2 = (GoodsCategory2)goodsCategory;
            goodsCategory2.setId(uidService.getUid());
            save = goodsCategory2Service.save(goodsCategory2);
        }else if(Constants.CATE_LEVEL_TWO.equals(goodsCategory.getLevelNo())){
            GoodsCategory3 goodsCategory3 = (GoodsCategory3)goodsCategory;
            goodsCategory3.setId(uidService.getUid());
            save = goodsCategory3Service.save(goodsCategory3);
        }
        return save;
    }
    
    @Override
    public List<GoodsCategory> getCateList (String levelNo,String parentCode){
        List<GoodsCategory> categoryList = new ArrayList<>();
        if(Constants.CATE_LEVEL_ONE.equals(levelNo)){
            categoryList = goodsCategory1Service.getCateList();
        }else if(Constants.CATE_LEVEL_TWO.equals(levelNo)){
            categoryList = goodsCategory2Service.getCateList(parentCode);
        }else if(Constants.CATE_LEVEL_TWO.equals(levelNo)){
            categoryList = goodsCategory3Service.getCateList(parentCode);
        }
        return categoryList;
    }
    
    @Override
    public String getCodeByLevel (String levelNo,String parentCode){
        String code = "";
        if(Constants.CATE_LEVEL_ONE.equals(levelNo)){
            code = goodsCategory1Service.getMaxCode();
        }else if(Constants.CATE_LEVEL_TWO.equals(levelNo)){
            code = goodsCategory2Service.getMaxCode(parentCode);
        }else if(Constants.CATE_LEVEL_TWO.equals(levelNo)){
            code = goodsCategory3Service.getMaxCode(parentCode);
        }
        return null;
    }
}
