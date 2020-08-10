package com.dsys.common.sdk.cache;

import com.alibaba.fastjson.JSON;
import com.dsys.api.common.BaseModel;
import com.dsys.common.sdk.cache.ehcache.properties.EhcacheProperties;
import com.dsys.common.sdk.redis.redisson.serializer.FastJsonRedisSerializer;
import com.dsys.common.util.ArrayUtil;
import com.dsys.common.util.Constants;
import com.google.common.collect.ImmutableMap;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.StringUtils;
//import org.ehcache.config.CacheConfiguration;
//import org.ehcache.config.builders.CacheConfigurationBuilder;
//import org.ehcache.config.builders.CacheManagerBuilder;
//import org.ehcache.config.builders.ExpiryPolicyBuilder;
//import org.ehcache.config.builders.ResourcePoolsBuilder;
//import org.ehcache.config.units.EntryUnit;
//import org.ehcache.config.units.MemoryUnit;
//import org.ehcache.expiry.ExpiryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.ehcache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import java.util.List;

/**
 * Package: com.dsys.base.sdk.cache
 * Description：
 * @Author: shilp
 * Date:  2019/12/31 15:08
 * Modified By:
 */
@Slf4j
@Configuration
@EnableCaching
//@EnableConfigurationProperties(EhcacheProperties.class)
public class CacheManagerConfig  extends CachingConfigurerSupport{
    
//    @Autowired
//    private EhcacheProperties ehcacheProperties;
    
    private static final String DEFAULT_CACHE_ALIAS = "base_str_cache";
    private static final String OBJECT_CACHE_ALIAS = "base_obj_cache";

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
     * ehcache 主要的管理器
     * 设为默认的cache管理器
     */
    @Bean(name = "appEhCacheCacheManager")
    @Primary
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        bean = ehCacheManagerFactoryBean();
        System.setProperty(CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
        log.info("[Ehcache缓存客户端] 初始化Ehcache客户端bean, success! 配置信息:{}", bean.getObject ());
        return new EhCacheCacheManager (bean.getObject ());
    }
    
    /**
     * @discription 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
     * @author shilp
     * @Return
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("config/ehcache.xml"));
        cacheManagerFactoryBean.setShared (true);
        log.info("ehcache工厂加载成功");
        return cacheManagerFactoryBean;
    }
    
    /**
     * 自定义KEY生成器，格式： jww:data:[包名 + 类名 + 方法名+ 参数]
     * 如：jww:data:com.jww.common.redis.RedisConfig:queryList_param1_param2
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            
            @Override
            public Object generate(Object target,Method method,Object... params) {
                org.springframework.cache.annotation.CacheConfig cacheConfig = target.getClass().getAnnotation(CacheConfig.class);
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
                sb.append("base")
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
                        paramStr = String.join("_", paramStr, "cacheCode");
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
    
    private static class CacheCustomizer
            implements CacheManagerCustomizer <EhCacheCacheManager> {
        /**
         * CacheManager缓存自定义初始化比较早，尽量不要@autowired 其他spring 组件
         */
        @Override
        public void customize(EhCacheCacheManager cacheManager) {
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
    
    
    
    /**
     * @discription 配置redis缓存管理器
     * @author shilp
     * @created 2020/5/23  19:17
     * @Param
     * @Return
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //设置CacheManager的值序列化方式为json序列化
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                //                .serializeKeysWith(RedisSerializationContext
                //                                                .SerializationPair
                //                                                .fromSerializer(new StringRedisSerializer()))
                //
                //设置默认超过期时间是60秒
                .entryTtl(Duration.ofMinutes(60));
        //初始化RedisCacheManager
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .transactionAware()
                .build();
    }
    
    /**
     * @discription value值序列化方式
     * @author shilp
     * @created 2020/5/23  19:23
     * @Param
     * @Return
     * @return
     */
    private FastJsonRedisSerializer<Object> valueSerializer(){
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer <>(Object.class);
        return fastJsonRedisSerializer;
    }
    
//    /**
//     * @discription ehcache缓存设置，设置为默认缓存
//     * @author shilp
//     * @created 2020/6/1  16:21
//     * @Param
//     * @Return
//    */
//    @Bean
//    @Primary
//    public EhCacheCacheManager ehcacheManager(){
//        log.info("[Ehcache缓存客户端] 初始化Ehcache客户端bean, start...");
//        // 2.设置缓存资源参数
//        ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder.newResourcePoolsBuilder()
//                // 设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
//                .heap(ehcacheProperties.getHeap(), EntryUnit.ENTRIES)
//                // 设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
//                .offheap(ehcacheProperties.getOffheap(), MemoryUnit.MB);
//
//        // 3.设置缓存管理器的构造器
//        CacheManagerBuilder cacheManagerBuilder = CacheManagerBuilder.newCacheManagerBuilder();
//
//        // 配置有磁盘持久化地址, 则设置持久化参数
//        if (StringUtils.isNotBlank(ehcacheProperties.getDiskDir())) {
//            resourcePoolsBuilder.disk(ehcacheProperties.getDisk(), MemoryUnit.MB, true);
//            // 硬盘持久化地址
//            cacheManagerBuilder.with(CacheManagerBuilder.persistence(ehcacheProperties.getDiskDir()));
//        }
//
//        // 4.ttl过期策略, 0表示永不过期
//        ExpiryPolicy expiryPolicy = ehcacheProperties.getTtl() == 0L ? ExpiryPolicyBuilder.noExpiration() : ExpiryPolicyBuilder.timeToLiveExpiration(Duration.of(ehcacheProperties.getTtl(), ChronoUnit.SECONDS));
//
//        // 5.构建缓存
//
////        // 5.1.初始化字符串缓存
//        cacheManagerBuilder = this.addStrCache(resourcePoolsBuilder, expiryPolicy, cacheManagerBuilder);
////
////        // 5.2.初始化对象缓存
//        //        cacheManagerBuilder = this.addObjCache(resourcePoolsBuilder, expiryPolicy, cacheManagerBuilder);
//
////        cacheManagerBuilder = this.addListCache(resourcePoolsBuilder, ExpiryPolicyBuilder.noExpiration(),cacheManagerBuilder);
//
//        // 6.创建缓存管理器
//        log.info("[Ehcache缓存客户端] 初始化Ehcache客户端bean, success! 配置信息:{}", ehcacheProperties);
//        return (EhCacheCacheManager)cacheManagerBuilder.build(true);
//    }
//
//    private CacheManagerBuilder addListCache (ResourcePoolsBuilder resourcePoolsBuilder,ExpiryPolicy<Object, Object> expiryPolicy,CacheManagerBuilder cacheManagerBuilder){
//        // 配置默认缓存配置
//        CacheConfiguration<String, List> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                // 缓存数据K和V的数值类型
//                // 在ehcache3.3中必须指定缓存键值类型,如果使用中类型与配置的不同,会报类转换异常
//                String.class, List.class, resourcePoolsBuilder)
//                // ttl过期时间
//                .withExpiry(expiryPolicy)
//                .build();
//
//        // 设置一个默认缓存(字符串)
//        return cacheManagerBuilder.withCache(DEFAULT_CACHE_ALIAS, cacheConfiguration);
//    }
//
//    //    public void afterPropertiesSet() throws Exception {
////
////
////        // 1.初始化配置参数
////        this.ehcacheManager();
////        // 7.获取缓存对象
////
////        // 7.1.获取字符串缓存
////        strCache = cacheManager.getCache(DEFAULT_CACHE_ALIAS, String.class, String.class);
////
////        // 7.2.获取对象缓存
////        objCache = cacheManager.getCache(OBJECT_CACHE_ALIAS, String.class, String.class);
////
////        if (strCache == null || objCache == null) {
////            throw new BeanInitializationException("Ehcache初始化异常: strCache or objCache is null!");
////        }
////
////
////    }
////
////
//    /**
//     * 初始化字符串缓存
//     * @param resourcePoolsBuilder 资源池构建对象
//     * @param expiryPolicy 过期策略
//     * @param cacheManagerBuilder 缓存管理器构建器
//     */
//    private CacheManagerBuilder addStrCache(ResourcePoolsBuilder resourcePoolsBuilder,ExpiryPolicy expiryPolicy,CacheManagerBuilder cacheManagerBuilder) {
//        // 配置默认缓存配置
//        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                // 缓存数据K和V的数值类型
//                // 在ehcache3.3中必须指定缓存键值类型,如果使用中类型与配置的不同,会报类转换异常
//                String.class, String.class, resourcePoolsBuilder)
//                // ttl过期时间
//                .withExpiry(expiryPolicy)
//                .build();
//
//        // 设置一个默认缓存(字符串)
//        return cacheManagerBuilder.withCache(DEFAULT_CACHE_ALIAS, cacheConfiguration);
//    }
//
//    /**
//     * 初始化对象缓存
//     * @param resourcePoolsBuilder 资源池构建对象
//     * @param expiryPolicy 过期策略
//     * @param cacheManagerBuilder 缓存管理器构建器
//     */
//    private CacheManagerBuilder addObjCache(ResourcePoolsBuilder resourcePoolsBuilder, ExpiryPolicy expiryPolicy, CacheManagerBuilder cacheManagerBuilder) {
//        // 对象缓存配置
//        CacheConfiguration<String, String> cacheConfigurationForObj = CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                String.class, String.class, resourcePoolsBuilder)
//                // ttl过期时间
//                .withExpiry(expiryPolicy)
//                .build();
//
//        // 设置对象类型缓存
//        return cacheManagerBuilder.withCache(OBJECT_CACHE_ALIAS, cacheConfigurationForObj);
//    }

}
