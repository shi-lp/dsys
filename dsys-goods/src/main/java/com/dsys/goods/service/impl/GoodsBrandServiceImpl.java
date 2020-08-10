package com.dsys.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.goods.GoodsBrand;
import com.dsys.api.service.goods.IGoodsBrandService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.util.StringUtils;
import com.dsys.goods.mapper.GoodsBrandMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: GoodsBrandServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:42
 */
@Service
public class GoodsBrandServiceImpl extends ServiceImpl<GoodsBrandMapper, GoodsBrand> implements IGoodsBrandService{
    
    @Reference
    private IUidService uidService;
    
    @Autowired
    private GoodsBrandMapper goodsBrandMapper;
    
    @Override
    public boolean addBrand (GoodsBrand goodsBrand){
        goodsBrand.setId(uidService.getUid());
        int insert = goodsBrandMapper.insert(goodsBrand);
        return StringUtils.insertReturn(insert);
    }
}
