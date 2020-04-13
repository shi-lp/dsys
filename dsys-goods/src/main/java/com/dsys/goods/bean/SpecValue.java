package com.dsys.goods.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 *
 * Title: SpecValue.java
 * Description: 规格值表
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月26日 下午11:53:57
 * @update 2019年12月26日 下午11:53:57
 * @version 1.0
 */
@Data
public class SpecValue implements Serializable {

    /** 描述 (@author: shilp) */

    private static final long serialVersionUID = -5781340918836539037L;

    // 主键
    private String id;

    // 规格编码
    private String specCode;

    // 规格值
    private String specValue;

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