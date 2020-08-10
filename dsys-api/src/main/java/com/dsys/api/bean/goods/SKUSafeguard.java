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
import sun.util.resources.ga.LocaleNames_ga;

/**
 *
 * Title: SkuSafeguard.java
 * Description: 描述
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月26日 下午11:51:01
 * @update 2019年12月26日 下午11:51:01
 * @version 1.0
 */
@Data
@TableName(value = "SP_SKU_SAFEGUARD_TB")
public class SKUSafeguard implements Serializable {

    /** 描述 (@author: shilp) */

    private static final long serialVersionUID = -1118517844913225623L;
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(value="id",type = IdType.INPUT)
    private Long id;

    //
    private String skuCode;

    //
    private String safeguardCode;

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