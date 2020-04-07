package com.dsys.base.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * 
 * ClassName: Role
 * 
 * @Description: 角色
 * @author shilp
 * @date 2019年11月5日
 */
@Data
public class Role implements Serializable{

	
	/**  描述   (@author: shilp) */      
	    
	private static final long serialVersionUID = 9165182322517178659L;

	// 主键
	private String sId;

	// 角色编码
	private String roleCode;

	// 角色名称
	private String roleName;

	// 所属系统
	private String belongSys;

	// 当前信息是否生效 1、生效 0、未生效 2、被删除
	private String doFlag;

	// 级别
	private Integer level;

	// 优先级
	private Integer point;

	// 创建人
	private String createUser;

	// 创建时间
	private Timestamp createTime;

	// 修改人
	private String updateUser;

	// 修改时间
	private Timestamp updateTime;
	
}
