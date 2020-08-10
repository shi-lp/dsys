package com.dsys.base.service.impl;

import com.dsys.api.bean.base.Model;
import com.dsys.api.bean.base.RoleModel;
import com.dsys.api.service.base.IRoleModelService;
import com.dsys.common.util.Constants;
import com.dsys.common.util.ToolUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**        
 * Title: RoleModelServiceImpl.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月17日 上午9:01:30 
 * @update 2019年12月17日 上午9:01:30 
 * @version 1.0
*/
@Service
public class RoleModelServiceImpl implements IRoleModelService{

	  
	@Override
	@Cacheable(value="models",key="allRoleModel")
	public List<RoleModel> getAllRoleModel() {
		return null;
	}

	  
	@Override
	public boolean initRoleModel() {
		// 从缓存中获取角色模块
		List<RoleModel> rms = new ArrayList<RoleModel>();
		// 从模块中获取全部模块
		List<Model> models = new ArrayList<Model>();
		// List转map进行使用
		Map<String,Model> modelMap = modelList2Map(models);
		List<Model> midModels = null;
		for(RoleModel rm : rms){
			midModels = new ArrayList<Model>();
			if(!ToolUtil.isNullOrEmpty(Constants.ROLE_MODEL_CACHE.get(rm.getRoleCode()))){
				midModels = Constants.ROLE_MODEL_CACHE.get(rm.getRoleCode());
			}
			midModels.add(modelMap.get(rm.getModelCode()));
			Constants.ROLE_MODEL_CACHE.put(rm.getRoleCode(),midModels);
		}
		return true;
	}


	  
	private Map<String, Model> modelList2Map (List<Model> models) {
		Map<String, Model> tempMap = new HashMap<String,Model>();
		for(Model m : models){
			tempMap.put(String.valueOf(m.getSId()),m);
		}
		return tempMap;
	}

}
