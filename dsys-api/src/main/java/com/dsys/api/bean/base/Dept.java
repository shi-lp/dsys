package com.dsys.api.bean.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门表(BpDeptTb)实体类
 *
 * @author makejava
 * @since 2020-06-16 16:12:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "BP_DEPT_TB")
public class Dept implements Serializable {
    private static final long serialVersionUID = 790141054519264180L;
    /**
    * 主键
    */
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long sId;
    
    /**
    * 上级编码
    */
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long pId;
    /**
    * 部门名称
    */
    private String deptName;
    /**
    * 部门编码
    */
    private String deptCode;
    /**
    * 描述
    */
    private String deptContext;
    /**
    * 部门等级
    */
    private Integer levelNo;
    /**
    * 是否启用
    */
    private DoFlagEnum doFlag;

}