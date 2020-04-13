package com.dsys.cim.controller;

import com.dsys.cim.service.ICustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Title: CustomerRegisterController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/10 16:53
 */
@Controller
public class CustomerRegisterController{
    @Autowired
    private ICustomerInfo customerInfoService;
}
