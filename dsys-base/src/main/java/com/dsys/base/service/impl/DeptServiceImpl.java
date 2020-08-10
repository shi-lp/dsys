package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.Dept;
import com.dsys.api.common.VxeOption;
import com.dsys.api.service.base.IDeptService;
import com.dsys.base.mapper.DeptMapper;
import com.dsys.common.util.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: DeptServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 部门实现类
 * @created 2020/6/16 16:25
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService{
    
    @Autowired
    private DeptMapper deptMapper;
    
    @Override
    public List<VxeOption> getDeptOption (){
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(Dept::getDoFlag,Constants.STATUS_DELETE)
                .orderByDesc(Dept::getLevelNo);
        List<VxeOption> vxeOptions = deptMapper.selectDeptOption(queryWrapper);
        return vxeOptions;
    }
}
