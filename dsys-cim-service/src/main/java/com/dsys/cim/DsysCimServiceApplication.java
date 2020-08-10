package com.dsys.cim;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.jta.atomikos.AtomikosProperties;

/**
 * @discription 客户管理
 * @author shilp
 * @created 2020/5/8  22:24
 * @Param
 * @Return
*/
@SpringBootApplication(exclude = {QuartzAutoConfiguration.class,RedissonAutoConfiguration.class,RedisAutoConfiguration.class,RabbitAutoConfiguration.class

})
@MapperScan(basePackages = "com.dsys.cim.mapper")
public class DsysCimServiceApplication{

    public static void main (String[] args){
        SpringApplication.run(DsysCimServiceApplication.class,args);
    }

}
