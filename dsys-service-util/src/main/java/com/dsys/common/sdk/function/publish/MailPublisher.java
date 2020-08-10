package com.dsys.common.sdk.function.publish;

import com.dsys.common.sdk.function.event.MailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Title: MailPublisher
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 邮件事件发布
 * @created 2020/6/17 10:08
 */
@Component
public class MailPublisher{
    
    @Autowired
    ApplicationContext applicationContext;
    
    public void publisher(MailEvent mailEvent){
        applicationContext.publishEvent(mailEvent);
    }
}
