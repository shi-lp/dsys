package com.dsys.cim.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dsys.common.model.BaseModel;
import lombok.Data;

/**     
 * @discription
 * @author shilp       
 * @created 2020/4/13  10:39
 * @Param 
 * @Return 
*/
@Data
@TableName("cim_receive_address")
public class ReceiveAddress extends BaseModel{
    
    @TableId(value="id",type= IdType.INPUT)
    private Long sId;

    private Long customerId;

    private String addresseeName;

    private Long phoneNumber;

    private String telephoneNumber;

    private Byte defaultStatus;

    private Long postCode;

    private String countryCn;

    private String countryCode;

    private String provinceCn;

    private String cityCn;

    private String regionCn;

    private String detailAddress;

    private Byte dataLevel;

    private Double orderNo;

    
}