package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.ControllerView;
import com.dsys.api.service.base.IControllerViewService;
import com.dsys.base.mapper.ControllerViewMapper;
import com.dsys.common.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Title: ControllerViewServiceImpl
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/1/19 10:27
 */
@Service
public class ControllerViewServiceImpl extends ServiceImpl<ControllerViewMapper,ControllerView> implements IControllerViewService{
    
    @Autowired
    private ControllerViewMapper cvm;
    
    public boolean addCvm(ControllerView cv) {
        cvm.insert(cv);
        return false;
    }

    public List <ControllerView> findAll(ControllerView cv) {
        Wrapper<ControllerView> wrapper = new QueryWrapper <ControllerView>();
        wrapper.equals(Constants.STATUS_ENABLE);
        List <ControllerView> cvl = cvm.selectList(wrapper);
        return cvl;
    }
}
