package com.dsys.common.sdk.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: SessionListener
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: session级别，针对每一个对话，如统计会话总数
 * @created 2020/4/23 15:55
 */
@WebListener
@Slf4j
public class SessionListener implements HttpSessionListener{
    
    @Override
    public void sessionCreated (HttpSessionEvent se){
    
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
