package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.Role;
import com.dsys.api.common.VxeOption;
import com.dsys.api.common.enums.DoFlagEnum;
import com.dsys.api.service.base.IRoleService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.util.Constants;
import com.dsys.common.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dsys.base.mapper.RoleMapper;
import java.util.List;

/**
 * Title: RoleServiceImpl.java
 * Description: 角色实现类
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月13日 下午10:51:53
 * @update 2019年12月13日 下午10:51:53
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements IRoleService{

    @Autowired
    private RoleMapper roleMapper;
    
    @Reference
    private IUidService uidService;

    @Override
    public Role getRoleById(String id) {
        return roleMapper.selectById(id);
    }

    
    @Override
    public boolean addRole (Role role){
        role.setSId(uidService.getUid());
        int insert = roleMapper.insert(role);
        return StringUtils.insertReturn(insert);
    }
    
    @Override
    public List<Role> getRoles (){
//        List<Role> roles = new LambdaQueryChainWrapper<>(roleMapper)
//                .ne(Role::getDoFlag,Constants.STATUS_DELETE)
//                .list();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("doFlag",DoFlagEnum.DELETED);
        List<Role> roles = roleMapper.selectLists(paramMap);
        return roles;
    }
    
    @Override
    public List<VxeOption> getOption (){
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .ne(Role::getDoFlag,Constants.STATUS_DELETE)
                .orderByDesc(Role::getRoleCode);
        List<VxeOption> options = roleMapper.selectOptions(queryWrapper);
        return options;
    }
    
}
