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
import lombok.EqualsAndHashCode;

/**
 *
 * Title: GoodsSpec.java
 * Description: 商品规格
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @created 2019年12月26日 下午11:45:45
 * @update 2019年12月26日 下午11:45:45
 * @version 1.0
 */
@Data
@TableName(value = "SP_GOODS_SPEC_TB")
public class GoodsSpec implements Serializable {
    /**  描述   (@author: shilp) */

    private static final long serialVersionUID = -8443932873300199869L;
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long id;

    // 规格编码
    private String specCode;

    // 规格名称
    private String specName;

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