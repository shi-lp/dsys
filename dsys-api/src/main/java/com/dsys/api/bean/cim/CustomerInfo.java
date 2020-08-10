package com.dsys.api.bean.cim;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**     
 * @discription 客户信息表
 * @author shilp       
 * @created 2020/4/13  11:04
 * @Param 
 * @Return 
*/
@Data
@TableName("cim_customer_info")
public class CustomerInfo implements Serializable{
    
    @JSONField(serializeUsing= ToStringSerializer.class)
    @TableId(type = IdType.INPUT)
    private Long sId;

    private String name;

    private String account;

    private String phoneNumber;

    private String email;

    private Integer onlineStatus;

    private Short loginDrive;

    private String loginIp;

    private Date signInDate;

    private Date logoutDate;

    private Date lastSignInDate;

    private Date createDate;
    
    /**
     * 0正常 1审核中 2被否决 -1已删除
     */
    private Byte dataLevel;
    
    /**
     * 用户级别
     */
    private Integer levelNo;
    
    /**
     * 用户来源
    */
    private String sourceType;
    
    /**
     * 用户Token信息
    */
    private String accessToken;
    
    /**     
     * @discription 字段不在数据库实体表中
     * @author shilp       
     * @created 2020/4/14  16:00
     * @Param 
     * @Return 
    */
    @TableField(exist = false)
    private String token;

}