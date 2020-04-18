package com.dsys.base.controller;

import com.dsys.base.service.IUserService;
import com.dsys.common.sdk.response.RenderResult;
import com.dsys.common.util.Constants;
import com.dsys.common.worknode.service.impl.UidServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dsys.base.bean.User;
import com.xfvape.uid.utils.NetUtils;

/**
 * Title: UserController.java Description: 用户操作控制
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月5日 下午9:14:39
 * @update 2019年12月5日 下午9:14:39
 * @version 1.0
 */
// 直接返回数据
@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private UidServiceImpl uidService;

	/**
	 * 
	 * @discription 用户新增
	 * @author shilp
	 * @created 2019年12月10日 下午9:28:57
	 * @update 2019年12月10日 下午9:28:57
	 * @param user
	 * @return
	 */
	/*@PostMapping(value = "/addUser")
	public Map<String,Object> addUser(@RequestBody User user) {
		Map<String,Object> renderMap = new HashMap<String,Object>();
		user.setSId(String.valueOf(uidService.getUid()));
		boolean respon = userService.addUser(user);
		return renderMap;
	}*/

	@PostMapping(value = "/addUser")
	public ResponseEntity <RenderResult> addUser(@RequestBody User user) {
		RenderResult res = new RenderResult(200,"ok");
		Map<String,Object> renderMap = new HashMap<String,Object>();
		user.setSId(String.valueOf(uidService.getUid()));
		if(!userService.addUser(user)){
			res.setStatus(400);
			res.setMessage("Bad request");

			// ResponseEntity是响应实体泛型，通过它可以设置http响应的状态值，此处返回400
			return new ResponseEntity<RenderResult>(res, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(res);
	}
	
	/**
	 * 
	 * @discription 用户注册
	 * @author shilp       
	 * @created 2019年12月13日 下午8:56:56  
	 * @update 2019年12月13日 下午8:56:56   
	 * @param user
	 * @return
	 */
	/*@PostMapping(value = "/regeisterUser")
	public Map<String,Object> regeisterUser(@RequestBody User user) {
		Map<String,Object> renderMap = new HashMap<String,Object>();
		user.setIp(NetUtils.getLocalAddress());
		user.setDoFlag(Constant.STATUS_ENABLE);
		user.setOnlineStatus(Constant.USER_DISONLINE);
		user.setSId(String.valueOf(uidService.getUid()));
		if(userService.addUser(user)){
			renderMap.put("status", "1");
			renderMap.put("msg", "注册成功");
		}else{
			renderMap.put("status", "0");
			renderMap.put("msg", "注册失败");
		}
		return renderMap;
	}*/

	@PostMapping(value = "/regeisterUser")
	public ResponseEntity <RenderResult> regeisterUser(@RequestBody User user) {
		RenderResult res = new RenderResult(200,"ok");
		Map<String,Object> renderMap = new HashMap<String,Object>();
		if(userService.accountIsExist(user.getAccount())){
			user.setIp(NetUtils.getLocalAddress());
			user.setDoFlag(Constants.STATUS_ENABLE);
			user.setOnlineStatus(Constants.USER_DISONLINE);
			user.setSId(String.valueOf(uidService.getUid()));
			if(userService.addUser(user)){
				renderMap.put("status", "1");
				renderMap.put("msg", "注册成功");
			}else{
				renderMap.put("status", "0");
				renderMap.put("msg", "注册失败");
			}
		}
		res.setData(renderMap);
		return ResponseEntity.ok(res);
	}
	
	/**
	 * 
	 * @discription 判断账号是否存在
	 * @author shilp       
	 * @created 2019年12月15日 下午2:41:08  
	 * @update 2019年12月15日 下午2:41:08   
	 * @param account
	 * @return
	 */
	@PostMapping(value = "/accountIsExist")
	public Map<String,Object> accountIsExist(@RequestParam("account") String account) {
		Map<String,Object> renderMap = new HashMap<String,Object>();
		if(!userService.accountIsExist(account)){
			renderMap.put("status", "1");
			renderMap.put("msg", "用户名已存在");
		}else{
			renderMap.put("status", "0");
			renderMap.put("msg", "注册失败");
		}
		return renderMap;
	}
	

	@PostMapping(value = "/getUsers")
	public Map<String,Object> getUser(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {
		Map<String,Object> renderJson = new HashMap <String,Object>();
		List<User> userList = userService.getUserList(null);
		renderJson.put("data",userList);
		renderJson.put("status",Constants.STATUS_SUCCESS);
		renderJson.put("msg",Constants.STATUS_ERROR);

		return renderJson;
	}

	@PostMapping(value = "/getUserPage")
	public Map<String,Object> getUserPage(@RequestParam(defaultValue = "1") int pageNo,
									  @RequestParam(defaultValue = "10") int pageSize) {
		Map<String,Object> renderJson = new HashMap <String,Object>();
		// 只对其后的第一个查询有效
		Page page = new Page(pageNo,pageSize);
		page = userService.selectPageUser(page,new User());
		renderJson.put("data",page.getRecords());
		renderJson.put("status",Constants.STATUS_SUCCESS);
		renderJson.put("msg",Constants.STATUS_ERROR);

		return renderJson;
	}

}
