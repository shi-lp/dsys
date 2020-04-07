package com;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 
 * Title: DsysApplication.java    
 * Description: 启动类
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月3日 下午9:49:58 
 * @update 2019年12月3日 下午9:49:58 
 * @version 1.0
 * //开启基于注解的缓存
 */
@MapperScan(value={"com.dsys.*.dao","com.dsys.*.service"})
@SpringBootApplication
@EnableCaching
public class DsysApplication {

	private static final Logger log = LoggerFactory.getLogger(DsysApplication.class);
	public static void main(String[] args) {
		log.info("********* 项目启动开始 ********");
		SpringApplication.run(DsysApplication.class, args);
	}
	
}
