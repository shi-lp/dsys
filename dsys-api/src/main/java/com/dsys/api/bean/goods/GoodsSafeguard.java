package com.dsys.api.bean.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * Title: SpGoodsSafeguard.java
 * Description: 增值保障
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @created 2019年12月26日 下午11:39:20
 * @update 2019年12月26日 下午11:39:20
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "SP_GOODS_FASEGUARD_TB")
public class GoodsSafeguard extends BaseModel implements Serializable {

    /**  描述   (@author: shilp) */

    private static final long serialVersionUID = 1657563595640703115L;
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long id;

    // 增值保障编码
    private String safeguardCode;

    // 增值保障名称
    private String safeguardName;

    // 保障价格
    private BigDecimal price;

    private DoFlagEnum doFlag;

}