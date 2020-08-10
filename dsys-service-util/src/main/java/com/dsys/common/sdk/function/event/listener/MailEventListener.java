package com.dsys.common.sdk.function.event.listener;

import com.dsys.common.function.mail.service.IMailService;
import com.dsys.common.sdk.function.event.MailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private IMailService mailService;
    
    @Override
    public void onApplicationEvent (MailEvent event){
        log.info("---接收到mailPublisher发送的邮件发布请求---");
        mailService.sendSimpleMail(event.getTo(),event.getSubject(),event.getContent(),event.isHtml());
        log.info("---邮件发布完成---");
    }
}
