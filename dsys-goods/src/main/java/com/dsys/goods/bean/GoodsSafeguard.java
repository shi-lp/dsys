package com.dsys.goods.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Data;

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
@Data
public class GoodsSafeguard implements Serializable {

    /**  描述   (@author: shilp) */

    private static final long serialVersionUID = 1657563595640703115L;
    private String id;

    // 增值保障编码
    private String safeguardCode;

    // 增值保障名称
    private String safeguardName;

    // 保障价格
    private BigDecimal price;

    private String doFlag;

    private String createUser;

    private Timestamp createTime;

    private String updateUser;

    private Timestamp updateTime;
}