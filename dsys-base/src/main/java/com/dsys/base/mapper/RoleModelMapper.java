package com.dsys.base.mapper;

import com.dsys.api.bean.base.RoleModel;
/**
 * Title: RoleModelDao.java    
 * Description: 角色模块表操作
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月17日 上午10:11:05 
 * @update 2019年12月17日 上午10:11:05 
 * @version 1.0
*/
public interface RoleModelMapper{

    int insertSelective(RoleModel record);

}
