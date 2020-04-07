package com.dsys.base.bean;

import java.util.List;
import lombok.Data;

/**        
 * Title: AuthInfoUser.java    
 * Description: 记录用户登录后的完整信息
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月18日 上午11:36:29 
 * @update 2019年12月18日 上午11:36:29 
 * @version 1.0
*/
@Data
public class AuthInfoUser {
	
	// 登录后生成的token
	private String token;
	
	// 用户信息
	private User user;
	
	// 用户对应的角色列表
	private List<Role> roleList;
	
	// 用户对应的模块列表
	private List<Model> modelList;
	
	// 用户对应的操作权限列表
	private List<Auth> authList;

}
