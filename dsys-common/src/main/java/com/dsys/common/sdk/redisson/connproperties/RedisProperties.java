package com.dsys.common.sdk.redisson.connproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
 * Title: Redis集成Redisson配置信息
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年11月27日 下午10:20:35
 * @update 2019年11月27日 下午10:20:35
 * @version 1.0
 */
@Data
//@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

	private String host;

	private int port;

	private int database;

	/**
	 * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
	 */
	private int timeout;

	private String password;

	private String mode;

	/**
	 * 池配置
	 */
	private RedisPoolProperties pool;

	/**
	 * 单机信息配置
	 */
	private RedisSingleProperties single;

	/**
	 * 集群 信息配置
	 */
	private RedisClusterProperties cluster;

	/**
	 * 哨兵配置
	 */
	private RedisSentinelProperties sentinel;

}
