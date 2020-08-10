package com.dsys.cim.controller;

import com.dsys.api.bean.cim.CustomerInfo;
import com.dsys.api.service.cim.ICustomerInfo;
import com.dsys.common.sdk.response.RenderResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
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
    
    @Reference
    private ICustomerInfo customerInfoService;
    
    /**
     * @discription 客户登录
     * @author shilp
     * @created 2020/4/14  15:59
     * @Param
     * @Return
    */
    @PostMapping(value="/sign")
    public RenderResponse CustomerSignController(@RequestBody CustomerInfo customerInfo){
        
        return RenderResponse.success("this ok json");
    }
}
