package com.dsys.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.goods.GoodsSKU;
import com.dsys.api.service.goods.IGoodsSKUService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.util.StringUtils;
import com.dsys.goods.mapper.GoodsSKUMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: GoodsSKUServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:35
 */
@Service
public class GoodsSKUServiceImpl extends ServiceImpl<GoodsSKUMapper, GoodsSKU> implements IGoodsSKUService{
    
    @Reference
    private IUidService uidService;
    
    @Autowired
    private GoodsSKUMapper goodsSKUMapper;
    
    @Override
    public boolean addSKU (GoodsSKU goodsSKU){
        goodsSKU.setId(uidService.getUid());
        int insert = goodsSKUMapper.insert(goodsSKU);
        return StringUtils.insertReturn(insert);
    }
}
