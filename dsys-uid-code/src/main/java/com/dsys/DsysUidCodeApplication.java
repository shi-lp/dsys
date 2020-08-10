package com.dsys;

import com.dsys.common.sdk.datasource.DataConfiguration;
import com.dsys.common.sdk.redis.redisson.RedisConfig;
import com.dsys.uid.sdk.uniquecode.CachedUidGeneratorConfig;

import com.dsys.uid.sdk.uniquecode.MyDisposableWorkerIdAssigner;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;

/**     
 * @discription 唯一编码生成类
 * @author shilp       
 * @created 2020/5/8  18:56
 * @Param 
 * @Return 
*/
@SpringBootApplication(exclude = {//CacheAutoConfiguration.class,
        RedissonAutoConfiguration.class,RedisAutoConfiguration.class,
        QuartzAutoConfiguration.class,RabbitAutoConfiguration.class
},scanBasePackageClasses = {DataConfiguration.class,CachedUidGeneratorConfig.class,MyDisposableWorkerIdAssigner.class})
@MapperScan(basePackages = {"com.dsys.uid.mapper"})
@EnableDubbo
@Slf4j
public class DsysUidCodeApplication{

    public static void main (String[] args){
        log.info("------唯一主键生成项目启动成功------");
        SpringApplication.run(DsysUidCodeApplication.class,args);
    }

}
