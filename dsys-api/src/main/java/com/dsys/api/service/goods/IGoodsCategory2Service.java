package com.dsys.api.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.bean.goods.GoodsCategory2;
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
public interface IGoodsCategory2Service extends IService<GoodsCategory2>{
    
    public boolean addGoodsCategory (GoodsCategory2 goodsCategory);
    
    public List<GoodsCategory> getCateList (String parentCode);
    
    public String getMaxCode (String parentCode);
}
