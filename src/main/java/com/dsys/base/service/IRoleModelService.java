package com.dsys.base.service;

import java.util.List;
import com.dsys.base.bean.RoleModel;

/**        
 * Title: IRoleModelService.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月17日 上午8:54:57 
 * @update 2019年12月17日 上午8:54:57 
 * @version 1.0
*/

public interface IRoleModelService {
	
	/**
	 * 
	 * @discription 获取所有的角色模块
	 * @author shilp       
	 * @created 2019年12月17日 上午9:10:22  
	 * @update 2019年12月17日 上午9:10:22   
	 * @return
	 */
	public List<RoleModel> getAllRoleModel();
	
	/**
	 * 
	 * @discription 加载角色模块到缓存
	 * @author shilp       
	 * @created 2019年12月17日 上午9:13:40  
	 * @update 2019年12月17日 上午9:13:40   
	 * @return
	 */
	public boolean initRoleModel();
}
