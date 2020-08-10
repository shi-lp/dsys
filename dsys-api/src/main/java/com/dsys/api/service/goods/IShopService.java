package com.dsys.api.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.goods.Shop;

/**
 * Title: IShopService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 商铺信息
 * @created 2020/5/19 16:31
 */
public interface IShopService extends IService<Shop>{
    
    public boolean addShop (Shop shop);
}
