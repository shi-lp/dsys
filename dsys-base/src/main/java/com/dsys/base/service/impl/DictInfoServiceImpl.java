package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dsys.base.bean.DictInfo;
import com.dsys.base.mapper.DictInfoMapper;
import com.dsys.base.service.IDictInfoService;
import com.dsys.common.util.ToolUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Title: DictInfoServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/13 16:31
 */
@Service
public class DictInfoServiceImpl extends ServiceImpl<DictInfoMapper,DictInfo> implements IDictInfoService{
    
    @Autowired
    private DictInfoMapper dictInfoMapper;
    
    @Override
    public boolean existDictInfo (DictInfo dictInfo){
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("dictCode",dictInfo.getDictCode());
        List<DictInfo> dicts = dictInfoMapper.selectByMap(params);
        if(!ToolUtil.isNullOrEmpty(dicts)){
            return true;
        }
        return false;
    }
    
    @Override
    public void addDictInfo (DictInfo dictInfo){
        dictInfoMapper.insert(dictInfo);
    }
}
