package com.dsys.base.controller;

import com.dsys.api.bean.base.Auth;
import com.dsys.api.bean.base.Model;
import com.dsys.api.service.base.IAuthService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.sdk.response.RenderResponse;
import java.util.List;
import java.util.Map;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @discription 权限控制层
 * @author shilp
 * @created 2020/5/19  9:46
 * @Param
 * @Return
*/
@RestController
@RequestMapping("/base")
public class AuthController {
    
    @Autowired
    private IAuthService authService;
    
    
    
    /**     
     * @discription 新增权限
     * @author shilp       
     * @created 2020/5/19  16:17
     * @Param 
     * @Return 
    */
    @PostMapping(value = "/auths")
    public RenderResponse addAuth(@RequestBody Auth auth){
        if(authService.addAuth(auth)){
            return RenderResponse.success();
        }
        return RenderResponse.fail("新增失败");
    }
    
    /**
     * @discription 获取所有的权限列表
     * @author shilp
     * @created 2020/5/19  16:19
     * @Param
     * @Return
    */
    @GetMapping(value = "/auths")
    public RenderResponse getAuth(){
        List<Auth> list = authService.getAuthList();
        return RenderResponse.success(list);
    }
    
    @GetMapping(value = "/auths/{code}")
    public RenderResponse existCode (@PathVariable("code")String code){
        if(authService.existCode(code)){
            return RenderResponse.success("已经存在");
        }
        return RenderResponse.fail("不存在");
    }
    
    
    /**
     * @discription 获取所有的模块和模块权限
     * @author shilp
     * @created 2020/6/16  17:06
     * @Param
     * @Return
    */
    @GetMapping(value = "/authModel/{roleId}")
    public RenderResponse getAuthModel(@PathVariable("roleId")String roleId){
        Map<Model,List<Auth>> renderMap = authService.getAuthModel(roleId);
        return RenderResponse.success(renderMap);
    }
}
