package com.dsys.api.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.base.Role;
import com.dsys.api.common.VxeOption;
import java.util.List;


/**        
 * Title: IRoleService.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月13日 下午10:51:22 
 * @update 2019年12月13日 下午10:51:22 
 * @version 1.0
*/
public interface IRoleService extends IService<Role>{

	public Role getRoleById (String id);
	
	
	/**
	 * @discription 角色新增
	 * @author shilp
	 * @created 2020/5/20  9:18
	 * @Param
	 * @Return
	*/
	public boolean addRole (Role role);
	
	/**
	 * @discription 获取所有Roles
	 * @author shilp
	 * @created 2020/5/20  9:27
	 * @Param
	 * @Return
	*/
	public List<Role> getRoles ();
 
	/**
	 * @discription 获取所有得下拉选项
	 * @author shilp
	 * @created 2020/8/3  20:04
	 * @Param
	 * @Return
	*/
    public List<VxeOption> getOption ();
}
