package com.dsys.common.phone.service;

import com.aliyuncs.exceptions.ClientException;
import java.util.Map;

/**        
 * Title: IPhoneService.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月15日 下午5:20:28 
 * @update 2019年12月15日 下午5:20:28 
 * @version 1.0
*/
public interface IPhoneService {

	  
	/**     
	 * @discription 短信发送
	 * @author shilp       
	 * @created 2019年12月15日 下午5:24:02  
	 * @update 2019年12月15日 下午5:24:02   
	 * @param phoneNumber 手机号
	 * @param signType   注册/验证
	*/
	Map <String,Object> sendSMS(String phoneNumber, String signType) throws ClientException;

}
