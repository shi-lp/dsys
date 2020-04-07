package com.dsys.base.bean.sys;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * ClassName: DataDictionary 
 * @Description: 数据字典
 * @author shilp
 * @date 2019年11月5日
 */
public class DataDictionary {
	
	@Getter @Setter
	private String id;

	@Getter @Setter
	private String code;
	
	@Getter @Setter
	private String parentCode;
	
	@Getter @Setter
	private String note;
	
	
}
