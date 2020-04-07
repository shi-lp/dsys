package com.dsys.base.sdk.redisson;

import com.alibaba.fastjson.JSON;
import com.dsys.base.bean.sys.BaseModel;
import com.dsys.base.util.ArrayUtil;
import com.dsys.base.util.Constants;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dsys.base.util.serializer.FastJsonRedisSerializer;
import com.dsys.base.util.serializer.StringRedisSerializer;
import java.util.stream.Collectors;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.dsys.base.sdk.redisson.connproperties.RedisProperties;
import com.dsys.base.sdk.redisson.lock.IDistributedLocker;
import com.dsys.base.sdk.redisson.lock.impl.RedissonDistributedLockerImpl;
import com.dsys.base.sdk.redisson.util.RedissLockUtil;
import com.dsys.base.util.StringUtils;

/**
 *
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport{

    @Autowired
    private RedisProperties redisProperties;
    
    /**
     * 自定义KEY生成器，格式： jww:data:[包名 + 类名 + 方法名+ 参数]
     * 如：jww:data:com.jww.common.redis.RedisConfig:queryList_param1_param2
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
    
            @Override
            public Object generate(Object target, Method method, Object... params) {
                CacheConfig cacheConfig = target.getClass().getAnnotation(CacheConfig.class);
                Cacheable cacheable = method.getAnnotation(Cacheable.class);
                CachePut cachePut = method.getAnnotation(CachePut.class);
                CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
                String cacheName = "";
                if (cacheable != null) {
                    String[] cacheNames = cacheable.value();
                    if (ArrayUtil.isNotEmpty(cacheNames)) {
                        cacheName = cacheNames[0];
                    }
                } else if (cachePut != null) {
                    String[] cacheNames = cachePut.value();
                    if (ArrayUtil.isNotEmpty(cacheNames)) {
                        cacheName = cacheNames[0];
                    }
                } else if (cacheEvict != null) {
                    String[] cacheNames = cacheEvict.value();
                    if (ArrayUtil.isNotEmpty(cacheNames)) {
                        cacheName = cacheNames[0];
                    }
                }
                if (cacheConfig != null && StringUtil.isBlank(cacheName)) {
                    String[] cacheNames = cacheConfig.cacheNames();
                    if (ArrayUtil.isNotEmpty(cacheNames)) {
                        cacheName = cacheNames[0];
                    }
                }
                if (StringUtil.isBlank(cacheName)) {
                    cacheName = "default";
                }
                String paramStr = getParamStr(params);
                StringBuilder sb = new StringBuilder();
                sb.append(Constants.CacheNamespaceEnum.DATA.value())
                        .append(cacheName).append(":")
                        .append(target.getClass().getName())
                        .append(":").append(method.getName())
                        .append("_").append(paramStr);
                return sb.toString();
            }
        
            /** 获取参数串（BaseModel取ID），以下划线连线 */
            private String getParamStr(Object[] params) {
                if (ArrayUtil.isEmpty(params)) {
                    return "";
                }
                String paramStr = "";
                for (Object param : params) {
                    //BaseModel类型，取ID
                    if (param instanceof BaseModel) {
                        BaseModel model = (BaseModel) param;
                        paramStr = String.join("_", paramStr, String.valueOf(model.getRedisCode()));
                    } else if (ArrayUtil.isArray(param)) {
                        //数组类型，将各元素序列化为字符串
                        Object[] arrs = (Object[]) param;
                        paramStr = Arrays.stream(arrs).map(JSON::toJSONString).collect(Collectors.joining("_"));
                    } else {
                        //其它类型，直接序列化为字符串
                        paramStr = String.join("_", paramStr, JSON.toJSONString(param));
                    }
                }
                return paramStr;
            }
        };
    }

    @ConditionalOnClass({Redisson.class})
    @ConditionalOnExpression("'${spring.redis.mode}'=='single' or '${spring.redis.mode}'=='cluster' or '${spring.redis.mode}'=='sentinel'")
    protected class RedissonSingleClientConfiguration {

        /**
         * 单机模式 redisson 客户端
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single")
        RedissonClient redissonSingle() {
            log.info("开始读取单机模式redisson配置");
            Config config = new Config();
            String node = redisProperties.getSingle().getAddress();
            node = node.startsWith("redis://") ? node : "redis://" + node;
            SingleServerConfig serverConfig = config.useSingleServer().setAddress(node)
                    .setTimeout(redisProperties.getPool().getConnTimeout())
                    .setConnectionPoolSize(redisProperties.getPool().getSize())
                    .setConnectionMinimumIdleSize(redisProperties.getPool().getMinIdle());
            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                serverConfig.setPassword(redisProperties.getPassword());
            }
            log.info("单机模式redisson配置加载完成");
            return Redisson.create(config);
        }

        /**
         * 集群模式的 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
        RedissonClient redissonCluster() {
            System.out.println("cluster redisProperties:" + redisProperties.getCluster());

            Config config = new Config();
            String[] nodes = redisProperties.getCluster().getNodes().split(",");
            List <String> newNodes = new ArrayList <String>(nodes.length);
            Arrays.stream(nodes)
                    .forEach((index) -> newNodes.add(index.startsWith("redis://") ? index : "redis://" + index));

            ClusterServersConfig serverConfig = config.useClusterServers()
                    .addNodeAddress(newNodes.toArray(new String[0]))
                    .setScanInterval(redisProperties.getCluster().getScanInterval())
                    .setIdleConnectionTimeout(redisProperties.getPool().getSoTimeout())
                    .setConnectTimeout(redisProperties.getPool().getConnTimeout())
                    .setFailedSlaveCheckInterval(redisProperties.getCluster().getFailedAttempts())
                    .setRetryAttempts(redisProperties.getCluster().getRetryAttempts())
                    .setRetryInterval(redisProperties.getCluster().getRetryInterval())
                    .setMasterConnectionPoolSize(redisProperties.getCluster().getMasterConnectionPoolSize())
                    .setSlaveConnectionPoolSize(redisProperties.getCluster().getSlaveConnectionPoolSize())
                    .setTimeout(redisProperties.getTimeout());
            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                serverConfig.setPassword(redisProperties.getPassword());
            }
            return Redisson.create(config);
        }

        /**
         * 哨兵模式 redisson 客户端
         *
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
        RedissonClient redissonSentinel() {
            System.out.println("sentinel redisProperties:" + redisProperties.getSentinel());
            Config config = new Config();
            String[] nodes = redisProperties.getSentinel().getNodes().split(",");
            List <String> newNodes = new ArrayList <String>(nodes.length);
            Arrays.stream(nodes)
                    .forEach((index) -> newNodes.add(index.startsWith("redis://") ? index : "redis://" + index));
            SentinelServersConfig serverConfig = config.useSentinelServers()
                    .addSentinelAddress(newNodes.toArray(new String[0]))
                    .setMasterName(redisProperties.getSentinel().getMaster()).setReadMode(ReadMode.SLAVE)
                    .setFailedSlaveCheckInterval(redisProperties.getSentinel().getFailMax())
                    .setTimeout(redisProperties.getTimeout())
                    .setMasterConnectionPoolSize(redisProperties.getPool().getSize())
                    .setSlaveConnectionPoolSize(redisProperties.getPool().getSize());

            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                serverConfig.setPassword(redisProperties.getPassword());
            }

            return Redisson.create(config);
        }
    }

    /**
     * 装配locker类，并将实例注入到RedissLockUtil中
     *
     * @return
     */
//    @Bean
    IDistributedLocker distributedLocker(RedissonClient redissonClient) {
        IDistributedLocker locker = new RedissonDistributedLockerImpl();
        locker.setRedissonClient(redissonClient);
        RedissLockUtil.setLocker(locker);
        return locker;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
//        RedisSerializer <Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
        RedisSerializer<Object> json = new Jackson2JsonRedisSerializer <Object>(Object.class);
        RedisSerializationContext.SerializationPair <Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(json);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(pair);
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofMinutes(60));
        //初始化RedisCacheManager
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
    
    @Bean
    public RedisTemplate <Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate <Object, Object> redisTemplate = new RedisTemplate <>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //使用Jackson2JsonRedisSerializer替换默认的序列化规则
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer <>(Object.class);
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);

        //设置value的序列化规则
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        //设置key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}