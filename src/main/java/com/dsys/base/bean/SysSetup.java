package com.dsys.base.bean;

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
public class SysSetup {

    private String sysName;

    private String sysCode;

    private String sysValue;

    private Integer sysOrder;

    private String expriationTime;

    private String activeTime;

    private String createUser;

    private Timestamp createTime;

    private String updateUser;

    private Timestamp updateTime;
}
