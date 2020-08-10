package com.dsys.api.bean.home;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.enums.DoFlagEnum;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * Title: HomeBanner
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/14 16:41
 */
@Data
@TableName("cim_home_banner_tb")
public class HomeBanner implements Serializable{
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long sId;
    
    private String imgUrl;
    
    private String pathUrl;
    
    private String pathType;
    
    private DoFlagEnum doFlag;
    
    private Float orderNo;
    
    private Timestamp beginTime;
    
    private Timestamp endTime;
    
    private String saltValue;
    
}
