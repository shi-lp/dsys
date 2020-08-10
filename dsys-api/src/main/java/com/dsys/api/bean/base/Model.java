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
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 
 * ClassName: Model
 * 
 * @Description: 模块
 * @author shilp
 * @date 2019年11月5日
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "bp_model_tb")
public class Model extends BaseModel implements Serializable{

	
	/** 主键,序列化将Long转为String，防止精度丢失 */
    @JSONField(serializeUsing= ToStringSerializer.class)
	@TableId(type= IdType.INPUT)
	private Long sId;
	
	/** 父Id */
	@JSONField(serializeUsing= ToStringSerializer.class)
	private Long parentId;

	/** 模块名称 */
	private String modelName;

	/** 链接类型 */
	private String clientType;

	/** 模块地址 */
	private String modelUrl;

	/** 模块备注 */
	private String modelNote;

	/** 优先级 */
	private Integer level;
	
	/** 排序 */
	private Integer modelOrder;

	/** 状态 1、生效 0、未生效 2、被删除 */
	private DoFlagEnum doFlag;

	/** 是否启用 */
	private String doEnable;
	
	private String imgUrl;
	
	@TableField(exist = false)
	private List<Model> modelList;
	
	@TableField(exist = false)
	private String parentName;

}
