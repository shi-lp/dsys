package com.dsys.goods.controller;

import com.dsys.api.bean.goods.Shop;
import com.dsys.api.service.goods.IShopService;
import com.dsys.common.sdk.response.RenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: ShopController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:57
 */
@RestController
@RequestMapping("/goods/shop")
public class ShopController{
    
    @Autowired
    private IShopService shopService;
    
    @PostMapping("/shops")
    public RenderResponse addShop(@RequestBody Shop shop){
        boolean b = shopService.addShop(shop);
        return RenderResponse.success();
    }
}
