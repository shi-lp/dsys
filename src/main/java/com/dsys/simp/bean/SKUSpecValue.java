package com.dsys.simp.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class SKUSpecValue implements Serializable {

    /**  描述   (@author: shilp) */

    private static final long serialVersionUID = 8085983202437520154L;

    // SKU编码
    private String id;

    //
    private String skuCode;

    // 规格值编码
    private String specValueCode;

    // 当前信息是否生效 1、生效 0、未生效 2、被删除
    private String doFlag;

    //
    private String createUser;

    //
    private Timestamp createTime;

    //
    private String updateUser;

    //
    private Timestamp updateTime;

}