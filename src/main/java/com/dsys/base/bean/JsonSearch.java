package com.dsys.base.bean;

import lombok.Data;

/**
 * 
 * Title: JsonSearch.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年11月25日 下午10:07:24 
 * @update 2019年11月25日 下午10:07:24 
 * @version 1.0
 */

@Data
public class JsonSearch{

	/**
	 * 查询的属性名称 是java对象名称或as的名称
	 */
	private String seachName;
	
	/**
	 * 类型 String  date number
	 */
	private String seachType;
	
	/**
	 * 格式 只支持date 如 yyyy-MM-dd等
	 */
	private String seachFormatter;
	//	
	/**
	 * 连接方式 
	 * eq:等于 ||ge:大于等于 ||ne:不等于||le:小于等于||lt:小于||gt:大于||likeAll:匹配全部||likeLeft:左匹配||likeRight:右匹配
	 */
	private String searchConnector;
	
	/**
	 * 查询值
	 */
	private String searchValue;
}
