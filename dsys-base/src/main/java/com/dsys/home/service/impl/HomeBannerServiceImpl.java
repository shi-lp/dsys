package com.dsys.home.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.home.HomeBanner;
import com.dsys.api.service.home.IHomeBannerService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.util.StringUtils;
import com.dsys.home.mapper.HomeBannerMapper;
import java.util.List;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: HomeBannerServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/14 16:52
 */
@Service
public class HomeBannerServiceImpl extends ServiceImpl<HomeBannerMapper, HomeBanner> implements IHomeBannerService{

    @Autowired
    private HomeBannerMapper homeBannerMapper;
    
    @Reference
    private IUidService uidService;
    
    @Override
    public boolean addBanner (HomeBanner homeBanner){
        homeBanner.setSId(uidService.getUid());
        int insert = homeBannerMapper.insert(homeBanner);
        return StringUtils.insertReturn(insert);
    }
    
    @Override
    public List<HomeBanner> getBanner (HomeBanner homeBanner){
        LambdaQueryWrapper<HomeBanner> queryWrapper = new LambdaQueryWrapper<>();
        List<HomeBanner> homeBanners = homeBannerMapper.selectList(queryWrapper);
        return homeBanners;
    }
}
