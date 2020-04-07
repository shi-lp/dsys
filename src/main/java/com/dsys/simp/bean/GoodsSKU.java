package com.dsys.simp.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
@Data
public class GoodsSKU implements Serializable {

    /**  描述   (@author: shilp) */

    private static final long serialVersionUID = -7135214566083926835L;

    // 主键
    private	String	id;

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

    private String doFlag;

    private String createUser;

    private Timestamp createTime;

    private String updateUser;

    private Timestamp updateTime;

}