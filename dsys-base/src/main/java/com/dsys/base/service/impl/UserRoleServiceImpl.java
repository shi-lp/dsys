package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.Role;
import com.dsys.api.bean.base.UserRole;
import com.dsys.api.service.base.IUserRoleService;
import com.dsys.base.mapper.RoleMapper;
import com.dsys.base.mapper.UserRoleMapper;
import com.dsys.common.util.StringUtils;
import com.dsys.common.util.ToolUtil;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements IUserRoleService{

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	
	@Override
	public boolean updateUserRole (List<UserRole> userRoles){
		return false;
	}
	
	@Override
	public boolean addUserRoles (List<Role> roles,String userId){
		// 删除数据库角色列表
		LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(UserRole::getUserId,userId);
		int delete = userRoleMapper.delete(queryWrapper);
		if(!StringUtils.insertReturn(delete)){
			log.error("角色批量删除失败");
			return false;
		}
		// 更新数据库
		List<UserRole> userRoles = new ArrayList<>();
		UserRole userRole = null;
		for(Role role : roles){
			userRole = new UserRole();
			userRole.setRoleCode(role.getRoleCode());
			userRole.setUserId(userId);
			userRoles.add(userRole);
		}
		if(!this.saveBatch(userRoles)){
			log.error("批量新增用户角色失败");
			return false;
		}
		// 更新缓存中的角色列表
		
		return false;
	}
	
	@Override
	public List<Role> getRolesByUserId (String userId){
		List<Role> roleList = null;
//		从缓存中获取权限列表

//		从数据库中获取权限列表
		LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(UserRole::getUserId,userId)
			.select(UserRole::getRoleCode);
		List<String> roles = userRoleMapper.selectRolesList(queryWrapper);
		if(!ToolUtil.isNullOrEmpty(roles)){
			roleList = roleMapper.selectBatchIds(roles);
		}
		return roleList;
	}
	
}
