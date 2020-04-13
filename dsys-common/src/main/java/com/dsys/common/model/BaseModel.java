package com.dsys.common.model;

import com.baomidou.mybatisplus.annotations.TableField;
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
    
    @TableField(exist = false)
    private String redisCode;
    // 创建人
    private Long createUser;
    
    // 创建时间
    private Timestamp createTime;
    
    // 修改人
    private Long updateUser;
    
    // 修改时间
    private Timestamp updateTime;

}
