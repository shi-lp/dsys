package com.dsys.base.sdk.database.dataProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.dsys.base.sdk.database.druid.DruidProperties;
import com.dsys.base.sdk.database.hikari.HikariProperties;

import lombok.Data;

/**        
 * Title: DataProperties.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月4日 下午9:54:24 
 * @update 2019年12月4日 下午9:54:24 
 * @version 1.0
*/
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource", ignoreUnknownFields = false)
public class DataProperties {
	
	private String driverClass;
	
	private String url;
	
	private String username;
	
	private String password;
	
	private String type;
	
	private HikariProperties hikariProperties;
	
	private DruidProperties druidProperties;

}
