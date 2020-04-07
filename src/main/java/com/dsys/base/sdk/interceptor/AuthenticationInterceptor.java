package com.dsys.base.sdk.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.dsys.base.util.json.JsonUtils;
import com.dsys.base.util.response.ResponseResult;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsys.base.sdk.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dsys.base.bean.User;
import com.dsys.base.sdk.token.PassToken;
import com.dsys.base.sdk.token.UserLoginToken;
import com.dsys.base.service.IUserService;

/**
 * Title: AuthenticationInterceptor.java Description: 登陆Token校验拦截器
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月11日 下午8:34:48
 * @update 2019年12月11日 下午8:34:48
 * @version 1.0
 */

public class AuthenticationInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	
	@Autowired
	private IUserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		log.info("校验Token信息是否存在Start");
		String token = request.getHeader("token");// 从 http 请求头中取出
																// token
		// 如果不是映射到方法直接通过
		if (!(object instanceof HandlerMethod)) {
			log.info("未映射token校验");
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();
		// 检查是否有passtoken注释，有则跳过认证
		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				log.info("Token校验通过");
				return true;
			}
		}
		// 检查有没有需要用户权限的注解
		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) {
				// 执行认证
				if (token == null) {
					log.warn("无Token信息");
					printJson(response, "");
					throw new RuntimeException("无token，请重新登录");
				}
				// 获取 token 中的 user id
				String userId;
				try {
					userId = JWT.decode(token).getAudience().get(0);
				} catch (JWTDecodeException j) {
					throw new RuntimeException("401");
				}
				User user = userService.findUserById(userId);
				if (user == null) {
					printJson(response, "");
					throw new RuntimeException("用户不存在，请重新登录");
				}
				// 验证 token
				if(TokenUtil.verity(token)){
					log.info("用户------"+user.getAccount()+"------Token校验通过");
					return true;
				}else{
					log.info("用户------"+user.getAccount()+"------Token校验失败");
					printJson(response, "");
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object o, Exception e) throws Exception {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
		response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
	}
	
	private static void printJson(HttpServletResponse response, String code) {
		ResponseResult responseResult = new ResponseResult(10086,false,"token过期,请重新登陆",null);
		String content = JSONUtils.toJSONString(responseResult);
		printContent(response, content);
	}
	
	
	private static void printContent(HttpServletResponse response, String content) {
		try {
			response.reset();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			response.setCharacterEncoding("UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write(content);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
