package com.dsys.common.sdk.function.event.listener;

import com.dsys.common.sdk.function.event.MailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Title: MailEventListener
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 邮件发送事件的监听(异步操作)
 * @created 2020/4/23 23:03
 */
@Component
@Slf4j
public class MailEventListener implements ApplicationListener<MailEvent>{
    @Override
    public void onApplicationEvent (MailEvent event){
        log.info("邮件发送代码");
    }
}
