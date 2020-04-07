package com.dsys.base.bean;

import lombok.Data;
  
/**
 * 
 * Title: Columns.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年11月25日 下午10:04:28 
 * @update 2019年11月25日 下午10:04:28 
 * @version 1.0
 */
@Data
public class Columns {

	/**
	 * 字段名称
	 */
	private String field; 
	
	/**
	 * 类别显示名字
	 */
	private String title;
	
	/**
	 * 是否隐藏
	 */
	private Boolean hidden;
	
	/**
	 * 是否是多选
	 */
	private Boolean checkbox;
	
	/**
	 * 格式信息
	 */
	private String formatter;
	
	/**
	 * 是否排序
	 */
	private Boolean sortable;
	
}
