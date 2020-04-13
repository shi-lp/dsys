package com.dsys.goods.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Title: Shop.java Description: 商铺
 *
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月26日 下午11:49:09
 * @update 2019年12月26日 下午11:49:09
 */
@Data
public class Shop implements Serializable {


    /**
     * 描述 (@author: shilp)
     */

    private static final long serialVersionUID = -3085140485405744273L;

    // 主键
    private String id;

    // 商铺编码
    private String shopCode;

    // 商铺名称
    private String shopName;

    // 当前信息是否生效 1、生效 0、未生效 2、被删除
    private String doFlag;

    // 创建人
    private String createUser;

    // 创建时间
    private Timestamp createTime;

    // 修改人
    private String updateUser;

    // 修改时间
    private Timestamp updateTime;

}