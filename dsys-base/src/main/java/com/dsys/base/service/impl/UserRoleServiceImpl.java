package com.dsys.base.service.impl;

import com.dsys.base.mapper.UserRoleMapper;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dsys.base.bean.UserRole;
import com.dsys.base.service.IUserRoleService;

/**        
 * Title: UserRoleServiceImpl.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月13日 下午10:53:24 
 * @update 2019年12月13日 下午10:53:24 
 * @version 1.0
*/
@Primary
@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Autowired
	private UserRoleMapper userRoleDao;
	
	@Override
	public List<UserRole> listByUserId(String userId) {
		return userRoleDao.listByUserId(userId);
	}

	@Override
	public Map<String, UserRole> mapByUserId(String sId) {
		return null;
	}
	
}
