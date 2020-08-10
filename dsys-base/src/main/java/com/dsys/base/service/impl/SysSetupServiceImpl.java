package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.SysSetup;
import com.dsys.api.service.base.ISysSetupService;
import com.dsys.base.mapper.SysSetupMapper;
import com.dsys.common.util.Constants;
import com.dsys.common.util.StringUtils;
import com.dsys.common.util.ToolUtil;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

/**
 * Title: SysSetupServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 系统配置
 * @created 2020/4/15 21:52
 */
@Service
public class SysSetupServiceImpl extends ServiceImpl<SysSetupMapper, SysSetup> implements ISysSetupService{
    
    @Autowired
    private SysSetupMapper sysSetupMapper;
    
    @Override
    public boolean insertSysSetup (SysSetup sysSetup){
        int insert = sysSetupMapper.insert(sysSetup);
        return StringUtils.insertReturn(insert);
    }
    
    @Override
    public boolean updateSys (SysSetup sysSetup){
        int i = sysSetupMapper.updateById(sysSetup);
        return StringUtils.insertReturn(i);
    }
    
    @Override
    public boolean exist (String sysCode){
        LambdaQueryWrapper<SysSetup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysSetup::getSysCode,sysCode);
        List<SysSetup> sysSetups = sysSetupMapper.selectList(queryWrapper);
        if(ToolUtil.isNullOrEmpty(sysSetups)){
            return true;
        }
        return false;
    }
    
    @Override
    @CachePut(cacheNames = Constants.CACHE_MODEL_NAME_BASE,key = "#sysList",unless = "")
    public List<SysSetup> insertListCache (String sysList){
        return this.list();
    }
    
    
    @Override
    @CachePut(cacheNames = Constants.CACHE_MODEL_NAME_BASE,key = "#sysMap",unless = "")
    public Map<String, SysSetup> insertMapCache (String sysMap){
        Map<String,SysSetup> renderMap = new HashMap<>();
        List<SysSetup> sysSetupList = this.list();
        for(SysSetup sys : sysSetupList){
            renderMap.put(sys.getSysCode(),sys);
        }
        return renderMap;
    }
    
    @Autowired
    private EhCacheCacheManager cacheManager;
    
    @Override
    public String getSysValue(String sysMap,String sysCode){
        Map<String,SysSetup> sysMaps = this.insertMapCache(sysMap);
        SysSetup sys = sysMaps.get(sysCode);
        if(ToolUtil.isNullOrEmpty(sys)){
            return "";
        }else{
            // 过期时间为空，表示永久有效
            if(StringUtils.isBlank(String.valueOf(sys.getExpriationTime()))){
                return sys.getSysValue();
            }else{
                // 过期时间大于当前时间，表示未过期，否则，已经过期，返回kong
                if(new Date(System.currentTimeMillis()).after(sys.getExpriationTime())){
                    return "";
                }
                return sys.getSysValue();
            }
        }
    }
    
}
