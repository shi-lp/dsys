package com.dsys.base.mapper;

import java.util.List;
import com.dsys.base.bean.UserRole;

/**        
 * Title: UserRoleDao.java    
 * Description: 用户角色操作类
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月13日 下午11:01:52 
 * @update 2019年12月13日 下午11:01:52 
 * @version 1.0
*/
public interface UserRoleMapper {

	List<UserRole> listByUserId(String userId);

	int insert(UserRole record);

	int insertSelective(UserRole record);

}
