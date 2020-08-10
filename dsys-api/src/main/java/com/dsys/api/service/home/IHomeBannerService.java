package com.dsys.api.service.home;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.home.HomeBanner;
import java.util.List;

/**
 * Title: IhomeBannerService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/14 16:50
 */
public interface IHomeBannerService extends IService<HomeBanner>{
    
    public boolean addBanner (HomeBanner homeBanner);
    
    public List<HomeBanner> getBanner (HomeBanner homeBanner);
}
