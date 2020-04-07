package com.dsys.base.sdk.redisson.connproperties;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * Title: RedisPoolProperties.java    
 * Description: redis连接池配置
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年11月27日 下午10:22:12 
 * @update 2019年11月27日 下午10:22:12 
 * @version 1.0
 */
@Data
@ToString
public class RedisPoolProperties {

	private int maxIdle;

	private int minIdle;

	private int maxActive;

	private int maxWait;

	private int connTimeout;

	private int soTimeout;

	/**
	 * 池大小
	 */
	private int size;
}
