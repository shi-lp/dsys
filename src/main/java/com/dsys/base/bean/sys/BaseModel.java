package com.dsys.base.bean.sys;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Title: BaseModel
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 数据模型基类
 * @created 2020/3/24 10:31
 */
@Data
public class BaseModel implements Serializable{
    
    private String redisCode;
    // 创建人
    private String createUser;
    
    // 创建时间
    private Timestamp createTime;
    
    // 修改人
    private String updateUser;
    
    // 修改时间
    private Timestamp updateTime;

}
