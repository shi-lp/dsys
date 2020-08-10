package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.DictInfo;
import com.dsys.api.bean.base.TreeBean;
import com.dsys.api.common.enums.DoFlagEnum;
import com.dsys.api.service.base.IDictInfoService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.util.TreeUtil;
import com.dsys.base.util.DictUtil;
import com.dsys.base.mapper.DictInfoMapper;
import com.dsys.common.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Title: DictInfoServiceImpl
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 数据字典操作类
 * @created 2020/4/13 16:31
 */
@Slf4j
@Service
public class DictInfoServiceImpl extends ServiceImpl<DictInfoMapper, DictInfo> implements IDictInfoService{
    
    @Autowired
    private DictInfoMapper dictInfoMapper;
    
    @Reference
    private IUidService uidService;
    
    @Autowired
    private RedissonClient redis;
    
    @Override
    public List<DictInfo> getDictList (String sysStatus,String dictName){
        if(!ToolUtil.isNullOrEmpty(sysStatus) && Constants.SYS_START.equals(sysStatus)){
            updateCacheLists();
        }
        List<DictInfo> dictInfos = cacheDictList();
        return dictInfos;
    }
    
    /**
     * @discription 从缓存中获取列表
     * @author shilp
     * @created 2020/8/3  10:45
     * @Param
     * @Return
    */
    public List<DictInfo> cacheDictList(){
        RBucket<List<DictInfo>> rBucket = redis.getBucket(Constants.BASE_DICT_LIST);
        List<DictInfo> dictInfos = rBucket.get();
        return dictInfos;
    }
    
    /**
     * @discription 从数据库中获取列表
     * @author shilp
     * @created 2020/8/3  10:45
     * @Param
     * @Return
    */
    public List<DictInfo> dbDictList(){
        LambdaQueryWrapper<DictInfo> lambdaQueryWrapper = new LambdaQueryWrapper<DictInfo>();
        lambdaQueryWrapper.ne(DictInfo::getSId,Constants.BASE_ROOT)
                .ne(DictInfo::getDoFlag,Constants.STATUS_DELETE)
                .orderByAsc(DictInfo::getDictCode);
        List<DictInfo> dictInfos = dictInfoMapper.selectList(lambdaQueryWrapper);
        return dictInfos;
    }
    
    /**
     * @discription 更新列表缓存
     * @author shilp
     * @created 2020/8/3  10:49
     * @Param
     * @Return
    */
    public void updateCacheLists(){
        List<DictInfo> dictInfos =  dbDictList();
        // 更新缓存中的数据字典树
        RBucket<List<DictInfo>> rBucket = redis.getBucket(Constants.BASE_DICT_LIST);
        if(!ToolUtil.isNullOrEmpty(dictInfos)){
            // 将数据库查询结果
            rBucket.set(dictInfos);
        }else{// 数据不存在 将redis设置为”null“，防止缓存穿透
            rBucket.set(null);
        }
    }
    
    @Override
    public DictInfo getDictInfoByid (String pid){
        LambdaQueryWrapper<DictInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DictInfo::getDictCode,pid);
        List<DictInfo> dictInfo = dictInfoMapper.selectList(queryWrapper);
        return dictInfo.get(0);
    }
    
    @Override
    public boolean existDictInfo (DictInfo dictInfo){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("dictCode",dictInfo.getDictCode());
        List<DictInfo> dicts = dictInfoMapper.selectByMap(params);
        if(!ToolUtil.isNullOrEmpty(dicts)){
            return true;
        }
        return false;
    }
    
    @Override
    public boolean addDictInfo (DictInfo dictInfo){
        if(!existDictCode(dictInfo.getDictCode())){
            dictInfo.setParentCode(StringUtil.isBlank(dictInfo.getParentCode()) ? "-1" : dictInfo.getParentCode());
            dictInfo.setDoFlag(DoFlagEnum.ENABLED);
            int insert = dictInfoMapper.insert(dictInfo);
            updateCache();
            updateCacheLists();
            return StringUtils.insertReturn(insert);
        }
        return false;
    }
    
    private boolean existDictCode (String dictCode){
        LambdaQueryWrapper<DictInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DictInfo::getDictCode,dictCode);
        List<DictInfo> dictInfos = dictInfoMapper.selectList(queryWrapper);
        if(!ToolUtil.isNullOrEmpty(dictInfos) && dictInfos.size() > 0){
            return true;
        }
        return false;
    }
    
    @Override
    public DictInfo getDictByParentCode (String position,String code){
        String renderCode = "";
        String parentCode = "";
        if("current".equals(position.trim())){
            if(code.length() > 4) {
                parentCode = code.substring(0,code.length() - 4);
            }else{
                parentCode = "-1";
            }
        }else if("next".equals(position.trim())){
            parentCode = code;
        }
        String order = "1";
        QueryWrapper<DictInfo> wrappers = new QueryWrapper<>();
        wrappers.orderByDesc("dict_code").eq("parent_code",parentCode)
                .groupBy("ORDER_CODE")
                .select("MAX(DICT_CODE) AS DICT_CODE,ORDER_CODE")
                .last(" limit 1 ");
        DictInfo dictInfo = dictInfoMapper.selectOne(wrappers);
        if(!ToolUtil.isNullOrEmpty(dictInfo)){
            // 截取后四位
            if(dictInfo.getDictCode().length() > 4){
                renderCode = dictInfo.getDictCode().substring(0,dictInfo.getDictCode().length() - 4);
            }
            long endCode = Long.parseLong(dictInfo.getDictCode().substring(dictInfo.getDictCode().length() - 4,dictInfo.getDictCode().length())) + 1;
            order = String.valueOf(dictInfo.getOrderCode() + 1);
            renderCode += StringUtils.dfCode(endCode);
        }else{
            if("-1".equals(parentCode)){
                renderCode = "0001";
            }else{
                renderCode = parentCode + "0001";
            }
        }
        DictInfo di = new DictInfo();
        di.setDictCode(renderCode);
        di.setOrderCode(Integer.valueOf(order));
        di.setDoFlag(DoFlagEnum.DISABLED);
        return di;
    }
    
    @Override
    public List<TreeBean> getDictByCode (String code){
        LambdaQueryWrapper<DictInfo> lambdaQueryWrapper = new LambdaQueryWrapper<DictInfo>();
        lambdaQueryWrapper.ne(DictInfo::getSId,Constants.BASE_ROOT).ne(DictInfo::getDoFlag,Constants.STATUS_DELETE).eq(DictInfo::getParentCode,code).orderByDesc(DictInfo::getDictCode);
        List<TreeBean> dictTree = dictInfoMapper.selectList2Tree1(lambdaQueryWrapper);
        return dictTree;
    }
    
    @Override
    public boolean updateDictById(DictInfo info){
        if(!ToolUtil.isNullOrEmpty(info.getDictCode())){
            LambdaUpdateWrapper<DictInfo> queryWrapper = new LambdaUpdateWrapper<>();
            queryWrapper.eq(DictInfo::getDictCode,info.getDictCode())
                        .set(DictInfo::getDictName,info.getDictName())
                        .set(DictInfo::getDictValue,info.getDictValue())
                        .set(DictInfo::getNote,info.getNote())
                        .set(DictInfo::getDoFlag,info.getDoFlag());
            int i = dictInfoMapper.update(null,queryWrapper);
            updateCache();
            return StringUtils.insertReturn(i);
        }
        return false;
    }
    
    @Override
    public boolean delDictInfoByDictCode (String dictCode){
        LambdaUpdateWrapper<DictInfo> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(DictInfo::getDictCode,dictCode)
            .set(DictInfo::getDoFlag,Constants.STATUS_DELETE);
        int delete = dictInfoMapper.update(null,queryWrapper);
        updateCache();
        return StringUtils.insertReturn(delete);
    }
    
    @Override
    public List<TreeBean> getDictTree (String sysStatus,String cacheName){
        if(!ToolUtil.isNullOrEmpty(sysStatus) &&  Constants.SYS_START.equals(sysStatus)){
            updateCache();
        }
        List<TreeBean> treeBeans = cacheTree(cacheName);
        return treeBeans;
    }
    
    public List<TreeBean> cacheTree (String cacheName){
        // 通过缓存获取字典列表
        RBucket<List<TreeBean>> rBucket = redis.getBucket(cacheName);
        List<TreeBean> treeBeans = rBucket.get();
        // 如果缓存中没有，查询数据库
        // 缓存为空
        if(ToolUtil.isNullOrEmpty(treeBeans)){
            treeBeans = getTreeBean();
            if(!ToolUtil.isNullOrEmpty(treeBeans)){
                // 将数据库查询结果
                rBucket.set(treeBeans);
            }else{// 数据不存在 将redis设置为”null“，防止缓存穿透
                rBucket.set(null);
            }
        }
        return treeBeans;
    }
    
    public List<TreeBean> getTreeBean(){
        List<TreeBean> treeBeans = new ArrayList<>();
        // 通过数据库获取字典所有列表
        LambdaQueryWrapper<DictInfo> lambdaQueryWrapper = new LambdaQueryWrapper<DictInfo>();
        lambdaQueryWrapper.ne(DictInfo::getSId,Constants.BASE_ROOT).ne(DictInfo::getDoFlag,Constants.STATUS_DELETE).orderByAsc(DictInfo::getDictCode);
        List<TreeBean> dictTree = dictInfoMapper.selectList2Tree(lambdaQueryWrapper);
        treeBeans = TreeUtil.result2List(dictTree);
        return treeBeans;
    }
    
    public void updateCache(){
        // 更新缓存中的数据字典树
        RBucket<List<TreeBean>> rBucket = redis.getBucket(Constants.BASE_DICT_TREE);
        List<TreeBean> treeBeans = getTreeBean();
        if(!ToolUtil.isNullOrEmpty(treeBeans)){
            // 将数据库查询结果
            rBucket.set(treeBeans);
        }else{// 数据不存在 将redis设置为”null“，防止缓存穿透
            rBucket.set(null);
        }
    }
    
    @Override
    public void addCountry (){
        List<DictInfo> dictInfos = DictUtil.addCountry();
        for(DictInfo di : dictInfos){
            di.setSId(uidService.getUid());
            dictInfoMapper.insert(di);
        }
    }
    
}
