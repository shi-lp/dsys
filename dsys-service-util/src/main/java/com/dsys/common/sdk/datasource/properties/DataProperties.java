package com.dsys.common.sdk.datasource.properties;

import com.dsys.common.sdk.datasource.druid.DruidProperties;
import com.dsys.common.sdk.datasource.hikari.HikariProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
