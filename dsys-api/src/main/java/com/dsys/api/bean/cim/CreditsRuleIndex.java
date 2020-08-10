package com.dsys.api.bean.cim;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
import java.io.Serializable;
import lombok.Data;

/**
 * Title: CreditsRuleIndex
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 积分规则索引表
 * @created 2020/5/13 16:16
 */
@Data
@TableName("cim_credits_rule_index_tb")
public class CreditsRuleIndex extends BaseModel implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * s_id 主键
     */
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long sId;
    
    /**
     * credits_code 规则序号
     */
    private String creditsCode;
    
    /**
     * credits_content 积分规则内容
     */
    private String creditsContent;
    
    /**
     * memo 备注
     */
    private String memo;
}
