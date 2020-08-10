package com.dsys.common.sdk.function.event;

import com.dsys.common.sdk.sms.properties.MailType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.ApplicationEvent;

/**
 * Title: MailEvent
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 创建邮件发送事件监听，实现ApplicationEvent构造函数，并且注册监听对象
 * @created 2020/4/23 22:59
 */
@Getter
@Setter
public class MailEvent extends ApplicationEvent{
    
    /**
     *指定收件人
    */
    private String to;
    
    /**
     *主题
     */
    private String subject;
    
    /**
     *内容
     */
    private String content;
    
    /**
     *是否是html
     */
    private boolean isHtml;
    
    /**
     *资源路径（文件路径）
     */
    private String rscPath;
    
    /**
     *资源标识
     */
    private String rscId;
    
    /**
     *附件地址，可传递多个
     */
    private String[] filePaths;
    
    /**
     *可变参数，填充占位符
     */
    private List<MailType> mailTypes;
    
    /**
     *
     */
    private MailProperties mailMessage;
    
    /**
     *邮件接收
     */
    private String eMail;
    
    /**
     *邮箱注册/验证
     */
    private String signType;
    
    /**
     * Create a new {@code ApplicationEvent}.
     * @param source the object on which the event initially occurred or with
     * which the event is associated (never {@code null})
     */
    public MailEvent (Object source,String to,String subject,String content,boolean isHtml){
        super(source);
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.isHtml = isHtml;
//        this.rscPath = rscPath;
//        this.rscId = rscId;
//        this.filePaths = filePaths;
//        this.mailTypes = mailTypes;
//        this.mailMessage = mailMessage;
//        this.eMail = eMail;
//        this.signType = signType;
    }
    
    
}
