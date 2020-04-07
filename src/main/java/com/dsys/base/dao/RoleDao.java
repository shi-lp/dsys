package com.dsys.base.dao;

import com.dsys.base.bean.Role;

/**
 * Title: RoleDao.java
 * Description: 角色操作
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月13日 下午11:00:11
 * @update 2019年12月13日 下午11:00:11
 */

public interface RoleDao {

    int deleteByPrimaryKey(String sId);

    int insert(Role record);

    Role getRoleById(String sId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

}
