package com.dsys.api.bean.cim;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Title: CreditsRule
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 积分规则对象表
 * @created 2020/5/13 16:14
 */
@Data
@TableName("cim_credits_rule_tb")
public class CreditsRule implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * s_id 主键
     */
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long sId;
    
    /**
     * rule_id 积分规则ID
     */
    private String ruleId;
    
    /**
     * rule_index_id 积分规则索引id
     */
    private Long ruleIndexId;
    
    /**
     * set_credits_upper 设置每日积分上限
     */
    private String setCreditsUpper;
    
    /**
     * credits_upper_value 每日积分上限值
     */
    private String creditsUpperValue;
    
    /**
     * start_credits_time 积分规则开始时间
     */
    private LocalDateTime startCreditsTime;
    
    /**
     * end_credits_time 积分规则结束时间
     */
    private String endCreditsTime;
    
    /**
     * memo 备注
     */
    private String memo;
}
