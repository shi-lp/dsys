package com.dsys.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dsys.base.bean.ControllerView;


/**
 * Title: ControllerViewMapper
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/1/19 10:24
 */
public interface ControllerViewMapper extends BaseMapper <ControllerView> {

    int deleteByPrimaryKey(String id);

    int insertSelective(ControllerView record);

    ControllerView selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ControllerView record);

    int updateByPrimaryKey(ControllerView record);

}
