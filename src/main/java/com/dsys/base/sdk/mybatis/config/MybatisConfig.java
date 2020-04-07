package com.dsys.base.sdk.mybatis.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title: MybatisConfig.java
 * Description: Mybatis配置类
 *
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月5日 下午9:49:51
 * @update 2019年12月5日 下午9:49:51
 */
// 设置批量扫描Mapper接
@Configuration
public class MybatisConfig {

    private static final Logger log = LoggerFactory.getLogger(MybatisConfig.class);

    /**
     * @return
     * @discription 配置mybatis实体类驼峰命名规则
     * @author shilp
     * @created 2019年12月5日 下午9:53:15
     * @update 2019年12月5日 下午9:53:15
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        log.info("-----MybatisConfig---Mybatis配置类加载开始");
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
                configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
            }
        };
    }

    /**
     * 开启分页
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
