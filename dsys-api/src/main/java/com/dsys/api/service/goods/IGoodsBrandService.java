package com.dsys.api.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.goods.GoodsBrand;

/**
 * Title: IGoodsBrandService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 商品品牌
 * @created 2020/5/19 16:23
 */
public interface IGoodsBrandService extends IService<GoodsBrand>{
    
    public boolean addBrand (GoodsBrand goodsBrand);
}
