package com.dsys.home.controller;

import com.dsys.api.bean.home.HomeBanner;
import com.dsys.api.service.home.IHomeBannerService;
import com.dsys.common.sdk.response.RenderResponse;
import java.util.HashMap;
import java.util.Map;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Title: MobileHomeController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/14 16:28
 */
@RestController(value = "/home")
public class MobileHomeController{
    
    @Reference
    private IHomeBannerService homeBannerService;

    @GetMapping(value = "/indexs")
    public RenderResponse getHome(){
        Map<String,Object> render = new HashMap<>();
        List<HomeBanner> homeBannerList = homeBannerService.list();
        render.put("banner",homeBannerList);
        return RenderResponse.success(render);
    }
}
