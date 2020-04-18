package com.dsys.base.controller;

import com.aliyuncs.exceptions.ClientException;
import com.dsys.common.mail.service.IMailService;
import com.dsys.common.phone.service.IPhoneService;
import com.dsys.common.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**        
 * Title: SignController.java    
 * Description: 验证控制类
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月15日 下午4:37:59 
 * @update 2019年12月15日 下午4:37:59 
 * @version 1.0
*/
@RestController
@RequestMapping("/sign")
public class SignController {
	
	private static final Logger log = LoggerFactory.getLogger(SignController.class);
	
	@Autowired
	private IMailService mailService;
	
	private IPhoneService phoneService;
	
	/**
	 * 
	 * @discription 发送邮箱验证
	 * @author shilp       
	 * @created 2019年12月15日 下午4:40:25  
	 * @update 2019年12月15日 下午4:40:25   
	 * @param eMail
	 * @return
	 */
	@PostMapping("/sendEMail")
	public Map<String,Object> sendEMail(@RequestParam("eMail") String eMail){
		Map<String,Object> renderJson = new HashMap<String,Object>();
		mailService.sendEMail(eMail,Constants.MAIL_REGISTER);
		return renderJson;
	}

	/**
	 * 
	 * @discription 邮箱验证
	 * @author shilp       
	 * @created 2019年12月15日 下午4:40:25  
	 * @update 2019年12月15日 下午4:40:25   
	 * @param eMail
	 * @return
	 */
	@PostMapping("/signEMail")
	public Map<String,Object> signEMail(@RequestParam("eMail") String eMail){
		Map<String,Object> renderJson = new HashMap<String,Object>();
		return renderJson;
	}
	
	/**
	 * 
	 * @discription 发送手机号验证
	 * @author shilp       
	 * @created 2019年12月15日 下午4:40:25  
	 * @update 2019年12月15日 下午4:40:25   
	 * @param phoneNumber
	 * @return
	 */
	@PostMapping("/sendPhone")
	public Map<String,Object> sendPhone(@RequestParam("phoneNumber") String phoneNumber) throws ClientException{
		Map<String,Object> renderJson = new HashMap<String,Object>();
		Map<String,Object> returnPhone = phoneService.sendSMS(phoneNumber,Constants.PHONE_REGISTER);
		return renderJson;
	}
	
	/**
	 * 
	 * @discription 手机号验证
	 * @author shilp       
	 * @created 2019年12月15日 下午4:40:25  
	 * @update 2019年12月15日 下午4:40:25   
	 * @param phoneNumber
	 * @return
	 */
	@PostMapping("/signPhone")
	public Map<String,Object> signPhone(@RequestParam("phoneNumber") String phoneNumber){
		Map<String,Object> renderJson = new HashMap<String,Object>();
		return renderJson;
	}
}
