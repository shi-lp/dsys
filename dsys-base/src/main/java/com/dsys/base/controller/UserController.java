package com.dsys.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsys.api.bean.base.User;
import com.dsys.api.common.enums.DoFlagEnum;
import com.dsys.api.service.base.IUserService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.sdk.response.RenderResponse;
import com.dsys.common.util.Constants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Title: UserController.java Description: 用户操作控制
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月5日 下午9:14:39
 * @update 2019年12月5日 下午9:14:39
 * @version 1.0
 */
@RestController
@RequestMapping(value="/base")
public class UserController {

	@Autowired
	private IUserService userService;

	@Reference
	private IUidService uidService;

	
	/**
	 * 
	 * @discription 用户注册
	 * @author shilp       
	 * @created 2019年12月13日 下午8:56:56  
	 * @update 2019年12月13日 下午8:56:56   
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/users")
	public RenderResponse registerUser (@RequestBody User user) {
		if(userService.accountIsExist(user.getAccount())){
			user.setSId(uidService.getUid());
			if(userService.addUser(user)){
				return RenderResponse.success();
			}else{
				return RenderResponse.fail("注册失败");
			}
		}else{
			return RenderResponse.fail("用户已经注册");
		}
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
	@GetMapping(value = "/users/account/{account}")
	public RenderResponse accountIsExist (@PathVariable("account") String account) {
		if(userService.accountIsExist(account)){
			return RenderResponse.success();
		}
		return RenderResponse.fail("用户已存在");
	}
	

	/**
	 * @discription 获取用户列表
	 * @author shilp
	 * @created 2020/5/19  15:43
	 * @Param
	 * @Return
	*/
	@GetMapping(value = "/users")
	public RenderResponse getUser(@RequestParam("pageSize") Long pageSize,
								  @RequestParam("current") Long current,
								  @RequestParam("account") String account,
								  @RequestParam("userName") String userName,
								  @RequestParam("roleId") String roleId,
								  @RequestParam("deptId") String deptId,
								  @RequestParam("doFlag") String doFlag) {
		User user = new User();
		user.setAccount(account);
		user.setUserName(userName);
		user.setRoleId(roleId);
		user.setDeptId(deptId);
		user.setDoFlag(DoFlagEnum.valueOf(doFlag));
		Page<User> page = new Page<User>(current,pageSize);
		IPage<User> userList = userService.getUserList(page,user);
		return RenderResponse.success(userList);
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
