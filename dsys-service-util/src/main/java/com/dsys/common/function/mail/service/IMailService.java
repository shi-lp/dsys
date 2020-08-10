package com.dsys.common.function.mail.service;

import com.dsys.common.sdk.sms.properties.MailProperties;
import com.dsys.common.sdk.sms.properties.MailType;
import java.util.List;

/**
 * Title: IMailService.java
 * Description: 邮件发送接口
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月14日 下午3:11:16
 * @update 2019年12月14日 下午3:11:16
 */
public interface IMailService {

    /**
     * @param to      指定收件人
     * @param subject 主题
     * @param content 内容
     * @param isHtml  是否是html
     * @discription 发送Html邮件
     * @author shilp
     * @created 2019年12月14日 下午12:21:29
     * @update 2019年12月14日 下午12:21:29
     */
    public void sendSimpleMail (String to,String subject,String content,boolean isHtml);

    /**
     * @param to      指定收件人
     * @param subject 主题
     * @param content 内容
     * @param rscPath 资源路径（文件路径）
     * @param rscId   资源标识
     * @discription 发送内嵌文件方法
     * @author shilp
     * @created 2019年12月14日 下午12:22:27
     * @update 2019年12月14日 下午12:22:27
     */
    public void sendInlineResourceMail (String to,String subject,String content,String rscPath,String rscId);

    /**
     * 简单发送附件方法
     *
     * @param to       指定收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件地址，可传递多个
     */
    public void sendAttachmentsMail (String to,String subject,String content,String[] filePaths);

    /**
     * 完整发送邮件方法，需要调用setMailMessage方法配置邮件
     * <p>占位符使用{},内容中如果要使用{},就修改代码吧，这里不支持。
     *
     * @param content   带占位符正文
     * @param mailTypes 可变参数，填充占位符
     * @throws Exception
     */
    public void sendCompleteHtml (String content,List<MailType> mailTypes,MailProperties mailMessage);


    /**
     * @param eMail    邮件接收
     * @param signType 邮箱注册/验证
     * @discription 发送邮件
     * @author shilp
     * @created 2019年12月15日 下午5:21:54
     * @update 2019年12月15日 下午5:21:54
     */
    public void sendEMail (String eMail,String signType);
}
