package com.dsys.goods.controller;

import com.dsys.api.bean.goods.GoodsSKU;
import com.dsys.api.service.goods.IGoodsSKUService;
import com.dsys.common.sdk.response.RenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: GoodsSKUController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:32
 */
@RestController
@RequestMapping("/goods")
public class GoodsSKUController{
    
    @Autowired
    private IGoodsSKUService goodsSKUService;
    
    /**     
     * @discription ${在此输入一句话描述此文件的作用}
     * @author shilp       
     * @created 2020/7/15  9:34
     * @Param 
     * @Return 
    */
    @PostMapping(value = "/SKUs")
    public RenderResponse addGoodsSKU(@RequestBody GoodsSKU goodsSKU){
        boolean b = goodsSKUService.addSKU(goodsSKU);
        return RenderResponse.success();
    }
}
