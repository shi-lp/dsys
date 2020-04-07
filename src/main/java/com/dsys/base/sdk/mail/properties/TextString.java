   
/**     
 * @discription 在此输入一句话描述此文件的作用
 * @author shilp       
 * @created 2019年12月14日 下午12:32:07
 * @update 2019年12月14日 下午12:32:07    
 * tags     
 * see_to_target     
*/
    
package com.dsys.base.sdk.mail.properties;

  
/**        
 * Title: TextString.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月14日 下午12:32:07 
 * @update 2019年12月14日 下午12:32:07 
 * @version 1.0
*/

public class TextString extends MailType{

	private String text;
	
	public TextString() {
        super();
    }
	
	public TextString(String text) {
        super();
        this.text = text;
    }
	  
	@Override
	public char getType() {
		return MailType.TYPE_TEXT;
	}
	
	public String getText() {
        return text;
    }
	
	public void setText(String text) {
        this.text = text;
    }


}
