package com.dsys.api.bean.cim;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * Title: CustomerCredits
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 用户积分表
 * @created 2020/5/13 16:09
 */
@Data
@TableName("cim_customer_credits_tb")
public class CustomerCredits implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long sId;
    
    /**
     * 用户ID
     */
    private Long customerId;
    
    /**
     * credits 积分
     */
    private Long credits;
    
    /**
     * memo 备注
     */
    private String memo;
}
