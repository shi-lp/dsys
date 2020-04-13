package com.dsys.cim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.cim.bean.CustomerInfo;
import com.dsys.cim.mapper.CustomerInfoMapper;
import com.dsys.cim.service.ICustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: CustomerInfoImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/13 9:26
 */
@Service
public class CustomerInfoImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements ICustomerInfo{
    
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    
    
}
