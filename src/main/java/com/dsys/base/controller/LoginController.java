package com.dsys.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsys.base.bean.User;
import com.dsys.base.sdk.token.TokenUtil;
import com.dsys.base.sdk.token.UserLoginToken;
import com.dsys.common.IUserService;
import com.dsys.base.util.ToolUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title: LoginController.java Description: 登录控制跳转
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月7日 上午11:14:25
 * @update 2019年12月7日 上午11:14:25
 * @version 1.0
 */
@RestController
@Slf4j
public class LoginController {

//	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;

	/**
	 * @discription 登录界面功能
	 * @author shilp
	 * @created 2019年12月7日 下午12:31:44
	 * @update 2019年12月7日 下午12:31:44
	 * @param param
	 * @return
	 */
	@PostMapping(value = "/auth/login")
	// @RequestMapping(value="/login",method = RequestMethod.POST)
	public Map<String, Object> Login(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd,
			HttpSession session) {
		Map<String, Object> param = new HashMap<String, Object>();
		User user = new User();
		user.setAccount(userName);
		user.setPwd(pwd);
		List<User> users = userService.findUser(user);
		if (ToolUtil.isNullOrEmpty(users)) {
			param.put("msg", "登录失败,用户不存在");
			param.put("status", "0");
			log.info("用户登陆失败");
		} else {
			String token = TokenUtil.getToken(user);
			session.setAttribute("loginUser", userName);
			param.put("token", token);
			param.put("user", user);
			param.put("status", "1");
			param.put("msg", "登陆成功");
		}
		return param;
	}

	@UserLoginToken
	@GetMapping("/auth/getMessage")
	public String getMessage() {
		return "你已通过验证";
	}

	@PostMapping("/auth/admin")
	@ResponseBody
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String printAdmin() {
		return "如果你看见这句话，说明你有ROLE_ADMIN角色";
	}

	@PostMapping("/auth/user")
	@ResponseBody
//	@PreAuthorize("hasRole('ROLE_USER')")
	public String printUser() {
		return "如果你看见这句话，说明你有ROLE_USER角色";
	}

}
