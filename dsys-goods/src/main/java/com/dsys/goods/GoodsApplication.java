package com.dsys.goods;

import com.dsys.common.sdk.datasource.DataConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {CacheAutoConfiguration.class,
        RedisAutoConfiguration.class,RedissonAutoConfiguration.class,
        QuartzAutoConfiguration.class,RabbitAutoConfiguration.class,ThymeleafAutoConfiguration.class
},scanBasePackageClasses = {DataConfiguration.class})
@ComponentScan(basePackages = {"com.dsys.goods.controller"})
@MapperScan(basePackages = {"com.dsys.goods.mapper"})
public class GoodsApplication{

    public static void main (String[] args){
        SpringApplication.run(GoodsApplication.class,args);
    }

}
