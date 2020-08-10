package com.dsys.common.sdk.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * Title: RedisKeyExpirationListener
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 监听所有的db过期事件，--- keyevent@*:expired
 * @created 2020/6/22 14:57
 */
//@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener{
    
    /**
     * Creates new {@link MessageListener} for {@code __keyevent@*__:expired} messages.
     * @param listenerContainer must not be {@literal null}.
     */
    public RedisKeyExpirationListener (RedisMessageListenerContainer listenerContainer){
        super(listenerContainer);
    }
    
    public void onMessage(Message message,Byte[] pattern){
        String expiredKey = message.toString();
        System.out.println(expiredKey);
    }
}
