package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dsys.base.bean.SysSetup;
import com.dsys.base.mapper.SysSetupMapper;
import com.dsys.base.service.ISysSetupService;
import java.util.List;

/**
 * Title: SysSetupServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/15 21:52
 */
public class SysSetupServiceImpl extends ServiceImpl<SysSetupMapper, SysSetup> implements ISysSetupService{
    @Override
    public List<SysSetup> findSysSetup (String sysCode){
        return null;
    }
    
    @Override
    public boolean insertSysSetup (SysSetup sysSetup){
        return false;
    }
}
