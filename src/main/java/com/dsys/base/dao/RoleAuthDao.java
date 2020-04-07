package com.dsys.base.dao;

import com.dsys.base.bean.RoleAuth;

/**
 * Package: com.dsys.base.dao
 * Description：角色权限操作
 * Author: shilp
 * Date:  2019/12/25 20:47
 * Modified By:
 */
public interface RoleAuthDao {

    int insert(RoleAuth record);

    int insertSelective(RoleAuth record);
}
