package com.dsys.common.sdk.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.dsys.common.sdk.datasource.properties.DataProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**        
 * Title: DataConfiguration.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月4日 下午10:19:00 
 * @update 2019年12月4日 下午10:19:00 
 * @version 1.0
*/
@Slf4j
@Configuration
@EnableConfigurationProperties(DataProperties.class)
public class DataConfiguration {

	@Autowired
	private DataProperties dataProperties;
	
	@Configuration
	@ConditionalOnClass({DataSource.class})
	@ConditionalOnExpression("'${spring.datasource.type}'=='com.zaxxer.hikari.HikariDataSource' or '${spring.datasource.type}'=='com.alibaba.druid.pool.DruidDataSource'")
	protected class DataSourceClientConfiguration{
		
		@Bean
		@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource")
		HikariDataSource HikariDataSource(){
			log.info("hikari数据源信息开始加载");
			HikariConfig config = new HikariConfig();
			config.setDriverClassName(dataProperties.getDriverClass());
			config.setJdbcUrl(dataProperties.getUrl());
			config.setUsername(dataProperties.getUsername());
			config.setPassword(dataProperties.getPassword());
			config.setMaximumPoolSize(dataProperties.getHikariProperties().getMaximumPoolSize());
			config.setAutoCommit(dataProperties.getHikariProperties().isAutocommit());
			config.setMaxLifetime(dataProperties.getHikariProperties().getMaxLifetime());
			config.setMinimumIdle(dataProperties.getHikariProperties().getMinimumIdle());
			config.setIdleTimeout(dataProperties.getHikariProperties().getIdleTimeout());
			config.setConnectionTimeout(dataProperties.getHikariProperties().getConnectionTimeout());
			return new HikariDataSource(config);
		}
		
		@Bean
		@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
		DruidDataSource DruidDataSource(){
			log.info("Druid数据源信息开始加载");
			DruidDataSource datasource = new DruidDataSource();
			datasource.setUrl(dataProperties.getUrl());
            datasource.setUsername(dataProperties.getUsername());
            datasource.setPassword(dataProperties.getPassword());
            datasource.setDriverClassName(dataProperties.getDriverClass());
            datasource.setInitialSize(dataProperties.getDruidProperties().getInitialSize());
            datasource.setMinIdle(dataProperties.getDruidProperties().getMinIdle());
            datasource.setMaxActive(dataProperties.getDruidProperties().getMaxActive());
            datasource.setMaxWait(dataProperties.getDruidProperties().getMaxWait());
            datasource.setTimeBetweenEvictionRunsMillis(dataProperties.getDruidProperties().getTimeBetweenEvictionRunsMillis());
            datasource.setMinEvictableIdleTimeMillis(dataProperties.getDruidProperties().getMinEvictableIdleTimeMillis());
            datasource.setValidationQuery(dataProperties.getDruidProperties().getValidationQuery());
            datasource.setTestWhileIdle(dataProperties.getDruidProperties().isTestWhileIdle());
            datasource.setTestOnBorrow(dataProperties.getDruidProperties().isTestOnBorrow());
            datasource.setTestOnReturn(dataProperties.getDruidProperties().isTestOnReturn());
            datasource.setPoolPreparedStatements(dataProperties.getDruidProperties().isPoolPreparedStatements());
            datasource.setMaxPoolPreparedStatementPerConnectionSize(dataProperties.getDruidProperties().getMaxPoolPreparedStatementPerConnectionSize());
            datasource.setUseGlobalDataSourceStat(dataProperties.getDruidProperties().isUseGlobalDataSourceStat());
            try {
                datasource.setFilters(dataProperties.getDruidProperties().getFilters());
            } catch (SQLException e) {
                System.err.println("druid configuration initialization filter: " + e);
            }
            datasource.setConnectionProperties(dataProperties.getDruidProperties().getConnectionProperties());
			log.info("Druid数据源信息加载完成");
            return datasource;
		}
	}
}
