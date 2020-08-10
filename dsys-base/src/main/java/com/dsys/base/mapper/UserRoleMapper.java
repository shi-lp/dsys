package com.dsys.base.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dsys.api.bean.base.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Select;

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
public interface UserRoleMapper extends BaseMapper<UserRole>{

	
	@Select("SELECT ROLE_CODE FROM BP_USERROLE_TB ${ew.customSqlSegment}")
	public List<String> selectRolesList (LambdaQueryWrapper<UserRole> queryWrapper);
}
