package com.dsys.cim.controller;

import com.dsys.cim.bean.CustomerInfo;
import com.dsys.cim.service.ICustomerInfo;
import com.dsys.common.sdk.response.DsysResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: CustomerRegisterController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 用户登录
 * @created 2020/4/10 16:53
 */
@Slf4j
@RestController
@RequestMapping("/cim/customer-register")
public class CustomerRegisterController{
    @Autowired
    private ICustomerInfo customerInfoService;
    
    /**
     * @discription 客户登录
     * @author shilp
     * @created 2020/4/14  15:59
     * @Param
     * @Return
    */
    @PostMapping(value="/sign")
    public DsysResponse CustomerSignController(@RequestBody CustomerInfo customerInfo){
        
        return new DsysResponse().success("this ok json");
    }
}
