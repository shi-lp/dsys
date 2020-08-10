package com.dsys.api.service.goods;

import com.dsys.api.bean.goods.GoodsCategory;
import java.util.List;

/**
 * Title: IGoodsCategoryService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 商品分类
 * @created 2020/5/19 16:23
 */
public interface IGoodsCategoryService{
    
    public boolean addGoodsCategory (GoodsCategory goodsCategory);
    
    public List<GoodsCategory> getCateList(String levelNo,String parentCode);
    
    public String getCodeByLevel (String levelNo,String parentCode);
}
