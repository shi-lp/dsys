package com.dsys.goods.controller;

import com.dsys.api.bean.goods.GoodsBrand;
import com.dsys.api.service.goods.IGoodsBrandService;
import com.dsys.common.sdk.response.RenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: GoodsBrandController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:54
 */
@RestController
@RequestMapping("/goods")
public class GoodsBrandController{
    
    @Autowired
    private IGoodsBrandService goodsBrandService;
    
    @PostMapping(value = "/brands")
    public RenderResponse addBeand(@RequestBody GoodsBrand goodsBrand){
        goodsBrandService.addBrand(goodsBrand);
        return RenderResponse.success();
    }
}
