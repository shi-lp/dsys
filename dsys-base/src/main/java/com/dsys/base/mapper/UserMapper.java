package com.dsys.base.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsys.api.bean.base.User;
import java.util.List;

import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User>{
    
    public IPage<User> selectPages (Page<User> page,@Param(Constants.WRAPPER) Wrapper<User> queryWrapper,@Param("roleId")String roleId,@Param("deptId")String deptId);
}
