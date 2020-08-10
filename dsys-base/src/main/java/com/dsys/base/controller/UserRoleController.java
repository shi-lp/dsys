package com.dsys.base.controller;

import com.dsys.api.bean.base.Role;
import com.dsys.api.bean.base.UserRole;
import com.dsys.api.service.base.IUserRoleService;
import com.dsys.common.sdk.response.RenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**        
 * Title: UserRoleController.java    
 * Description: 用户角色控制层
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月17日 上午10:40:55 
 * @update 2019年12月17日 上午10:40:55 
 * @version 1.0
*/
@RestController
@RequestMapping(value="/base/userRole")
public class UserRoleController {
    
    @Autowired
    private IUserRoleService userRoleService;
    
    /**
     * @discription 新增用户角色列表
     * @author shilp
     * @created 2020/5/25  15:01
     * @Param
     * @Return
    */
    @PutMapping("/userRoles/{userId}")
    public RenderResponse addUserRole(@PathVariable("userId") String userId,
                                      @RequestBody List<Role> roles){
        if(userRoleService.addUserRoles(roles,userId)){
            return RenderResponse.success();
        }
        return RenderResponse.fail("新增用户角色失败");
    }
	
    /**
     * @discription 更新用户角色列表
     * @author shilp
     * @created 2020/5/25  14:47
     * @Param
     * @Return
    */
    @PutMapping("/userRoles")
	public RenderResponse updateUserRole(@RequestBody List<UserRole> userRoles){
        if(userRoleService.updateUserRole(userRoles)){
            return RenderResponse.success();
        }
        return RenderResponse.fail("更新用户角色列表失败");
    }
    
    /**
     * @discription 通过用户Id获取角色列表
     * @author shilp
     * @created 2020/5/25  15:54
     * @Param
     * @Return
    */
    @GetMapping("/userRoles/{userId}")
    public RenderResponse getRolesByUser(@PathVariable("userId") String userId){
    
        List<Role> roles = userRoleService.getRolesByUserId(userId);
        
        return RenderResponse.success(roles);
    }

}
