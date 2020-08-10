package com.dsys.common.sdk.function.event.listener;

import com.dsys.common.sdk.function.event.PhoneEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Title: PhoneEventListener
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 短信发送事件监听
 * @created 2020/4/23 23:04
 */
@Component
@Slf4j
public class PhoneEventListener implements ApplicationListener<PhoneEvent>{
    @Override
    public void onApplicationEvent (PhoneEvent event){
        log.info("短信发送代码");
    }
}
