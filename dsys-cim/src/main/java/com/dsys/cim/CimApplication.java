package com.dsys.cim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**     
 * @discription Customer information management启动类
 * @author shilp       
 * @created 2020/4/10  14:57
 * @Param 
 * @Return 
*/
@MapperScan(basePackages="com.dsys.cim.mapper")
@SpringBootApplication
public class CimApplication{

    public static void main (String[] args){
        SpringApplication.run(CimApplication.class,args);
    }

}
