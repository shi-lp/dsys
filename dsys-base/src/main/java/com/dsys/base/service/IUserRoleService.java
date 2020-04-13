package com.dsys.base.service;

import java.util.List;
import java.util.Map;

import com.dsys.base.bean.UserRole;

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
public interface IUserRoleService {

	public List<UserRole> listByUserId(String userId);

	  
	/**     
	 * @discription 通过用户ID获取用户的角色列表
	 * @author shilp       
	 * @created 2019年12月16日 上午11:29:55  
	 * @update 2019年12月16日 上午11:29:55   
	 * @param sId
	 * @return     
	*/
	public Map<String, UserRole> mapByUserId(String sId);
}
