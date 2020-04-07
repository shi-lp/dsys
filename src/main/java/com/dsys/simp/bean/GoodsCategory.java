package com.dsys.simp.bean;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

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
public class GoodsCategory implements Serializable {

    /**  描述   (@author: shilp) */
    private static final long serialVersionUID = 8814490822467472217L;
    // 主键
    private String id;

    // 分类名称
    private String categoryName;

    // 分类编码
    private String categoryCode;

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