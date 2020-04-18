package com.dsys.cim.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class CustomerInfo{
    @TableId(value = "id",type = IdType.INPUT)
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

    private Byte dataLevel;

    private Integer levelNo;
    
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