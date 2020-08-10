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
 *
 * Title: SKUSpecValue.java Description: sku规格
 *
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月26日 下午11:52:46
 * @update 2019年12月26日 下午11:52:46
 * @version 1.0
 */
@Data
@TableName(value = "SP_SKU_SPEC_TB")
public class SKUSpecValue implements Serializable {

    /**  描述   (@author: shilp) */

    private static final long serialVersionUID = 8085983202437520154L;
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(value="id",type = IdType.INPUT)
    private Long id;

    //
    private String skuCode;

    // 规格值编码
    private String specValueCode;

    // 当前信息是否生效 1、生效 0、未生效 2、被删除
    private DoFlagEnum doFlag;

    //
    private String createUser;

    //
    private Timestamp createTime;

    //
    private String updateUser;

    //
    private Timestamp updateTime;

}