package com.dsys.base.controller;

import com.dsys.base.service.IModelService;
import com.dsys.common.sdk.response.RenderResult;
import com.dsys.common.util.Constants;
import com.dsys.common.worknode.service.impl.UidServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsys.base.bean.Role;
import com.dsys.base.service.IRoleService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsys.base.bean.Model;


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
@RequestMapping(value="/roleModel")
public class RoleModelController {
	
	@Autowired
	private IModelService modelService;
	
	@Autowired
	private UidServiceImpl uidService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private RedissonClient redissonClient;

	@PostMapping("/getModel")
/*	public Map<String,Object> getModel(){
		Map<String,Object> renderJson = new HashMap<String,Object>();
		List<Model> models = modelService.StatInitModels("all");
//		List<Model> models = redissonClient.getList("models::all");
		renderJson.put("data", models);
		renderJson.put("msg", "查询成功");
		renderJson.put("status",Constant.STATUS_SUCCESS);
		return renderJson;
	}*/

	public ResponseEntity <RenderResult> getModel(){
		RenderResult res = new RenderResult(200,"ok");
		Map<String,Object> renderJson = new HashMap<String,Object>();
		List<Model> models = modelService.StatInitModels("all");
//		List<Model> models = redissonClient.getList("models::all");
		renderJson.put("data", models);
		renderJson.put("msg", "查询成功");
		renderJson.put("status",Constants.STATUS_SUCCESS);
		res.setData(renderJson);
//		ResponseEntity.HeadersBuilder
		return ResponseEntity.ok(res);
	}
	
	/**
	 * 
	 * @discription 模块列表展示
	 * @author shilp       
	 * @created 2019年12月17日 下午4:36:56  
	 * @update 2019年12月17日 下午4:36:56   
	 * @param userId
	 * @return
	 */
	@PostMapping("/getAllModel")
	public Map<String,Object> getAllModel(@RequestParam("userId") String userId){
		Map<String,Object> renderJson = new HashMap<String,Object>();
		List<Model> models = modelService.StatInitModels("all");
		renderJson.put("data",models);
		return renderJson;
	}
	
	/**
	 * 
	 * @discription model新增
	 * @author shilp       
	 * @created 2019年12月17日 下午2:59:41  
	 * @update 2019年12月17日 下午2:59:41   
	 * @return
	 */
	@PostMapping(value="/addModel")
	public Map<String,Object> modelSubmit(@RequestBody Model model){
		Map<String,Object> renderJson = new HashMap<String,Object>();
		model.setDoFlag(Constants.STATUS_ENABLE);
		model.setSId(String.valueOf(uidService.getUid()));
		if(modelService.addModel(model)){
			renderJson.put("status", "1");
			renderJson.put("msg","模块添加成功");
		}else{
			renderJson.put("status", "0");
			renderJson.put("msg","模块添加失败");
		}
		return renderJson;
	}

	@PostMapping("/getAllRole")
	public Map<String,Object> getAllRole(@RequestParam("userId") String userId){
		Map<String,Object> renderJson = new HashMap<String,Object>();
		List<Role> models = roleService.getAllRole();
		renderJson.put("data",models);
		return renderJson;
	}
}
