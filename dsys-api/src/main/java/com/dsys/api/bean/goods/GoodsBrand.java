package com.dsys.api.bean.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Title: GoodsBrand.java
 * Description: 商品品牌
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月26日 下午11:33:34
 * @update 2019年12月26日 下午11:33:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "SP_GOODS_BRAND_TB")
public class GoodsBrand extends BaseModel implements Serializable {

    /**
     * 描述   (@author: shilp)
     */
    private static final long serialVersionUID = -88388726742348865L;
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long id;

    /**品牌编码*/
    private String brandCode;

    /**品牌名称*/
    private String brandName;

    /**图片路径*/
    private String pgUrl;

    /**当前信息是否生效 1、生效 0、未生效 2、被删除*/
    private DoFlagEnum doFlag;

}