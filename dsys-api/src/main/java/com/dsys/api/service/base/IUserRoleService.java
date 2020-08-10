package com.dsys.api.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.base.Role;
import com.dsys.api.bean.base.UserRole;
import java.util.List;
import java.util.Map;

/**        
 * Title: IUserRoleService.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月13日 下午10:52:48 
 * @update 2019年12月13日 下午10:52:48 
 * @version 1.0
*/
public interface IUserRoleService extends IService<UserRole>{

 
	/**
	 * @discription 更新用户角色
	 * @author shilp
	 * @created 2020/5/25  14:56
	 * @Param
	 * @Return
	*/
    public boolean updateUserRole (List<UserRole> userRoles);
	
    /**
     * @discription 新增用户角色
     * @author shilp
     * @created 2020/5/25  15:03
     * @Param
     * @Return
    */
	public boolean addUserRoles (List<Role> roles,String userId);
	
	/**
	 * @discription 通过用户ID获取用户的角色列表
	 * @author shilp
	 * @created 2019年12月16日 上午11:29:55
	 * @update 2019年12月16日 上午11:29:55
	 * @param
	 * @return
	 */
	public List<Role> getRolesByUserId (String userId);
}
