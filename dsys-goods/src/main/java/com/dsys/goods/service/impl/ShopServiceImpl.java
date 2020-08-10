package com.dsys.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.goods.Shop;
import com.dsys.api.service.goods.IShopService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.goods.mapper.ShopMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: ShopServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:48
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService{
    @Reference
    private IUidService uidService;
    
    @Autowired
    private ShopMapper shopMapper;
    
    @Override
    public boolean addShop (Shop shop){
        shop.setId(uidService.getUid());
        shopMapper.insert(shop);
        return false;
    }
}
