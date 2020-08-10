package com.dsys.base.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dsys.api.bean.base.Dept;
import com.dsys.api.common.VxeOption;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Title: DeptMapper
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/6/16 16:22
 */
public interface DeptMapper extends BaseMapper<Dept>{
    
    public List<VxeOption> selectDeptOption (@Param(Constants.WRAPPER) Wrapper<Dept> wrapper);
}
