package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dsys.base.bean.ControllerView;
import com.dsys.base.mapper.ControllerViewMapper;
import com.dsys.base.service.IControllerViewService;
import com.dsys.common.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

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
public class ControllerViewServiceImpl extends ServiceImpl<ControllerViewMapper,ControllerView> implements IControllerViewService{
    @Autowired
    private ControllerViewMapper cvm;


    public boolean addCvm(ControllerView cv) {
        cvm.insert(cv);
        return false;
    }

    public List <ControllerView> findAll(ControllerView cv) {
        EntityWrapper<ControllerView> ew = new EntityWrapper <ControllerView>();
        ew.eq("doFlag", Constants.STATUS_ENABLE);
        List <ControllerView> cvl = cvm.selectList(ew);
        return cvl;
    }
}
