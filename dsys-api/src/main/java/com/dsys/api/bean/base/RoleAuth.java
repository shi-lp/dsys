package com.dsys.api.bean.base;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Title: RoleAuth.java Description: 角色权限对应表
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月10日 下午8:36:03
 * @update 2019年12月10日 下午8:36:03
 * @version 1.0
 */
@Data
@TableName(value = "BP_ROLEAUTH_TD")
public class RoleAuth implements Serializable{

	// 角色编码
	private String roleCode;

	// 权限编码
	private String authCode;

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
