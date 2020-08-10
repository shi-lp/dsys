package com.dsys.api.bean.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Title: SpuSpec.java
 * Description: SPU规格表
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月26日 下午11:55:12
 * @update 2019年12月26日 下午11:55:12
 */
@Data
@TableName(value = "SP_SPU_SPEC_TB")
public class SpuSpec implements Serializable {

    /**
     * 描述   (@author: shilp)
     */

    private static final long serialVersionUID = 2441272240314934830L;
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(value="id",type = IdType.INPUT)
    private Long id;

    // SPU码
    private String spuCode;

    // 规格编码
    private String specCode;

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