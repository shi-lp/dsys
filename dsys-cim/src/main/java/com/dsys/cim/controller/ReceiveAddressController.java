package com.dsys.cim.controller;

import com.dsys.cim.bean.ReceiveAddress;
import com.dsys.cim.service.IReceiveAddressService;
import com.dsys.common.sdk.response.DsysResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: ReceiveAddressController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 客户收货地址表（Customer information management客户信息管理） 前端控制器
 * @created 2020/4/14 14:33
 */
@RestController
@RequestMapping("/cim/receive-address")
public class ReceiveAddressController{

    @Autowired
    private IReceiveAddressService receiveAddressService;
    
    
    /**
     * @discription 收货地址新增
     * @author shilp
     * @created 2020/4/17  15:13
     * @Param
     * @Return
    */
    @PostMapping(value="/addAddress")
    public DsysResponse addAddress(@RequestBody ReceiveAddress receiveAddress){
        DsysResponse dr = new DsysResponse();
        
        return dr;
    }
    
    
}
