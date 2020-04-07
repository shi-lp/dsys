package com.dsys.base.service.impl;

/**        
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dsys.base.bean.User;
import com.dsys.base.bean.UserRole;
import com.dsys.base.service.IUserRoleService;
import com.dsys.base.service.IUserService;

import cn.com.zytech.sdk.util.ToolUtil;

 * Title: CustomUserDetailsService.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月13日 下午11:05:41 
 * @update 2019年12月13日 下午11:05:41 
 * @version 1.0
@Service
 */
public class CustomUserDetailsService {
//	implements UserDetailsService 
//
//	@Autowired
//	private IUserService userService;
//	
//	@Autowired
//	private IUserRoleService userRoleService;
//	
//	@Override
//	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
//		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		User findUser = new User();
//		findUser.setAccount(account);
//        // 从数据库中取出用户信息
//        List<User> userList = userService.findUser(findUser);
//
//        // 判断用户是否存在
//        if(ToolUtil.isNullOrEmpty(userList)) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//
//        // 添加权限
//        List<UserRole> userRoles = userRoleService.listByUserId(userList.get(0).getSId());
//        for (UserRole userRole : userRoles) {
//            authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
//        }
//
//        // 返回UserDetails实现类
//        return new org.springframework.security.core.userdetails.User(userList.get(0).getAccount(), userList.get(0).getPwd(), authorities);
//	}
}
