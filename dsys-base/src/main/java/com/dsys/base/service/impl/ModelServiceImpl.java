package com.dsys.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsys.api.bean.base.Auth;
import com.dsys.api.bean.base.Model;
import com.dsys.api.common.VxeOption;
import com.dsys.api.common.enums.DoFlagEnum;
import com.dsys.api.service.base.IAuthService;
import com.dsys.api.service.base.IModelService;
import com.dsys.base.mapper.ModelMapper;
import com.dsys.common.util.Constants;
import com.dsys.common.util.StringUtils;
import com.dsys.common.util.ToolUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**        
 * Title: ModelServiceImpl.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月16日 下午9:16:12 
 * @update 2019年12月16日 下午9:16:12 
 * @version 1.0
*/

@Service
@Slf4j
public class ModelServiceImpl extends ServiceImpl<ModelMapper,Model> implements IModelService{

	@Autowired
	private ModelMapper modelDao;
	
	@Autowired
	private IAuthService authService;

	/**
	 * 
	 * @discription 方法调用之前先查缓存
	 * 	cacheName/value 缓存名字
	 * 	key 缓存数据时的key，不指定，默认使用方法参数名
	 * condition:条件
	 * sync：
	 * 缓存配置类：CacheAutoConfiguration
	 */
	@Override
	public List<Model> StatInitModels(String sysStatus,String modelName) {
		LambdaQueryWrapper<Model> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.ne(Model::getDoFlag,Constants.STATUS_DELETE)
				.orderByAsc(Model::getModelOrder);
		log.info("------开始查询------");
		return modelDao.selectList(queryWrapper);
	}

	
	/**
	 * 
	 * @discription 先调用方法，再更新缓存 ，同步更新缓存（讲存取时缓存key设置未相同）
	 * @return
	 */
//	@CachePut(value="",key="")
	public List<Model> updateInitModels(){
		return null;
	}
	
	
	/**
	 * 
	 * @discription 
	 * allEntries=true；指定清除所有缓存
	 * beforeInvocation = false:缓存的清除是否在方法之前执行；默认在方法之后执行
	 */
//	@CacheEvict(value="",key="")
	public void deleteInitModels(){
		
	}
	
	@Transactional
	@Override
	public boolean addModel(Model model) {
		int insert = modelDao.insert(model);
		Auth auth = new Auth();
		auth.setModelId(model.getSId());
		auth.setAuthName(model.getModelName());
		auth.setAuthContext(model.getModelNote());
		auth.setDoFlag(DoFlagEnum.ENABLED);
		auth.setDoSide(Constants.STATUS_ENABLE);
		auth.setParentModel(model.getParentId());
		authService.addAuth(auth);
		return StringUtils.insertReturn(insert);
	}
	
	@Override
	public List<Model> getModels (String roleId){
		LambdaQueryWrapper<Model> modelLambdaQueryWrapper = new LambdaQueryWrapper<>();
		modelLambdaQueryWrapper.eq(Model::getDoFlag,Constants.STATUS_ENABLE)
				.ne(Model::getSId,Constants.TREE_ROOT)
			.orderByDesc(Model::getLevel);
		List<Model> modelList = modelDao.selectList(modelLambdaQueryWrapper);
		Map<String,List<Model>> modelListMap = new HashMap<>();
		List<Model> models = null;
		for(Model m : modelList){
			models = modelListMap.get("s"+m.getParentId());
			if(ToolUtil.isNullOrEmpty(models)){
				models = new ArrayList<>();
			}
			models.add(m);
			modelListMap.put("s"+m.getParentId(),models);
		}
		List<Model> renderList = new ArrayList<>();
		if(!ToolUtil.isNullOrEmpty(modelListMap.get("s-1"))){
            for(Model mm : modelListMap.get("s-1")){
				mm.setModelList(modelListMap.get("s"+mm.getSId()));
                renderList.add(mm);
            }
        }
		return renderList;
	}
	
	@Override
	public IPage<Model> getModelList (Page<Model> page){
	    LambdaQueryWrapper<Model> queryWrapper = new LambdaQueryWrapper<>();
	    queryWrapper.ne(Model::getDoFlag,Constants.STATUS_DELETE);
		IPage<Model> modelList = modelDao.selectModelList(page, queryWrapper);
		return modelList;
	}
    
    @Override
    public List<VxeOption> getParentOptions (String... s){
	    LambdaQueryWrapper<Model> queryWrapper = new LambdaQueryWrapper<>();
	    queryWrapper.ne(Model::getDoFlag,Constants.STATUS_DELETE)
				.and(wrapper -> wrapper.eq(Model::getSId,Constants.BASE_ROOT)
						.or()
						.eq(Model::getParentId,Constants.BASE_ROOT));
	    List<VxeOption> returnList = modelDao.getOptions(queryWrapper);
        return returnList;
    }
    
    @Override
    public boolean updateModel (Model model){
	    if(!ToolUtil.isNullOrEmpty(model.getSId())){
	        LambdaQueryWrapper<Model> queryWrapper = new LambdaQueryWrapper<>();
	        queryWrapper.eq(Model::getSId,model.getSId());
            int update = modelDao.update(model,queryWrapper);
            return StringUtils.insertReturn(update);
        }
        return false;
    }
    
    @Override
    public boolean delModel (String sid){
        if(!ToolUtil.isNullOrEmpty(sid)){
            LambdaUpdateWrapper<Model> queryWrapper = new LambdaUpdateWrapper<>();
            queryWrapper.eq(Model::getSId,sid)
                    .set(Model::getDoFlag,Constants.STATUS_DELETE);
            int update = modelDao.update(null,queryWrapper);
            return StringUtils.insertReturn(update);
        }
        return false;
    }
	
}
