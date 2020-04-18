package com.dsys.cim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.cim.bean.ReceiveAddress;
import java.util.List;

/**
 * Title: IReceiveAddressService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/13 20:27
 */
public interface IReceiveAddressService extends IService<ReceiveAddress>{
    
    /**
     * @discription 根据客户ID获取收获地址列表
     * @author shilp
     * @created 2020/4/17  9:37
     * @Param userId:用户Id
     * @Return 收获地址列表
    */
    public List<ReceiveAddress> getAddressByCustomerId (Long userId);
}
