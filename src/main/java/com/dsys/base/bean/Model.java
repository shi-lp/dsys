package com.dsys.base.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

/**
 * 
 * ClassName: Model
 * 
 * @Description: 模块
 * @author shilp
 * @date 2019年11月5日
 */
@Data
public class Model implements Serializable{

	
	/**  描述   (@author: shilp) */      
	    
	private static final long serialVersionUID = 1325745707586739005L;

	// 主键
	private String sId;
	
	// 父Id
	private String parentId;

	// 模块名称
	private String modelName;

	// 链接类型
	private String clientType;

	// 模块地址
	private String modelUrl;

	// 模块备注
	private String modelNote;

	// 优先级
	private Integer level;
	
	// 排序
	private Integer modelOrder;

	// 状态 1、生效 0、未生效 2、被删除
	private String doFlag;

	// 是否启用
	private String doEnable;

	// 创建人
	private String createUser;

	// 创建时间
	private Timestamp createTime;

	// 修改人
	private String updateUser;

	// 修改时间
	private Timestamp updateTime;
	

}