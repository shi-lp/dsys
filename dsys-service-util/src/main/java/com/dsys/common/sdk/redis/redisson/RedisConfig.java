package com.dsys.common.sdk.redis.redisson;

import com.dsys.common.sdk.redis.redisson.connproperties.RedisProperties;
import com.dsys.common.sdk.redis.redisson.lock.IDistributedLocker;
import com.dsys.common.sdk.redis.redisson.lock.impl.RedissonDistributedLockerImpl;
import com.dsys.common.sdk.redis.redisson.serializer.FastJsonRedisSerializer;
import com.dsys.common.sdk.redis.redisson.serializer.StringRedisSerializer;
import com.dsys.common.sdk.redis.redisson.util.RedissLockUtil;
import com.dsys.common.util.StringUtils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @discription redis配置信息
 * @author shilp
 * @created 2020/5/23  19:09
 * @Param
 * @Return
*/
@Slf4j
@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig{

    @Autowired
    private RedisProperties redisProperties;
    
    @Bean(name = "jedisPool")
    public JedisPool connectionFactory() {
        log.info("---- JedisPool 加载   start-----");
        
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 在获取当时通过pool能够获取到的最大的连接的jedis个数（已经被客户端 连接上或者正在闲置等待客户端连接）
        poolConfig.setMaxTotal(redisProperties.getPool().getMaxActive());
        // 最大能够保持idle的数量，控制一个pool最多有多少个状态为idle的jedis实例
        poolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        // 当连接池内的连接耗尽时，getBlockWhenExhausted为true时，连接会阻塞，超过了阻塞的时间（设定的maxWaitMillis，单位毫秒）时会报错
        poolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
        poolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        // 在获取连接的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);
        //在空闲时检查有效性, 默认false
        poolConfig.setTestWhileIdle(false);
//        JedisClientConfiguration jedisClientConfiguration = null;
//
//        if (redisProperties.isSsl()){
//            jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().
//                    poolConfig(poolConfig).and().
//                    readTimeout(Duration.ofMillis(redisProperties.getTimeout())).useSsl()
//                    .build();
//        }else {
//            jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().
//                    poolConfig(poolConfig).and().
//                    readTimeout(Duration.ofMillis(redisProperties.getTimeout())).build();
//        }
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//
//        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
//        redisStandaloneConfiguration.setPort(redisProperties.getPort());
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
//        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
//        RedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
        log.info("---- JedisPool 加载   end -----");
        JedisPool jedisPool = new JedisPool(poolConfig,redisProperties.getHost(),redisProperties.getPort(),
            redisProperties.getTimeout(),redisProperties.getPassword());
        return jedisPool;
    }
    

    /**
     * @discription redisson配置
     * @author shilp
     * @created 2020/5/24  13:40
     * @Param
     * @Return
    */
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
    @Bean
    IDistributedLocker distributedLocker(RedissonClient redissonClient) {
        IDistributedLocker locker = new RedissonDistributedLockerImpl();
        locker.setRedissonClient(redissonClient);
        RedissLockUtil.setLocker(locker);
        return locker;
    }


    
    
    
    
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate <Object, Object> redisTemplate = new RedisTemplate <>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //使用Jackson2JsonRedisSerializer替换默认的序列化规则
        //Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
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