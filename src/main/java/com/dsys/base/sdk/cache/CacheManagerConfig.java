package com.dsys.base.sdk.cache;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Package: com.dsys.base.sdk.cache
 * Description：
 * Author: shilp
 * Date:  2019/12/31 15:08
 * Modified By:
 */
@Slf4j
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class CacheManagerConfig {

    private final CacheProperties cacheProperties;

    CacheManagerConfig(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    /**
     * 缓存名，名称暗示了缓存时长 注意： 如果添加了新的缓存名，需要同时在下面的RedisCacheCustomizer#RedisCacheCustomizer里配置名称对应的缓存时长
     * ，时长为0代表永不过期；缓存名最好公司内部唯一，因为可能多个项目共用一个redis。
     *
     * @see RedisCacheCustomizer#customize(RedisCacheManager)
     */
    public interface CacheNames {
        /**
         * 15分钟缓存组
         */
        String CACHE_15MINS = "dsys:cache:15m";
        /**
         * 30分钟缓存组
         */
        String CACHE_30MINS = "dsys:cache:30m";
        /**
         * 60分钟缓存组
         */
        String CACHE_60MINS = "dsys:cache:60m";
        /**
         * 180分钟缓存组
         */
        String CACHE_180MINS = "dsys:cache:180m";
    }

    /**
     * cache的一些自定义配置
     */
    @Bean
    public RedisCacheCustomizer redisCacheManagerCustomizer() {
        return new RedisCacheCustomizer();
    }

    private static class RedisCacheCustomizer
            implements CacheManagerCustomizer <RedisCacheManager> {
        /**
         * CacheManager缓存自定义初始化比较早，尽量不要@autowired 其他spring 组件
         */
        @Override
        public void customize(RedisCacheManager cacheManager) {
            // 自定义缓存名对应的过期时间
            Map <String, Long> expires = new ImmutableMap.Builder <String, Long>()
                    .put(CacheNames.CACHE_15MINS, TimeUnit.MINUTES.toSeconds(15))
                    .put(CacheNames.CACHE_30MINS, TimeUnit.MINUTES.toSeconds(30))
                    .put(CacheNames.CACHE_60MINS, TimeUnit.MINUTES.toSeconds(60))
                    .put(CacheNames.CACHE_180MINS, TimeUnit.MINUTES.toSeconds(180)).build();
            // spring cache是根据cache name查找缓存过期时长的，如果找不到，则使用默认值
//            cacheManager.setDefaultExpiration(expires.get(CacheNames.CACHE_30MINS));
//            cacheManager.setExpires(expires);
        }
    }

}
