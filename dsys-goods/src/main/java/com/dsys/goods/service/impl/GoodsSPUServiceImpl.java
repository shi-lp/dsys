package com.dsys.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.goods.GoodsSpu;
import com.dsys.api.service.goods.IGoodsSpuService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.goods.mapper.GoodsSpuMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: GoodsSPUServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 9:46
 */
@Service
public class GoodsSPUServiceImpl extends ServiceImpl<GoodsSpuMapper,GoodsSpu> implements IGoodsSpuService{
    
    @Reference
    private IUidService uidService;
    
    @Autowired
    private GoodsSpuMapper goodsSpuMapper;
}
