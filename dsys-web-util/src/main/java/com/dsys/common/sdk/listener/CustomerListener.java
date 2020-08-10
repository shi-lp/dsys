package com.dsys.common.sdk.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: CustomerListener
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: request级别，针对每一个客户请求
 * @created 2020/4/23 15:52
 */
@WebListener
@Slf4j
public class CustomerListener implements ServletRequestListener{
    
    @Override
    public void requestInitialized (ServletRequestEvent sre){
        log.info("request监听器：可以记录访问次数");
    }
    
    @Override
    public void requestDestroyed (ServletRequestEvent sre){
        log.info("request监听器：销毁");
    }
}
