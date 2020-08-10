package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.Auth;
import com.dsys.api.bean.base.Model;
import com.dsys.api.common.enums.DoFlagEnum;
import com.dsys.api.service.base.IAuthService;
import com.dsys.api.service.base.IModelService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.base.mapper.AuthMapper;
import com.dsys.common.util.Constants;
import com.dsys.common.util.StringUtils;
import com.dsys.common.util.ToolUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title: AuthServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 权限表实现
 * @created 2020/5/19 16:38
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements IAuthService{
    
    @Autowired
    private AuthMapper authMapper;
    
    @Autowired
    private IModelService modelService;
    
    @Reference
    private IUidService uidService;
    
    @Override
    public boolean addAuth (Auth auth){
        auth.setSId(uidService.getUid());
        int insert = authMapper.insert(auth);
        return StringUtils.insertReturn(insert);
    }
    
    @Override
    public Map<Model, List<Auth>> getAuthModel (String roleId){
        Map<Model, List<Auth>> returnMap = new HashMap<>();
        List<Model> modelList = modelService.list();
        LambdaQueryWrapper<Auth> authLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authLambdaQueryWrapper.orderByDesc(Auth::getModelId);
        List<Auth> authList = authMapper.selectList(authLambdaQueryWrapper);
        Map<String,Model> modelMap = new HashMap<>();
        if(!ToolUtil.isNullOrEmpty(modelList)){
            for(Model m : modelList){
                modelMap.put("s"+String.valueOf(m.getSId()),m);
            }
        }
        Map<String,List<Auth>> authMap = new HashMap<>();
        if(!ToolUtil.isNullOrEmpty(authList)){
            List<Auth> auths = null;
            for(Auth a : authList){
                if(!ToolUtil.isNullOrEmpty(returnMap.get("s"+String.valueOf(a.getModelId())))){
                    auths = new ArrayList<>();
                }else{
                    auths = returnMap.get("s"+String.valueOf(a.getModelId()));
                }
                returnMap.put(modelMap.get("s"+String.valueOf(a.getModelId())),auths);
            }
        }
        return returnMap;
    }
    
    @Transactional
    @Override
    public void syncModelToAuth (){
        IPage<Model> modelList = modelService.getModelList(new Page<>(1,1000));
        List<Long> sb = new ArrayList<>();
        Map<String,Model> modelMap = new HashMap<>();
        for(Model m : modelList.getRecords()){
            sb.add(m.getSId());
            modelMap.put("s"+String.valueOf(m.getSId()),m);
        }
        LambdaQueryWrapper<Auth> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Auth::getModelId,sb)
            .eq(Auth::getDoSide,Constants.STATUS_ENABLE);
        // 需要更新数据，不存在的插入
        List<Auth> auths = authMapper.selectList(queryWrapper);
        for(Auth a : auths){
            a.setAuthName(modelMap.get("s"+String.valueOf(a.getModelId())).getModelName());
            sb.remove(a.getModelId()); // 去除更新的
        }
        Model model = null;
        Auth au = null;
        for(Long l : sb){
            au = new Auth();
            model = modelMap.get("s"+String.valueOf(l));
            au.setDoFlag(DoFlagEnum.ENABLED);
            au.setDoSide(Constants.STATUS_ENABLE);
            au.setAuthName(model.getModelName());
            au.setModelId(model.getSId());
            au.setParentModel(model.getParentId());
            au.setSId(uidService.getUid());
            auths.add(au);
        }
        this.saveOrUpdateBatch(auths);
    }
    
    @Override
    public List<Auth> getAuthList (){
        LambdaQueryWrapper<Auth> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(Auth::getDoFlag,Constants.STATUS_DELETE);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("doFlag",Constants.STATUS_DELETE);
        List<Auth> globalList = authMapper.selectAllList(paramMap);
        List<Auth> modelList = new ArrayList<>();
        List<Auth> authList = new ArrayList<>();
        Map<String,Auth> modelMap = new HashMap<>(); // 主菜单，模块列表
        Map<String,List<Auth>> authMap = new HashMap<>(); // 权限菜单
        List<Auth> auths = null;
        for(Auth a : globalList){
            if(Constants.STATUS_ENABLE.equals(a.getDoSide())){
                modelList.add(a); // 主菜单，模块列表
                modelMap.put("s" + String.valueOf(a.getModelId()),a);
            }else{
                authList.add(a); // 权限菜单
                auths = authMap.get("s" + String.valueOf(a.getModelId()));
                if(ToolUtil.isNullOrEmpty(auths)){
                    auths = new ArrayList<>();
                }
                auths.add(a);
                authMap.put("s" + String.valueOf(a.getModelId()),auths);
            }
        }
        List<Auth> renderList = new ArrayList<>();
        Map<String,List<Auth>> authMap1 = new HashMap<>();
        for(Auth aa : modelList) {
            if(!ToolUtil.isNullOrEmpty(authMap.get("s" + String.valueOf(aa.getModelId())))){
                aa.setChildren(authMap.get("s" + String.valueOf(aa.getModelId())));
            }
            if(Constants.BASE_ROOT.equals(String.valueOf(aa.getParentModel()))){
                renderList.add(aa);
            }
            auths = authMap1.get("s" + String.valueOf(aa.getParentModel()));
            if(ToolUtil.isNullOrEmpty(auths)){
                auths = new ArrayList<>();
            }
            auths.add(aa);
            authMap1.put("s" + String.valueOf(aa.getParentModel()),auths);
        }
        for(Auth aaa : renderList){
            aaa.setChildren(authMap1.get("s" + String.valueOf(aaa.getModelId())));
        }
        return renderList;
    }
    
    @Override
    public boolean existCode (String code){
        LambdaQueryWrapper<Auth> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Auth::getAuthCode,code);
        List<Auth> auths = authMapper.selectList(queryWrapper);
        return StringUtils.insertReturn(auths.size());
    }
}
