package com.dsys.base.sdk.database.druid;

import lombok.Data;
import lombok.ToString;

/**
 * Title: DruidProperties.java Description: 描述
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月12日 上午10:45:33
 * @update 2019年12月12日 上午10:45:33
 * @version 1.0
 */
@Data
@ToString
public class DruidProperties {

	private int initialSize;
	
	private int minIdle;
	
	private int maxActive;
	
	private int maxWait;
	
	private int timeBetweenEvictionRunsMillis;
	
	private int minEvictableIdleTimeMillis;
	
	private String validationQuery;
	
	private boolean testWhileIdle;
	
	private boolean testOnBorrow;
	
	private boolean testOnReturn;
	
	private boolean poolPreparedStatements;
	
	private int maxPoolPreparedStatementPerConnectionSize;
	
	private String filters;
	
	private String connectionProperties;
	
	private boolean useGlobalDataSourceStat;
}
