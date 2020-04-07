package com.dsys.base.sdk.mail.properties;

  
/**        
 * Title: MailType.java    
 * Description: 邮件类型
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月14日 下午12:30:53 
 * @update 2019年12月14日 下午12:30:53 
 * @version 1.0
*/

public abstract class MailType {

	public final static char TYPE_FILE = 'F';
    public final static char TYPE_ATTACH = 'A';
    public final static char TYPE_TEXT = 'T';
    public final static char TYPE_JSON = 'J';

    public abstract char getType();
}
