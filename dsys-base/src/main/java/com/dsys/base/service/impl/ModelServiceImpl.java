package com.dsys.base.service.impl;

import com.dsys.base.mapper.ModelMapper;
import com.dsys.base.service.IModelService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dsys.base.bean.Model;

import lombok.extern.slf4j.Slf4j;

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
@Primary
@Service
@Slf4j
public class ModelServiceImpl implements IModelService{

	@Autowired
	private ModelMapper modelDao;

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
	@CachePut(value="models",key="#p0", unless="#result == null")
	public List<Model> StatInitModels(String aoth) {
		log.info("开始查询");
		return modelDao.findAllModel();
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

	  
	@Override
	public boolean addModel(Model model) {
		modelDao.addModel(model);
		return true;
	}

}
