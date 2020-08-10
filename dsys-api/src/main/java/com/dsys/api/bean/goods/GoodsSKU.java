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
 * Title: GoodsSku.java
 * Description: 商品SKU
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @created 2019年12月26日 下午11:43:27
 * @update 2019年12月26日 下午11:43:27
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "SP_GOODS_SKU_TB")
public class GoodsSKU extends BaseModel implements Serializable {

    /**  描述   (@author: shilp) */

    private static final long serialVersionUID = -7135214566083926835L;

    // 主键
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private	Long id;

    // SKU编码
    private	String	skuCode;

    // SKU名称
    private	String	skuName;

    // 价格
    private BigDecimal price;

    // 库存
    private Integer stock;

    // 商铺编码,为0表示自营
    private String shopCode;

    // SPU编码
    private String spuCode;

    // 状态
    private String skuStatus;

    private DoFlagEnum doFlag;
}