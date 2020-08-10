package com.dsys.base.controller;

import com.dsys.api.bean.base.User;
import com.dsys.api.service.base.IUserService;
import com.dsys.common.sdk.function.event.MailEvent;
import com.dsys.common.sdk.function.publish.MailPublisher;
import com.dsys.common.sdk.response.RenderResponse;
import com.dsys.common.sdk.token.TokenUtil;
import com.dsys.common.sdk.token.UserLoginToken;
import com.dsys.common.util.*;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.JedisPool;

/**
 * 
 * Title: LoginController.java Description: 登录控制跳转
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月7日 上午11:14:25
 * @update 2019年12月7日 上午11:14:25
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/base/login")
public class UserLoginController{
	
	@Autowired
	private MailPublisher mailPublisher;

	@Autowired
	private IUserService userService;
	
	@Autowired
    private JedisPool jedisPool;

	/**
	 * @discription 登录功能,返回携带客户信息的Token
	 * @author shilp
	 * @created 2019年12月7日 下午12:31:44
	 * @update 2019年12月7日 下午12:31:44
	 * @param
	 * @return
	 */
	@GetMapping(value = "/sign")
	public RenderResponse Login(@RequestParam("account") String account,@RequestParam("pwd") String pwd) throws IOException{
		
		User user = userService.findUser(account,pwd);
		if (ToolUtil.isNullOrEmpty(user)) {
			return RenderResponse.fail("登录失败,用户不存在");
		}
		/**生成用户Token*/
		String token = TokenUtil.getToken(user.getAccount(),user.getUserName(),user.getSId());
		// 获得request对象,response对象
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String ip = IPUtil.getIP(request);
		CacheUtils.addTokenToRedis(Constants.BASE_TOKEN +
                                        Constants.USER_LOGIN_TOKEN +String.valueOf(user.getSId()),token);
		// 发送邮件事件，提醒登录信息
		mailPublisher.publisher(new MailEvent(this,user.getEMail(),"登陆提醒",getEmailStr(user.getAccount(),ip),true));
		// 将客户ID和token储存到redis中
		return RenderResponse.success(token);
	}

	@UserLoginToken(required = true)
	@GetMapping("/auth/getMessage")
	public String getMessage() {
		return "你已通过验证";
	}

	@PostMapping("/auth/admin")
	@ResponseBody
	public String printAdmin() {
		return "如果你看见这句话，说明你有ROLE_ADMIN角色";
	}

	@PostMapping("/auth/user")
	@ResponseBody
	public String printUser() {
		return "如果你看见这句话，说明你有ROLE_USER角色";
	}
	
	public String getEmailStr(String userId,String host){
		String s = DateUtils.FormatDate(LocalDateTime.now(),"yyyy-MM-dd HH:mm:ss");
		String content = "用户"+userId+"于"+s+"登陆成功\n";
		content += "当前登陆IP为："+host+"\n";
		content += GeoIpUtil.getContentByIp(host);
		return content;
	}

}
