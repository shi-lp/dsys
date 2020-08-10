package com.dsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;

/**     
 * @discription 用户管理前端访问接口
 * @author shilp       
 * @created 2020/5/9  9:39
 * @Param 
 * @Return 
*/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,QuartzAutoConfiguration.class
})
public class DsysCimWebApplication{
    
    public static void main (String[] args){
        SpringApplication.run(DsysCimWebApplication.class,args);
    }
    

}