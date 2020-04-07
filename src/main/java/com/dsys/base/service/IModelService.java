package com.dsys.base.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dsys.base.bean.Model;

/**        
 * Title: IModelService.java    
 * Description: 模块操作
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月16日 下午9:13:20 
 * @update 2019年12月16日 下午9:13:20 
 * @version 1.0
*/
public interface IModelService {

	/**
	 * 
	 * @discription 初始化配置查询所有模块
	 * @author shilp       
	 * @created 2019年12月16日 下午9:15:03  
	 * @update 2019年12月16日 下午9:15:03   
	 * @return
	 */
	public List<Model> StatInitModels(String aoth);
	
	public boolean addModel(Model model);
	
}
