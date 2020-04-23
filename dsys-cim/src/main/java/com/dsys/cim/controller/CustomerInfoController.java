package com.dsys.cim.controller;


import com.dsys.cim.bean.CustomerInfo;
import com.dsys.cim.bean.ReceiveAddress;
import com.dsys.cim.service.ICustomerInfo;
import com.dsys.cim.service.IReceiveAddressService;
import com.dsys.common.sdk.response.DsysResponse;
import com.dsys.common.sdk.response.RenderResponse;
import com.dsys.common.util.ToolUtil;
import com.dsys.common.worknode.service.impl.UidServiceImpl;
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
// @ResponseBody + @Controller
@RestController
@RequestMapping("/cim/customer-info")
public class CustomerInfoController{
    
    @Autowired
    private ICustomerInfo customerInfoService;
    
    @Autowired
    private IReceiveAddressService receiveAddressService;
    
    @Autowired
    private UidServiceImpl uidService;
    
    /**
     * @discription 通过用户ID获取收货地址列表
     * @author shilp
     * @created 2020/4/17  14:43
     * @Param
     * @Return
    */
    @RequestMapping(value="/receiveAddresses/{customerId}",method = RequestMethod.GET,produces = "application/json")
//    @GetMapping(value="/getReceiveAddressList")@RequestParam("customerId") String customerId,
    public RenderResponse getAddressList (@PathVariable String customerId){
        List<ReceiveAddress> raList = new ArrayList<>();
        if(ToolUtil.isNullOrEmpty(customerId)){
            raList = receiveAddressService.getAddressByCustomerId(Long.valueOf(customerId));
        }else{
            return RenderResponse.fail("传入用户ID为空");
        }
        return RenderResponse.success(raList);
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
            customerInfo.setSId(uidService.getUid());
            customerInfoService.saveOrUpdate(customerInfo);
            dr.success();
        }
        return dr;
    }
    
    
    
}
