package com.dsys.base.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.dsys.common.model.BaseModel;
import java.io.Serializable;

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
public class Role extends BaseModel implements Serializable{

	
	/**  描述   (@author: shilp) */      
	    
	private static final long serialVersionUID = 9165182322517178659L;

	// 主键
	@TableId(value="id",type= IdType.INPUT)
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

	
}
