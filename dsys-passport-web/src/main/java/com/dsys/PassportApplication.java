package com.dsys;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.dsys.common.sdk.datasource.DataConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.spring.boot.autoconfigure.DubboAutoConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * @discription ${在此输入一句话描述此文件的作用}
 * @author shilp
 * @created 2020/6/12  16:26
 * @Param
 * @Return
*/
@SpringBootApplication(exclude = {CacheAutoConfiguration.class,MybatisPlusAutoConfiguration.class,
        DataSourceAutoConfiguration.class,QuartzAutoConfiguration.class,RabbitAutoConfiguration.class,
        DubboAutoConfiguration.class
})
//,scanBasePackageClasses = {},proxyBeanMethods=false
@Slf4j
public class PassportApplication{
    
    public static void main (String[] args){
        log.info("-------passport 模块加载开始 --------");
        SpringApplication.run(PassportApplication.class,args);
    }
    
}
