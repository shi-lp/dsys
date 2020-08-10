package com.dsys.cim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.cim.ReceiveAddress;
import com.dsys.api.service.cim.IReceiveAddressService;
import java.util.List;

import com.dsys.cim.mapper.ReceiveAddressMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Title: ReceiveAddressServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 收货地址实现类
 * @created 2020/4/13 20:28
 */
@Service
public abstract class ReceiveAddressServiceImpl extends ServiceImpl<ReceiveAddressMapper, ReceiveAddress> implements IReceiveAddressService{
    
    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;
    
    @Override
    public List<ReceiveAddress> getAddressByCustomerId (Long userId){
        QueryWrapper<ReceiveAddress> ew = new QueryWrapper<>();
        ew.eq("customerId",userId)
        .orderByAsc("defaultStatus");
//        List<ReceiveAddress> receiveAddressList = receiveAddressMapper.selectList(new Wrapper<ReceiveAddress>(){
//            @Override
//            public ReceiveAddress getEntity (){
//                return null;
//            }
//
//            @Override
//            public MergeSegments getExpression (){
//                return null;
//            }
//
//            @Override
//            public void clear (){
//
//            }
//
//            @Override
//            public String getSqlSegment (){
//                return null;
//            }
//        });
        List<ReceiveAddress> receiveAddressList = null;
        return receiveAddressList;
    }
    
}
