package com.dsys.base.util.code;

import org.springframework.beans.factory.annotation.Autowired;

import com.dsys.base.service.impl.UidServiceImpl;

/**
 * 
 * ClassName: CodeUtils 
 * @Description: 编码生成工具类
 * @author shilp
 * @date 2019年11月5日
 */
public class CodeUtils {

	@Autowired
    private UidServiceImpl uidService;
	
	public String getUniqueId(){
		return String.valueOf(uidService.getUid());
	}
}
