package com.dsys.cim.controller;

import com.dsys.api.bean.cim.ReceiveAddress;
import com.dsys.api.service.cim.IReceiveAddressService;
import com.dsys.common.sdk.response.RenderResponse;
import com.dsys.common.sdk.token.UserLoginToken;
import com.dsys.common.util.ToolUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

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

    /**@Autowired*/
    @Reference
    private IReceiveAddressService receiveAddressService;
    
    
    /**
     * @discription 收货地址新增
     * @author shilp
     * @created 2020/4/17  15:13
     * @Param
     * @Return
    */
    @PostMapping(value="/addAddress")
    @UserLoginToken(required = true)
    public RenderResponse addAddress(@RequestBody ReceiveAddress receiveAddress){
        
        return RenderResponse.success();
    }
    
    /**
     * @discription 通过用户ID获取收货地址列表
     * @author shilp
     * @created 2020/4/17  14:43
     * @Param
     * @Return
     */
    //    @RequestMapping(value="/receiveAddresses/{customerId}",method = RequestMethod.GET,produces = "application/json")
    @GetMapping(value="/address/{customerId}")
    @UserLoginToken(required = true)
    public RenderResponse getAddressList (@PathVariable("customerId") String customerId){
        List<ReceiveAddress> raList = new ArrayList<>();
        if(ToolUtil.isNullOrEmpty(customerId)){
            //            raList = receiveAddressService.getAddressByCustomerId(Long.valueOf(customerId));
        }else{
            return RenderResponse.fail("传入用户ID为空");
        }
        return RenderResponse.success(raList);
    }
    
    
}
