package com.dsys.api.bean.cim;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Title: CreditsDetail
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/5/13 16:20
 */
@Data
@TableName("cim_credits_detail_tb")
public class CreditsDetail implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /**
     * s_id 主键
     */
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long sId;
    
    /**
     * customer_id 用户id
     */
    private Long customerId;
    
    /**
     * credits_state 积分状态（新增还是消耗）
     */
    private String creditsState;
    
    /**
     * credits_rule_id 积分规则ID
     */
    private Long creditsRuleId;
    
    /**
     * credits_value 积分值
     */
    private Integer creditsValue;
    
    /**
     * credits_time 积分时间
     */
    private Timestamp creditsTime;
    
    /**
     * Order_No 排序号
     */
    private Integer orderNo;
    
    /**
     * invalid_time 失效日期
     */
    private Timestamp invalidTime;
    
    /**
     * Memo 注释
     */
    private String memo;
}
