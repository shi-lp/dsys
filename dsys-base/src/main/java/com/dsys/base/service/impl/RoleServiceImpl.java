package com.dsys.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dsys.base.bean.Role;
import com.dsys.base.mapper.RoleMapper;
import com.dsys.base.service.IRoleService;

import java.util.List;

/**
 * Title: RoleServiceImpl.java
 * Description: 描述
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月13日 下午10:51:53
 * @update 2019年12月13日 下午10:51:53
 */
@Primary
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleDao;

    @Override
    public Role getRoleById(String id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public List <Role> getAllRole() {
        return null;
    }

}
