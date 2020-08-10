package com.dsys.base.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dsys.api.bean.base.Role;
import com.dsys.api.common.VxeOption;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

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

public interface RoleMapper extends BaseMapper<Role>{
    
    public List<VxeOption> selectOptions (@Param(Constants.WRAPPER) Wrapper<Role> wrapper);
    
    public List<Role> selectLists (Map<String,Object> paramMap);
}
