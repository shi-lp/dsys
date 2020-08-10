package com.dsys.api.bean.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.api.common.BaseModel;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Package: com.dsys.base.bean
 * Description：
 * Author: shilp
 * Date:  2019/12/26 1:07
 * Modified By:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "SM_SYS_TB")
public class SysSetup extends BaseModel implements Serializable{
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type= IdType.INPUT)
    private Long sId;

    private String sysName;

    private String sysCode;

    private String sysValue;

    private Integer sysOrder;

    private Timestamp expriationTime;

    private Timestamp activeTime;

}
