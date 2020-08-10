package com.dsys.common.util;

import com.dsys.api.bean.base.Model;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: Constant.java Description: 基础参数初始化
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月15日 下午2:33:55
 * @update 2019年12月15日 下午2:33:55
 * @version 1.0
 */

public class Constants {
	
	/**系统根节点数据*/
	public static final String BASE_ROOT = "-1";

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
	
	public static final String CATE_LEVEL_ONE = "level_1";
	
	public static final String CATE_LEVEL_TWO = "level_2";
	
	public static final String CATE_LEVEL_THRESS = "level_3";
	
	
	/**
	 * @discription 不拦截请求结尾格式 // 不用拦截静态页面.css/.js
	 * @author shilp
	 * @created 2020/6/5  14:45
	 * @Param
	 * @Return
	*/
	public static final List<String> UNINTERCEPTOR = Arrays.asList(new String[]{"/**/*.js","/**/*.css",
										"/**/*.html","/**/*.jpg","/**/*.png","/**/*.woff2",
										"/**/*.woff","/**/*.ttf"});
	
	/**
	 * @discription 系统启动标识，系统启动时，从数据库更新缓存
	 * @author shilp
	 * @created 2020/8/3  10:25
	 * @Param
	 * @Return
	*/
	public static final String SYS_START = "start";
	
	/**
	 * @discription 系统运行标识，运行时，根据逻辑CURD缓存
	 * @author shilp
	 * @created 2020/8/3  10:25
	 * @Param
	 * @Return
	*/
	public static final String SYS_RUN = "run";
	
	public static final Long TREE_ROOT = -1L;
	
	public static final String CACHE_MODEL_NAME_BASE = "baseCache";
	
	public static final String CACHE_MODEL_NAME_CIM = "cimCache";
	
	public static final String BASE_DICT_LIST = "base:dict:list";
	
	public static final String BASE_DICT_TREE = "base:dict:tree";
	
	public static final String BASE_DICT_MAP = "base:dict:map";
	
	public static final String BASE_MODEL_LIST = "base:model:list";
	
	public static final String BASE_MODEL_TREE = "base:model:tree";
	
	public static final String BASE_SYS_LIST = "base:sys:list";
	
	public static final String BASE_SYS_MAP = "baseSysMap";
	
	public static final String BASE_TOKEN = "base:";
	
	public static final String USER_LOGIN_TOKEN = "userLoginToken:";
	
	public static final Map<String, List<Model>> ROLE_MODEL_CACHE = new HashMap<String, List<Model>>();
}
