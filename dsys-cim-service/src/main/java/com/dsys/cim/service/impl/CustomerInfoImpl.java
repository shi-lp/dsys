package com.dsys.cim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.cim.CustomerInfo;
import com.dsys.api.service.cim.ICustomerInfo;
import com.dsys.cim.mapper.CustomerInfoMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
