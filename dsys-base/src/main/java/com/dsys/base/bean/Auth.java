package com.dsys.base.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import com.dsys.common.model.BaseModel;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Title: Auth.java Description: 操作权限
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月10日 下午8:31:41
 * @update 2019年12月10日 下午8:31:41
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value="bp_auth_tb")
public class Auth extends BaseModel  {

	
	/**  描述   (@author: shilp) */      
	    
//	private static final long serialVersionUID = 8416927606058908258L;

	
	/**
	 * 主键
	 */
	@TableId(value = "id",type = IdType.INPUT)
	private Long sId;
	
	/**
	 * 权限编码
	 */
	private String authCode;
	
	/**
	 * 权限名称
 	 */
	private String authName;
	
	/**
	 * 当前信息是否生效 1、生效 0、未生效 2、被删除
	 */
	private String doFlag;

}
