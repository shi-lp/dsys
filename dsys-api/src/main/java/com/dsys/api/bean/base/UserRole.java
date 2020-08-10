package com.dsys.api.bean.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 
 * ClassName: UserRole
 * 
 * @Description: 用户角色
 * @author shilp
 * @date 2019年11月5日
 */
@Data
@TableName(value = "BP_USERROLE_TB")
public class UserRole implements Serializable{

	
	/**  描述   (@author: shilp) */      
	    
	private static final long serialVersionUID = 5192913718532754676L;

	// 用户ID
	private String userId;

	// 角色编码
	private String roleCode;

	// 当前信息是否生效 1、生效 0、未生效 2、被删除
	private DoFlagEnum doFlag;

	// 创建人
	private String createUser;

	// 创建时间
	private Timestamp createTime;

	// 修改人
	private String updateUser;

	// 修改时间
	private Timestamp updateTime;
	
	/**
	 * 非PO字段
	 */
	@TableField(exist = false)
	private String roleName;

}
