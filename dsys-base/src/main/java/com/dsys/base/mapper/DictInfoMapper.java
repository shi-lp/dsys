package com.dsys.base.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dsys.api.bean.base.DictInfo;
import com.dsys.api.bean.base.TreeBean;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**        
 * Title: DictInfoDao.java    
 * Description: 数据字典数据库操作类
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月16日 下午7:55:13 
 * @update 2019年12月16日 下午7:55:13 
 * @version 1.0
*/

public interface DictInfoMapper extends BaseMapper<DictInfo>{
    
    @Select("select DICT_CODE AS id,PARENT_CODE AS pid, DICT_NAME AS tname, ORDER_CODE AS orderNum from bp_dictinfo_tb ${ew.customSqlSegment}")
    public List<TreeBean> selectList2Tree (@Param(Constants.WRAPPER) Wrapper<DictInfo> queryWrapper);
    
    @Select("select DICT_CODE AS id,PARENT_CODE AS pid, DICT_NAME AS tname, ORDER_CODE AS orderNum from bp_dictinfo_tb ${ew.customSqlSegment}")
    public List<TreeBean> selectList2Tree1 (@Param(Constants.WRAPPER) Wrapper<DictInfo> queryWrapper);
}
