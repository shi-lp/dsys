package com.dsys.base.sdk.Security;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dsys.base.service.impl.CustomUserDetailsService;

/**
 * Title: WebSecurityConfig.java Description: 权限配置
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月13日 下午11:18:36
 * @update 2019年12月13日 下午11:18:36
 * @version 1.0
@Configuration
@EnableWebSecurity // 启动SpringSecurity过滤器链
@EnableGlobalMethodSecurity(prePostEnabled = true)
 */
public class WebSecurityConfig {
//	extends WebSecurityConfigurerAdapter 

//	@Autowired
//	private CustomUserDetailsService userDetailsService;
//
//	/**
//	 * 
//	 * @discription 代替<security:authentication-manager>配置，认证信息
//	 * @author shilp
//	 * @created 2019年12月15日 下午10:48:15
//	 * @update 2019年12月15日 下午10:48:15
//	 * @param auth
//	 * @throws Exception
//	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
//	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
//			@Override
//			public String encode(CharSequence charSequence) {
//				return charSequence.toString();
//			}
//
//			@Override
//			public boolean matches(CharSequence charSequence, String s) {
//				return s.equals(charSequence.toString());
//			}
//		}
//
//		);
//		// 密码加密
//		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder() {
//
//		});
//	}
//
//	/**
//	 * 
//	 * @discription security配置信息,代替<Security:http>配置，拦截资源的访问权限，设置httpbasic或者form登陆方式
//	 * @author shilp
//	 * @created 2019年12月15日 下午10:19:41
//	 * @update 2019年12月15日 下午10:19:41
//	 * @param http
//	 * @throws Exception
//	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
//	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				// 如果有允许匿名的url，填在下面
//				// 拦截所有请求
//				.antMatchers("/**").fullyAuthenticated()
//				// 设置不拦截登录和注册页面
//				.antMatchers("/login", "/register").permitAll().anyRequest().authenticated().and()
//				// 设置登陆方式
//				// .httpBasic()
//				.formLogin()
//				// 设置登陆页
//				.loginPage("/login")
//				// 设置登陆成功页
//				.defaultSuccessUrl("/index").permitAll()
//				// 自定义登陆用户名和密码参数，默认为username和password
//				// .usernameParameter("username")
//				// .passwordParameter("password")
//				.and().logout().permitAll()
//				// 关闭CSRF跨域
//				.and().csrf().disable();
//	}
//
//	@Override
//	public void configure(WebSecurity web) {
//		// 设置拦截忽略文件夹，可以对静态资源放行
//		web.ignoring().antMatchers("/css/**", "/js/**");
//		web.ignoring().antMatchers("/**");
//	}
}
