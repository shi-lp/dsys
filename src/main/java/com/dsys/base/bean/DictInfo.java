package com.dsys.base.bean;

import com.dsys.base.bean.sys.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Title: DictInfo.java Description: 数据字典表
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月10日 下午8:49:32
 * @update 2019年12月10日 下午8:49:32
 * @version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class DictInfo extends BaseModel implements Serializable{
	
	
	/**  描述   (@author: shilp) */      
	    
	private static final long serialVersionUID = -202323157244431265L;

	private String sId;

	// 字典编码
	private String dictCode;

	// 上级编码
	private String parentCode;

	// 字典名称
	private String dictName;

	// 标识，0系统自动生成，1自定义添加
	private String identify;

	// 备注
	private String note;

	// 值
	private String value;

	// 排序
	private Integer orderCode;

	// 当前信息是否生效 1、生效 0、未生效 2、被删除
	private String doFlag;

	/*// 创建人
	private String createUser;

	// 创建时间
	private Timestamp createTime;

	// 修改人
	private String updateUser;

	// 修改时间
	private Timestamp updateTime;*/

}
