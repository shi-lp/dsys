package com.dsys.goods.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 *
 * Title: GoodsSpu.java
 * Description: 商品SPU
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @created 2019年12月26日 下午11:47:37
 * @update 2019年12月26日 下午11:47:37
 * @version 1.0
 */
@Data
public class GoodsSpu implements Serializable {
    /**
     * 描述   (@author: shilp)
     */

    private static final long serialVersionUID = 2598026466535753137L;

    //
    private String id;

    // SPU编码
    private String spuNo;

    //
    private String goodsName;

    // 品牌编码
    private String brandCode;

    // 分类编码
    private String categoryCode;

    // 当前信息是否生效 1、生效 0、未生效 2、被删除
    private String doFlag;

    // 创建人
    private String createUser;

    // 创建时间
    private Timestamp createTime;

    // 更新用户
    private String updateUser;

    // 更新时间
    private Timestamp updateTime;
}