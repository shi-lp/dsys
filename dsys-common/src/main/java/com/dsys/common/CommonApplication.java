package com.dsys.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**     
 * @discription ${在此输入一句话描述此文件的作用}
 * @author shilp       
 * @created 2020/4/15  16:33
 * @Param 
 * @Return 
*/
@MapperScan(basePackages="com.dsys.common.worknode.mapper")
@SpringBootApplication
@EnableCaching
public class CommonApplication{
    
    public static void main (String[] args){
        SpringApplication.run(CommonApplication.class,args);
    }
    
}
