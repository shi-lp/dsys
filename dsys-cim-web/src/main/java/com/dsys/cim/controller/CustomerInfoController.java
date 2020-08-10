package com.dsys.cim.controller;


import com.dsys.api.bean.cim.CustomerInfo;
import com.dsys.api.service.cim.ICustomerInfo;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.sdk.response.RenderResponse;
import com.dsys.common.util.ToolUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客户信息表 前端控制器
 * </p>
 *
 * @author shilp
 * @since 2020-04-14
 */
// @ResponseBody + @Controller

@CrossOrigin
@RestController
@RequestMapping("/cim/customer-info")
public class CustomerInfoController{
    
    @Reference
    private ICustomerInfo customerInfoService;
    
    @Reference
    private IUidService uidService;
    
    
    
    
    /**
     * @discription 新增用户信息
     * @author shilp
     * @created 2020/4/17  14:43
     * @Param
     * @Return
    */
    @PostMapping(value="/customerInfo")
    public RenderResponse addCustomerInfo(@RequestBody CustomerInfo customerInfo){
        if(!ToolUtil.isNullOrEmpty(customerInfo)){
            customerInfo.setSId(uidService.getUid());
            customerInfoService.save(customerInfo);
        }
        return RenderResponse.success();
    }
    
    /**
     * @discription 更新用户信息
     * @author shilp
     * @created 2020/5/9  16:18
     * @Param
     * @Return
    */
    @PutMapping(value = "/customerInfo")
    public RenderResponse udCustomerInfo(){
        return RenderResponse.success();
    }
    
    
    
}
