package com.dsys.common.sdk.function.event;

import java.util.List;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.ApplicationEvent;

/**
 * Title: MailEvent
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 邮件发送事件
 * @created 2020/4/23 22:59
 */
public class MailEvent extends ApplicationEvent{
    
   
    
    
    /**
     * Create a new {@code ApplicationEvent}.
     * @param source the object on which the event initially occurred or with
     * which the event is associated (never {@code null})
     */
    public MailEvent (Object source){
        super(source);
    }
}
