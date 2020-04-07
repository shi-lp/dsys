package com.dsys.simp.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Data
public class GoodsBrand implements Serializable {

    /**
     * 描述   (@author: shilp)
     */
    private static final long serialVersionUID = -88388726742348865L;
    // 主键
    private String id;

    // 品牌编码
    private String brandCode;

    // 品牌名称
    private String brandName;

    // 图片路径
    private String pgUrl;

    // 当前信息是否生效 1、生效 0、未生效 2、被删除
    private String doFlag;

    // 创建人
    private String createUser;

    // 创建时间
    private Timestamp createTime;

    // 更新人
    private String updateUser;

    // 更新时间
    private Timestamp updateTime;

}