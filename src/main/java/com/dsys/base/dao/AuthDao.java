package com.dsys.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dsys.base.bean.Auth;

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
public interface AuthDao extends BaseMapper<Auth> {

    int deleteByPrimaryKey(String sId);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(String sId);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);
}
