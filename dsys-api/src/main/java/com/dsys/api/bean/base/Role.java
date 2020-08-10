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

/**
 * 
 * ClassName: Role
 * 
 * @Description: 角色
 * @author shilp
 * @date 2019年11月5日
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "BP_ROLE_TB")
public class Role extends BaseModel implements Serializable{

	
	/**  描述   (@author: shilp) */      
	    
	private static final long serialVersionUID = 9165182322517178659L;

	/**主键*/
	@JSONField(serializeUsing= ToStringSerializer.class)
	@TableId(type= IdType.INPUT)
	private Long sId;

	/**角色编码*/
	private String roleCode;

	/**角色名称*/
	private String roleName;

	/**所属系统*/
	private String belongSys;

	/**当前信息是否生效 1、生效 0、未生效 2、被删除*/
	private DoFlagEnum doFlag;

	/**级别*/
	private Integer level;

	/**优先级*/
	private Integer point;
	
	/**部门ID*/
	@JSONField(serializeUsing= ToStringSerializer.class)
	private long deptId;
	
	@TableField(exist = false)
	private String deptName;
	
}
