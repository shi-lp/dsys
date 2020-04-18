package com.dsys.cim.controller;


import com.dsys.cim.bean.CustomerInfo;
import com.dsys.cim.bean.ReceiveAddress;
import com.dsys.cim.service.ICustomerInfo;
import com.dsys.cim.service.IReceiveAddressService;
import com.dsys.common.sdk.response.DsysResponse;
import com.dsys.common.util.ToolUtil;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 客户信息表 前端控制器
 * </p>
 *
 * @author shilp
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/cim/customer-info")
public class CustomerInfoController{
    
    @Autowired
    private ICustomerInfo customerInfoService;
    
    @Autowired
    private IReceiveAddressService receiveAddressService;
    
    /**
     * @discription 通过用户ID获取收货地址列表
     * @author shilp
     * @created 2020/4/17  14:43
     * @Param
     * @Return
    */
    @GetMapping(value="/getReceiveAddressList")
    public DsysResponse getAddressList(@RequestParam("customerId")String customerId){
        DsysResponse dr = new DsysResponse();
        List<ReceiveAddress> raList = new ArrayList<>();
        if(ToolUtil.isNullOrEmpty(customerId)){
            raList = receiveAddressService.getAddressByCustomerId(Long.valueOf(customerId));
            dr.success(raList);
        }else{
            dr.failure("传入用户ID为空");
        }
        return dr;
    }
    
    /**
     * @discription 新增用户信息
     * @author shilp
     * @created 2020/4/17  14:43
     * @Param
     * @Return
    */
    @PostMapping(value="/addCustomerInfo")
    public DsysResponse addCustomerInfo(@RequestBody CustomerInfo customerInfo){
        DsysResponse dr = new DsysResponse();
        if(ToolUtil.isNullOrEmpty(customerInfo)){
            customerInfoService.saveOrUpdate(customerInfo);
            dr.success();
        }
        return dr;
    }
    
    
    
}
