package com.dsys.common.sdk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: ContextListener
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: application级别，整个应用只存在一个，可以进行全局应用配置
 * @created 2020/4/23 15:59
 */
@WebListener
@Slf4j
public class ContextListener implements ServletContextListener{
    
    @Override
    public void contextInitialized (ServletContextEvent sce){
    
    }
    
    @Override
    public void contextDestroyed (ServletContextEvent sce){
    
    }
}
