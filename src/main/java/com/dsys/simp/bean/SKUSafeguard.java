package com.dsys.simp.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class SKUSafeguard implements Serializable {

    /** 描述 (@author: shilp) */

    private static final long serialVersionUID = -1118517844913225623L;

    //
    private String id;

    //
    private String skuCode;

    //
    private String safeguardCode;

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