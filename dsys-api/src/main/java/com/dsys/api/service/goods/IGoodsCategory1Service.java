package com.dsys.api.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.bean.goods.GoodsCategory1;
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
public interface IGoodsCategory1Service extends IService<GoodsCategory1>{
    
    public boolean addGoodsCategory (GoodsCategory1 goodsCategory);
    
    public List<GoodsCategory> getCateList ();
    
    public String getMaxCode ();
}
