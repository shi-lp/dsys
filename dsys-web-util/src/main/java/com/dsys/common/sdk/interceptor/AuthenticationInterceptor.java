package com.dsys.common.sdk.interceptor;

import com.dsys.common.sdk.exception.CustomException;
import com.dsys.common.sdk.exception.GlobalExceptionType;
import com.dsys.common.sdk.token.PassToken;
import com.dsys.common.sdk.token.TokenUtil;
import com.dsys.common.sdk.token.UserLoginToken;
import com.dsys.common.util.HttpClientUtil;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * Title: AuthenticationInterceptor.java Description: 登陆Token校验拦截器(aop)
 * ：注意引用启动类扫描范围
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月11日 下午8:34:48
 * @update 2019年12月11日 下午8:34:48
 * @version 1.0
 */

public class AuthenticationInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		log.info("校验Token信息是否存在Start");
		String token = request.getHeader("token");
		// 从 http 请求头中取出
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
				log.info("无需Token校验");
				return true;
			}
		}
		// 需要用户权限校验
		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			// 发送http请求到认证中心进行认证
			String success = HttpClientUtil.doGet("http://localhost:8888/verify?token="+token);
//			必须登陆成功才能访问
			if (userLoginToken.required()) {
//				// 执行Token认证
				if (token == null) {
					printJson(response,"无token信息，请登录");
					throw new CustomException(GlobalExceptionType.USER_INPUT_ERROR,"无token信息，请登录");
				}
				
				
//				// 获取 token 中的 user id
//				String userId;
//				try {
//					userId = JWT.decode(token).getAudience().get(0);
//				} catch (JWTDecodeException j) {
//					throw new RuntimeException("401");
//				}

//				// 验证 token
				if(TokenUtil.verity(token)){
					// 校验过期时间，<= 2/3 token过期时间的，不用更新token
					//  大于2/3token，更新token
					// 大于过期时间的，跟redis中的token查看是否存在，不存在，返回false重新登陆，存在，也更新
//					覆盖cookie中的token，更新过期时间
					return true;
				}else{
					printJson(response,"Token验证失败");
					return false;
				}
			}else{
//				不用登陆成功也可以访问
				if(TokenUtil.verity(token)){
				//					覆盖cookie中的token，更新过期时间
					return true;
				}else{
					printJson(response,"Token验证失败");
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
	
	private static void printJson(HttpServletResponse response,String content) {
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
