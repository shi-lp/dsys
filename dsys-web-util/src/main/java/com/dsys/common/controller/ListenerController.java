package com.dsys.common.controller;

import com.dsys.common.sdk.function.event.MailEvent;
import com.dsys.common.sdk.response.RenderResponse;
import javax.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: ListenerController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 监听事件发布
 * @created 2020/4/24 9:14
 */
@RestController
public class ListenerController{
    
    @Resource
    private ApplicationContext applicationContext;
    
    @RequestMapping("/public/{function}")
    public RenderResponse publish(@PathVariable String function){
        applicationContext.publishEvent(new MailEvent("邮件事件发布"));
        return RenderResponse.success("邮件发送事件");
    }
}
