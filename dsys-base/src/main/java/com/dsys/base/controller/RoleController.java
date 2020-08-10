package com.dsys.base.controller;

import com.dsys.api.bean.base.Role;
import com.dsys.api.common.VxeOption;
import com.dsys.api.service.base.IRoleService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.sdk.response.RenderResponse;
import java.util.List;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Title: RoleController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 角色控制类
 * @created 2020/5/20 8:53
 */
@RestController
@RequestMapping(value = "/base")
public class RoleController{
    
    @Autowired
    private IRoleService roleService;
    
    /**
     * @discription 角色新增
     * @author shilp
     * @created 2020/5/20  9:17
     * @Param
     * @Return
    */
    @PostMapping(value = "/roles")
    public RenderResponse addRole(@RequestBody Role role){
        if(roleService.addRole(role)){
            return RenderResponse.success();
        }
        return RenderResponse.fail("新增失败");
    }
    
    /**
     * @discription 获取所有的角色列表
     * @author shilp
     * @created 2020/5/20  9:22
     * @Param
     * @Return
    */
    @GetMapping(value = "/roles")
    public RenderResponse getRoles(){
        List<Role> roles = roleService.getRoles();
        return RenderResponse.success(roles);
    }
    
    /**
     * @discription 获取角色下拉列表
     * @author shilp
     * @created 2020/8/3  19:47
     * @Param
     * @Return
    */
    @GetMapping(value = "/roles/option")
    public RenderResponse getRoleOption(){
        List<VxeOption> options = roleService.getOption();
        return RenderResponse.success(options);
    }
    
}
