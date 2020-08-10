package com.dsys.api.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.base.Model;
import com.dsys.api.common.VxeOption;
import java.util.List;

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
public interface IModelService extends IService<Model>{

	/**
	 * 
	 * @discription 初始化配置查询所有模块
	 * @author shilp       
	 * @created 2019年12月16日 下午9:15:03  
	 * @update 2019年12月16日 下午9:15:03   
	 * @return
	 */
	public List<Model> StatInitModels (String sysStatus, String modelName);
	
	/**
	 * @discription 新增模块
	 * @author shilp
	 * @created 2020/5/20  9:35
	 * @Param
	 * @Return
	*/
	public boolean addModel (Model model);
	
	/**
	 * @discription 获取模块列表
	 * @author shilp
	 * @created 2020/6/16  17:15
	 * @Param
	 * @Return
	*/
	public List<Model> getModels (String roleId);
    
    public IPage<Model> getModelList (Page<Model> page);
    
    public List<VxeOption> getParentOptions (String... s);
	
	public boolean updateModel (Model model);
	
	public boolean delModel (String sid);
	
	
}
