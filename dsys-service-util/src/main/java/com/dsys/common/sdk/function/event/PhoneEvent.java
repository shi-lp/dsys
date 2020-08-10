package com.dsys.common.sdk.function.event;

import org.springframework.context.ApplicationEvent;

/**
 * Title: PhoneEvent
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 短信发送事件
 * @created 2020/4/23 23:00
 */
public class PhoneEvent extends ApplicationEvent{
    /**
     * Create a new {@code ApplicationEvent}.
     * @param source the object on which the event initially occurred or with
     * which the event is associated (never {@code null})
     */
    public PhoneEvent (Object source){
        super(source);
    }
}
