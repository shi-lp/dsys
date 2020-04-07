package com.dsys.base.sdk.mvcConfig;

import com.dsys.base.sdk.interceptor.AuthenticationInterceptor;
import com.dsys.base.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**        
 * Title: BaseSpringMvcConfig.java    
 * Description: 扩展SpringMVC,既保留自动配置，也可以自己扩展
 * 添加@EnableWebMVC全面接管SpringMVC
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月7日 下午1:55:19 
 * @update 2019年12月7日 下午1:55:19 
 * @version 1.0
*/
@Configuration
public class BaseSpringMvcConfig implements WebMvcConfigurer{

	private static final Logger log = LoggerFactory.getLogger(BaseSpringMvcConfig.class);

	@Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
	
	/** 
	 * @discription 
	 * @author shilp       
	 * @created 2019年12月7日 下午1:58:52
	 * @update 2019年12月7日 下午1:58:52      
	 * @param
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)     
	*/  
	@Bean
	public WebMvcConfigurer webMvcConfigAdapter(){
		WebMvcConfigurer adapter = new WebMvcConfigurer() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
//				registry.addViewController("").setViewName("");
//				请求路径										返回视图名称
				registry.addViewController("/").setViewName("login");
				registry.addViewController("/login.html").setViewName("login");
				registry.addViewController("/login").setViewName("login");
				registry.addViewController("/dsys").setViewName("login");
				registry.addViewController("/dsys/").setViewName("login");
				registry.addViewController("/user/showAddUser").setViewName("/base/user/UserInput");
				registry.addViewController("/dsys/register").setViewName("register");
				registry.addViewController("/register").setViewName("register");
				registry.addViewController("/index").setViewName("index");
				registry.addViewController("/index.html").setViewName("/index.html");
				registry.addViewController("/modelInput").setViewName("/base/model/ModelInput");
				registry.addViewController("/modelList").setViewName("/base/model/ModelList");
				registry.addViewController("/top.html").setViewName("/top.html");
				registry.addViewController("/left.html").setViewName("/left.html");
				registry.addViewController("/mainCenter.html").setViewName("/mainCenter.html");
				registry.addViewController("/footer.html").setViewName("/footer.html");
				registry.addViewController("/DictInfo.html").setViewName("/base/dict/DictInfo.html");
			}
			
			  
			/** 
			 * @discription 注册拦截器
			 * @author shilp       
			 * @created 2019年12月8日 下午10:09:43
			 * @update 2019年12月8日 下午10:09:43      
			 * @param registry     
			 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)     
			*/  
			    
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				log.info("**********注册拦截器开始********");
				log.info("**********Token截器校验开始********");
				registry.addInterceptor(authenticationInterceptor())
						.addPathPatterns("/**")
						.excludePathPatterns(Constants.UNINTERCEPTOR);    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
				
				// 拦截所有页面，除了后面的
//				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//				.excludePathPatterns("/login.html","/","/login");
				// 添加Token校验拦截器
//				registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html","/","/login","/dsys","/dsys/");;
			}
		};
		return adapter;
	}
}
