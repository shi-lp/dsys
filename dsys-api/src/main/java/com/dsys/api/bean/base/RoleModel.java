package com.dsys.api.bean.base;

import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * 
 * ClassName: UserModel
 * 
 * @Description: 用户可见模块配置
 * @author shilp
 * @date 2019年11月5日
 */
@Data
public class RoleModel implements Serializable{

	
	/**  描述   (@author: shilp) */      
	    
	private static final long serialVersionUID = 2676966225874507829L;

	// 角色编码
	private String roleCode;

	// 模块编码
	private String modelCode;

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

}
