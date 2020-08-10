package com.dsys.common.sdk.cache.ehcache.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Title: EhcacheProperties
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: ehcache配置
 * @created 2020/6/1 15:13
 */
@Data
@ConfigurationProperties(prefix = "cache.ehcache")
public class EhcacheProperties{
    
    /**
     * ehcache heap大小
     * jvm内存中缓存的key数量
     */
    private Long heap;
    /**
     * ehcache offheap大小
     * 堆外内存大小, 单位: MB
     */
    private Long offheap;
    /**
     * 磁盘持久化目录
     */
    private String diskDir;
    /**
     * ehcache disk
     * 持久化到磁盘的大小, 单位: MB
     * diskDir有效时才生效
     */
    private Long disk;
    /**
     * 缓存过期时间, 单位: 秒
     * 0表示不过期
     */
    private Long ttl;

}
