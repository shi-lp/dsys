package com.dsys.goods.controller;

import com.dsys.api.bean.goods.GoodsSafeguard;
import com.dsys.api.service.goods.IGoodsSafeguardService;
import com.dsys.common.sdk.response.RenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: GoodsSafeguardController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:53re
 */
@RestController
@RequestMapping("/goods")
public class GoodsSafeguardController{
    
    @Autowired
    private IGoodsSafeguardService safeguardService;
    
    @PostMapping(value = "/safeguards")
    public RenderResponse addSafeguard(@RequestBody GoodsSafeguard goodsSafeguard){
        return RenderResponse.success();
    }
}
