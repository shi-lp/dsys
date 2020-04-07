package com.dsys.base.sdk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;

import com.dsys.base.bean.User;
import com.dsys.base.service.IUserService;
import com.dsys.base.service.impl.UserServiceImpl;

/**
 * 
 * Title: LoginHandlerInterceptor.java    
 * Description: 登陆拦截器配置
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月8日 下午9:34:27 
 * @update 2019年12月8日 下午9:34:27 
 * @version 1.0
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(LoginHandlerInterceptor.class);
	
	
	@Bean
	public IUserService getUserService(){
		return new UserServiceImpl();
	}

	/** 
	 * @discription 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理
	 * @author shilp       
	 * @created 2019年12月8日 下午9:34:50
	 * @update 2019年12月8日 下午9:34:50      
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception     
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)     
	*/  
	    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//这里的异常是我自定义的异常，系统抛出异常后框架捕获异常然后转为统一的格式返回给前端， 其实这里也可以返回false
//		throw new FastRuntimeException(20001,"No access");
		return true;
	}
	
	
	  
	/**     
	 * @discription 校验用户访问权限
	 * @author shilp       
	 * @created 2019年12月9日 下午10:41:32  
	 * @update 2019年12月9日 下午10:41:32   
	 * @param userId
	 * @param requestURI
	 * @return     
	*/
	private boolean checkAuth(Long userId, String requestURI) {
		return false;
	}

	/**
	 * @discription 根据token获取用户ID
	 * @author shilp       
	 * @created 2019年12月9日 下午10:40:34  
	 * @update 2019年12月9日 下午10:40:34   
	 * @param header
	 * @return
	 */
	private Long getUserId(String header) {
		return null;
	}

	/**     
	 * @discription 在业务处理器处理请求执行完成后，生成视图之前执行。
	 * 后处理（调用了Service并返回ModelAndView，但未进行页面渲染），
	 * 有机会修改ModelAndView （这个博主就基本不怎么用了）；
	 * @author shilp       
	 * @created 2019年12月8日 下午9:35:23  
	 * @update 2019年12月8日 下午9:35:23        
	*/
	private void postHandler() {
		// TODO 自动生成的方法存根

	}
	
	  
	/** 
	 * @discription 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）
	 * @author shilp       
	 * @created 2019年12月8日 下午9:35:38
	 * @update 2019年12月8日 下午9:35:38      
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception     
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)     
	*/  
	    
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO 自动生成的方法存根
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
