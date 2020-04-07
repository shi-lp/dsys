package com.dsys.base.service;

import com.dsys.base.bean.Role;
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
public interface IRoleService {

	public Role getRoleById(String id);

	List<Role> getAllRole();
}
