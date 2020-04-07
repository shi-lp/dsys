package com.dsys.base.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.dsys.base.bean.DictInfo;
import com.dsys.base.bean.Model;
import javax.swing.text.html.HTML;

/**
 * Title: Constant.java Description: 基础参数初始化
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月15日 下午2:33:55
 * @update 2019年12月15日 下午2:33:55
 * @version 1.0
 */

public class Constants {

	// 用户在线
	public static final String USER_ONLINE = "1";

	// 用户在线
	public static final String USER_DISONLINE = "0";
	
	// 状态未启用
	public static final String STATUS_DISABLE = "0";
	
	// 状态启用
	public static final String STATUS_ENABLE = "1";
	
	// 被删除
	public static final String STATUS_DELETE = "2";
	
	// 注册邮箱标识
	public static final String MAIL_REGISTER = "1";
	
	// 验证邮箱标识
	public static final String MAIL_SIGN = "2";
	
	// 注册手机标识
	public static final String PHONE_REGISTER = "1";
	
	// 验证手机标识
	public static final String PHONE_SIGN = "2";

	// 短信类型，验证码短信
	public static final String SMSTYPE_SIGN = "1";

	// 短信类型，通知短信
	public static final String SMSTYPE_NOTICE = "2";

	// 成功状态
	public static final String STATUS_SUCCESS = "0";

	// 失败状态
	public static final String STATUS_ERROR = "1";
	
	// 角色对应模块的缓存Map,
	public static final ConcurrentHashMap<String,List<Model>> ROLE_MODEL_CACHE = new ConcurrentHashMap<String, List<Model>>();
	
	// 数据字典缓存模块
	public static final ConcurrentHashMap<String,List<DictInfo>> DICTINFO_CACHE = new ConcurrentHashMap<String, List<DictInfo>>();
	
	// 不拦截请求结尾格式 // 不用拦截静态页面.css/.js
	public static final List<String> UNINTERCEPTOR = Arrays.asList(new String[]{"/**/*.js","/**/*.css",
										"/**/*.html","/**/*.jpg","/**/*.png","/**/*.woff2",
										"/**/*.woff","/**/*.ttf"});
	
	
	public enum  CacheNamespaceEnum{
		DATA;
		
		public char[] value (){
			return null;
		}
	};
}
