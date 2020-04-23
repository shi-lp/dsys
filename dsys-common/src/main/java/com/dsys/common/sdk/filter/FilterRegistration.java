package com.dsys.common.sdk.filter;

import com.dsys.common.sdk.filter.dsys.CustomerFilter;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title: FilterConfig
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 过滤器（多过滤器，先进后出，作用于Controller）
 * @created 2020/4/23 16:36
 */
@Configuration
public class FilterRegistration{

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
//        当过滤器有注入其他Bean时，可直接通过@Bean的方式进行实体类过滤器，这样不可自动注入
//        当然，若无其他bean时，可直接new,也可使用getBean的方式
        registration.setFilter(customerFilter());
//        过滤器名称
        registration.setName("customerFilter");
//        拦截路径
        registration.addUrlPatterns("/*");
//        设置顺序
        registration.setOrder(10);
        return registration;
    }
    
    @Bean
    public Filter customerFilter(){
        return new CustomerFilter();
    }
}
