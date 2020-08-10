package com.dsys.api.bean.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * Title: GoodsCategory.java
 * Description: 商品分类
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @created 2019年12月26日 下午11:35:59
 * @update 2019年12月26日 下午11:35:59
 * @version 1.0
 */
@Data
@TableName(value="SP_GOODS_CATEGORY3_TB")
public class GoodsCategory3 extends GoodsCategory implements Serializable {

    /**  描述   (@author: shilp) */
    private static final long serialVersionUID = 8814490822467472217L;
    /**主键*/
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long id;

    
    private String parentCode;

}