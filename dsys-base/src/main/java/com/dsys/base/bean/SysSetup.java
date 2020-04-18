package com.dsys.base.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.dsys.common.model.BaseModel;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Package: com.dsys.base.bean
 * Description：
 * Author: shilp
 * Date:  2019/12/26 1:07
 * Modified By:
 */
@Data
public class SysSetup extends BaseModel{
    
    @TableId(value="id",type= IdType.INPUT)
    private Long sId;

    private String sysName;

    private String sysCode;

    private String sysValue;

    private Integer sysOrder;

    private String expriationTime;

    private String activeTime;

}
