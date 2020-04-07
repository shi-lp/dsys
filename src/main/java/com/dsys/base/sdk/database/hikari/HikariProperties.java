package com.dsys.base.sdk.database.hikari;

import lombok.Data;
import lombok.ToString;

/**        
 * Title: HikariProperties.java    
 * Description: Hikari连接池配置
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月4日 下午9:47:09 
 * @update 2019年12月4日 下午9:47:09 
 * @version 1.0
*/
@Data
@ToString
public class HikariProperties {
	
	private boolean autocommit;
	
	private int maximumPoolSize;
	
	private int minimumIdle;
	
	private long idleTimeout;
	
	private long connectionTimeout;
	
	private long maxLifetime;
}
