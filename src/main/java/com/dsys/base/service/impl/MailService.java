package com.dsys.base.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.dsys.base.sdk.mail.properties.AttachFile;
import com.dsys.base.sdk.mail.properties.InlineFile;
import com.dsys.base.sdk.mail.properties.JsonTable;
import com.dsys.base.sdk.mail.properties.MailProperties;
import com.dsys.base.sdk.mail.properties.MailType;
import com.dsys.base.sdk.mail.properties.TextString;
import com.dsys.common.IMailService;
import com.dsys.base.util.Constants;

/**        
 * Title: MailService.java    
 * Description: 
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月14日 下午12:15:46 
 * @update 2019年12月14日 下午12:15:46 
 * @version 1.0
*/
@Primary
@Service
public class MailService implements IMailService{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	static final String DELIM_STR = "{}";
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private MailProperties mailProperties;
	
	
	@Override
    public void sendSimpleMail(String to,String subject,String content,boolean isHtml) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailProperties.getFrom(), mailProperties.getFromName());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, isHtml);
			javaMailSender.send(message);
			log.info("html邮件发送成功");
		} catch (UnsupportedEncodingException e) {
			log.error("发送html邮件时发生异常！", e);
		} catch (MessagingException e) {
			log.error("发送html邮件时发生异常！", e);
			e.printStackTrace();
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		} 
	}
	
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getFrom(), mailProperties.getFromName());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            javaMailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
        	log.error("发送嵌入静态资源的邮件时发生异常！", e);
        } catch (UnsupportedEncodingException e) {
        	log.error("发送嵌入静态资源的邮件时发生异常！", e);
        } catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
    }
	
    public void sendAttachmentsMail(String to, String subject, String content, String[] filePaths) {
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(mailProperties.getFrom(), mailProperties.getFromName());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            for (String filePath : filePaths) {
                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
                helper.addAttachment(MimeUtility.encodeText(fileName), file);
            }

            javaMailSender.send(message);
            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
        	log.error("发送带附件的邮件时发生异常！", e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
    }

    /**
     * 完整发送邮件方法，需要调用setMailMessage方法配置邮件
     * <p>占位符使用{},内容中如果要使用{},就修改代码吧，这里不支持。
     * @param content
     *            带占位符正文
     * @param mailTypes
     *            可变参数，填充占位符
     */
    @Override
    public void sendCompleteHtml(String content,List<MailType> mailTypes,MailProperties mailMessage) {
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
        if (mailMessage.getFrom() == null || "".equals(mailMessage.getFrom())) {
            mailMessage.setFrom(mailProperties.getFrom());
            mailMessage.setFromName(mailProperties.getFromName());
        }
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
        	MimeMessageHelper helper = new MimeMessageHelper(message, true);
        	helper.setFrom(mailMessage.getFrom());
        	helper.setTo((InternetAddress) mailMessage.getTo());
        	if (mailMessage.getCc() != null && mailMessage.getCc().size() > 0){
                helper.setCc((InternetAddress) mailMessage.getCc());
            }
        	helper.setSubject(mailMessage.getSubject());
        	String msg = getContent(content, mailTypes);
        	helper.setText(msg, true);
        	for (MailType item : mailTypes) {
        		switch (item.getType()) {
        		case MailType.TYPE_FILE:
        			if (item != null) {
        				InlineFile inlineFile = (InlineFile) item;
        				helper.addInline(inlineFile.getCid(), new File(inlineFile.getFilePath()));
        			}
        			break;
        		case MailType.TYPE_ATTACH:
        			if (item != null) {
        				AttachFile attachFile = (AttachFile) item;
        				helper.addAttachment(MimeUtility.encodeText(attachFile.getFileName()),
        						new File(attachFile.getFilePath()));
        			}
        			break;
        		}
        	}
            javaMailSender.send(message);
        }catch(MailException e){
        	log.error("邮件发送过程出错，重发一次。",e);
            javaMailSender.send(message);
        } catch (javax.mail.MessagingException e) {
        	log.error("邮件发送过程出错，重发一次。",e);
            javaMailSender.send(message);
			e.printStackTrace();
		} catch (MessagingException e) {
			log.error("邮件发送过程出错，重发一次。",e);
            javaMailSender.send(message);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("邮件发送过程出错，重发一次。",e);
            javaMailSender.send(message);
			e.printStackTrace();
		}
        log.info("完整的邮件已经发送。");
    }

    /**
     * 解析占位符
     *
     * @param content
     *            字符串，带占位符{},有多少个{},就要有多少个MailType
     * @param mailTypes
     *            MailType填充参数, 注：换行需主动添加
     * @return 解析后的正文
     * @throws MessagingException
     * @throws IOException
     */
    private String getContent(String content, List<MailType> mailTypes) throws MessagingException, IOException {
        String bodyPrefix = "<html><body>";
        String bodySuffix = "</body></html>";
        StringBuffer sb = new StringBuffer();
        sb.append(bodyPrefix);
        for (MailType item : mailTypes) {
            if (content.length() < 1)
                break;

            int index = content.indexOf(DELIM_STR);
            if (index == -1)
                break;
            sb.append(content.substring(0, index));
            switch (item.getType()) {
            case MailType.TYPE_FILE:
                if (item != null) {
                    InlineFile inlineFile = (InlineFile) item;
                    sb.append("<img src=\'cid:" + inlineFile.getCid() + "\' />");
                }
                break;
            case MailType.TYPE_TEXT:
                TextString textString = (TextString) item;
                sb.append(textString.getText());
                break;
            case MailType.TYPE_JSON:
                JsonTable json = (JsonTable) item;
                sb.append(genReportData(json));

                break;
            }
            content = content.substring(index + 2);
        }
        sb.append(content);
        sb.append(bodySuffix);
        return sb.toString();
    }

    /**
     * 根据Json字符串，生成有序的表格
     *
     * @param jsonTable
     *            json转table实体
     * @return 表格字符串
     * @throws IOException
     */
    private String genReportData(JsonTable jsonTable) throws IOException {
        JSONArray ja = (JSONArray) JSON.parse(jsonTable.getData(), Feature.OrderedField);
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("<table border=\"1\" style=\"border-collapse:collapse;font-size:14px\">\n");
            sb.append("<caption align = \"left\">");
            sb.append(jsonTable.getTitle());
            sb.append("</caption>\n");
            JSONObject jsonFirst = (JSONObject) ja.get(0);
            sb.append("<tr>\n");
            for (String key : jsonFirst.keySet()) {
                sb.append("<td>");
                sb.append(jsonFirst.get(key));
                sb.append("</td>\n");
            }

            sb.append("</tr>\n");
            ja.remove(0);
            for (Object column : ja) {
                sb.append("<tr>\n");
                JSONObject json = (JSONObject) column;
                for (String key : jsonFirst.keySet()) {
                    sb.append("<td>");
                    sb.append(json.get(key));
                    sb.append("</td>\n");
                }

                sb.append("</tr>\n");
            }

            sb.append("</table>\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

	@Override
	public void sendEMail(String eMail, String signType) {
		if(Constants.MAIL_REGISTER.equals(signType)){// 注册
			
		}else{//验证
			
		}
	}

	
}
