package com.dsys.base.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dsys.api.bean.base.Auth;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * Title: DictInfoDao.java
 * Description: 权限模块数据库操作
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @created 2019年12月16日 下午7:55:13
 * @update 2019年12月16日 下午7:55:13
 * @version 1.0
 */
public interface AuthMapper extends BaseMapper<Auth>{
    
    public List<Auth> selectAllList (Map<String, Object> sqlMap);
}
