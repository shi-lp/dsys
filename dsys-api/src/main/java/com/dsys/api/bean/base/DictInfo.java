package com.dsys.api.bean.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
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
@TableName(value = "bp_dictinfo_tb")
public class DictInfo extends BaseModel implements Serializable{
	
	
	/**  描述   (@author: shilp) */
	@JSONField(serializeUsing= ToStringSerializer.class)
	@TableId(type= IdType.INPUT)
	private Long sId;

	/**字典编码*/
	private String dictCode;

	/**上级编码*/
	private String parentCode;

	/**字典名称*/
	private String dictName;

	/**标识，0系统自动生成，1自定义添加*/
	private String identify;

	/**备注*/
	private String note;

	/**值*/
	private String dictValue;

	/**排序*/
	private Integer orderCode;

	/**当前信息是否生效 1、生效 0、未生效 2、被删除*/
	private DoFlagEnum doFlag;

	

}
