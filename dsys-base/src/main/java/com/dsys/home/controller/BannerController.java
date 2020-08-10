package com.dsys.home.controller;

import com.dsys.api.bean.home.HomeBanner;
import com.dsys.api.service.home.IHomeBannerService;
import com.dsys.common.sdk.response.RenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Title: BannerController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:03
 */
@RestController
@RequestMapping(value = "/home")
public class BannerController{
    
    @Autowired
    private IHomeBannerService homeBannerService;
    
    /**
     * @discription 轮播图维护新增
     * @author shilp
     * @created 2020/7/15  9:40
     * @Param
     * @Return
    */
    @PostMapping(value = "/banners")
    public RenderResponse addBanner(@RequestBody HomeBanner homeBanner){
        boolean b = homeBannerService.addBanner(homeBanner);
        if(b) {
            return RenderResponse.success();
        }
        return RenderResponse.fail("新增轮播图失败");
    }
    
    @GetMapping(value = "/banners")
    public RenderResponse getBanner(@RequestBody HomeBanner homeBanner){
        List<HomeBanner> homeBannerList = homeBannerService.getBanner(homeBanner);
        return RenderResponse.success(homeBannerList);
    }
}
