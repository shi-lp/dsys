package com.dsys.cim.service.impl;

import com.dsys.api.bean.cim.CustomerCredits;
import com.dsys.api.service.cim.ICustomerCreditsService;
import com.dsys.cim.mapper.CustomerCreditsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * cim_customer_credits_tb 用户积分表 服务实现类
 * </p>
 *
 * @author shilp
 * @since 2020-05-13
 */
@Service
public class CustomerCreditsServiceImpl extends ServiceImpl<CustomerCreditsMapper, CustomerCredits> implements ICustomerCreditsService{

}
