package com.dsys.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**     
 * @discription 启动入口
 * @author shilp       
 * @created 2020/4/15  16:33
 * @Param 
 * @Return 
*/
@MapperScan(basePackages="com.dsys.common.worknode.mapper")
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class,org.redisson.spring.starter.RedissonAutoConfiguration.class})
@ServletComponentScan
@EnableCaching
public class CommonApplication{
    
    public static void main (String[] args){
        SpringApplication.run(CommonApplication.class,args);
    }
    
}
