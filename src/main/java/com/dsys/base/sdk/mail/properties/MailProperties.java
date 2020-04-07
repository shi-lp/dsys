package com.dsys.base.sdk.mail.properties;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**        
 * Title: MailProperties.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月14日 下午12:17:48 
 * @update 2019年12月14日 下午12:17:48 
 * @version 1.0
*/
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

	private String from;
	
	private String fromName;
	
	private List<String> to;
	
    private List<String> cc;
    
    private String subject;
	
    MailProperties(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.cc = builder.cc;
        this.subject = builder.subject;
        this.fromName = builder.fromName;
    }


}
