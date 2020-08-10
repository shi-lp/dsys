package com.dsys.api.bean.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * Title: Auth.java Description: 操作权限
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月10日 下午8:31:41
 * @update 2019年12月10日 下午8:31:41
 * @version 1.0
 */
@Data
@TableName(value="bp_auth_tb")
public class Auth extends BaseModel implements Serializable{

	
	/**  描述   (@author: shilp) */      
	    
//	private static final long serialVersionUID = 8416927606058908258L;
	
	/**
	 * 主键
	 */
	@JSONField(serializeUsing= ToStringSerializer.class)
	@TableId(type = IdType.INPUT)
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
	private DoFlagEnum doFlag;
	
	/**
	 * 权限描述
	 */
	private String authContext;
	
	/**
	 * 模块ID
	*/
	@JSONField(serializeUsing= ToStringSerializer.class)
	private Long modelId;
	
	/**
	 * fu模块ID
	 */
	@JSONField(serializeUsing= ToStringSerializer.class)
	private Long parentModel;
	
	/**     
	 * @discription 是否为菜单
	 * @author shilp       
	 * @created 2020/8/5  11:39
	 * @Param 
	 * @Return 
	*/
	private String doSide;
	
	@TableField(exist = false)
	private String modelName;
	
	@TableField(exist = false)
	private List<Auth> children;
	
}
