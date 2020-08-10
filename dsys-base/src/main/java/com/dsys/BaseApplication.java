package com.dsys;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @discription 后台基础启动项
 * @author shilp
 * @created 2020/5/19  20:11
 * @Param
 * @Return
*/
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class
    })
@MapperScan(basePackages = {"com.dsys.*.mapper"})
@ComponentScan(basePackages = {"com.dsys.*"})
@EnableCaching
@EnableScheduling
@EnableTransactionManagement
public class BaseApplication{

    public static void main (String[] args){
        SpringApplication.run(BaseApplication.class,args);
    }

}
