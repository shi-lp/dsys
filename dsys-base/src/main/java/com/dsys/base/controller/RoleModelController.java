package com.dsys.base.controller;

import com.dsys.api.bean.base.Model;
import com.dsys.api.service.base.IModelService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.sdk.response.RenderResponse;
import com.dsys.common.util.Constants;
import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Title: RoleModelController.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月17日 上午10:41:43 
 * @update 2019年12月17日 上午10:41:43 
 * @version 1.0
*/
@RestController
@RequestMapping(value="/base/roleModel")
public class RoleModelController {
	
	@Autowired
	private IModelService modelService;
	
	@Reference
	private IUidService uidService;

	
	/**
	 * 
	 * @discription 模块列表展示
	 * @author shilp       
	 * @created 2019年12月17日 下午4:36:56  
	 * @update 2019年12月17日 下午4:36:56   
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "/models/{userId}")
	public RenderResponse getAllModel(@PathVariable("userId") String userId){
		List<Model> models = modelService.StatInitModels(Constants.SYS_RUN,"all");
		return RenderResponse.success(models);
	}
	
	

	
}
