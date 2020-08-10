package com.dsys.common.sdk.mybatis;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Title: MybatisConfig.java
 * Description: Mybatis配置类
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月5日 下午9:49:51
 * @update 2019年12月5日 下午9:49:51
 */
@EnableTransactionManagement
@Configuration
public class MybatisConfig{
    
    private static final Logger log = LoggerFactory.getLogger(MybatisConfig.class);
    
    /**
     * @return
     * @discription 配置mybatis实体类驼峰命名规则
     * @author shilp
     * @created 2019年12月5日 下午9:53:15
     * @update 2019年12月5日 下午9:53:15
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer (){
        log.info("-----MybatisConfig-----配置类加载开始");
        return new ConfigurationCustomizer(){
            @Override
            public void customize (org.apache.ibatis.session.Configuration configuration){
                // 驼峰
                configuration.setMapUnderscoreToCamelCase(true);
                // 自动赋值给实体
                // NONE：取消自动映射
                // PARTIAL:只会自动映射，没有定义嵌套结果集映射的结果集
                // FULL:会自动映射任意复杂的结果集（无论是否嵌套)
                configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
            }
        };
    }
    
    /**
     * 开启分页，设置物理分页
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor (){
        log.info("-----MybatisConfig-----配置分页");
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInterceptor.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(-1);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
    
    /**     
     * @discription 添加乐观锁插件
     * 目前乐观锁仅支持 updateById(id) 与 update(entity, wrapper) 方法
     * 在 update(entity, wrapper) 方法下，wrapper 不能复用!!!
     * @author shilp       
     * @created 2020/8/10  11:36
     * @Param 
     * @Return 
    */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor (){
        log.info("-----MybatisConfig-----配置乐观锁");
        return new OptimisticLockerInterceptor();
    }
}
