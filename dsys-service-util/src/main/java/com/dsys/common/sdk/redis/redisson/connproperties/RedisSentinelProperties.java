package com.dsys.common.sdk.redis.redisson.connproperties;

import lombok.Data;
import lombok.ToString;

@Data
public class RedisSentinelProperties {

	/**
     * 哨兵master 名称
     */
    private String master;

    /**
     * 哨兵节点
     */
    private String nodes;

    /**
     * 哨兵配置
     */
    private boolean masterOnlyWrite;

    /**
     *
     */
    private int failMax;
}
