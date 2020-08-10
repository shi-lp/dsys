package com.dsys.api.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.bean.goods.GoodsCategory3;
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
public interface IGoodsCategory3Service extends IService<GoodsCategory3>{
    
    public boolean addGoodsCategory (GoodsCategory3 goodsCategory);
    
    public List<GoodsCategory> getCateList (String parentCode);
    
    public String getMaxCode (String parentCode);
}
