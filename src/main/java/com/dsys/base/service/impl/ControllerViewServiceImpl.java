package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dsys.base.bean.ControllerView;
import com.dsys.base.dao.ControllerViewMapper;
import com.dsys.common.IControllerViewService;
import com.dsys.base.util.Constants;
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
public class ControllerViewServiceImpl implements IControllerViewService {
    @Autowired
    private ControllerViewMapper cvm;


    @Override
    public boolean addCvm(ControllerView cv) {
        cvm.insert(cv);
        return false;
    }

    @Override
    public List <ControllerView> findAll(ControllerView cv) {
        EntityWrapper<ControllerView> ew = new EntityWrapper <ControllerView>();
        ew.eq("doFlag", Constants.STATUS_ENABLE);
        List <ControllerView> cvl = cvm.selectList(ew);
        return cvl;
    }
}
