package com.dsys.cim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.cim.bean.ReceiveAddress;
import com.dsys.cim.mapper.ReceiveAddressMapper;
import com.dsys.cim.service.IReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ReceiveAddressServiceImpl extends ServiceImpl<ReceiveAddressMapper, ReceiveAddress> implements IReceiveAddressService{
    
    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;
    
    @Override
    public List<ReceiveAddress> getAddressByCustomerId (Long userId){
        QueryWrapper<ReceiveAddress> ew = new QueryWrapper<>();
        ew.eq("customerId",userId)
        .orderByAsc("defaultStatus");
        List<ReceiveAddress> receiveAddressList = receiveAddressMapper.selectList(ew);
        return receiveAddressList;
    }
    
}
