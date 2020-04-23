package com.dsys.common.sdk.filter.dsys;


import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: CustomerFilter
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/23 16:30
 */
//@WebFilter(filterName = "customerFilter",urlPatterns = "/*")
@Slf4j
public class CustomerFilter implements Filter{
    
    @Override
    public void init (FilterConfig filterConfig) throws ServletException{
        log.info("customerFilter初始化");
    }
    
    @Override
    public void doFilter (ServletRequest request,ServletResponse response,FilterChain chain) throws IOException, ServletException{
        log.info("customerFilter请求处理前");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    
        // 链路，直接传给下一个filter
        chain.doFilter(request,response);
        log.info("customerFilter请求处理后");
    }
    
    @Override
    public void destroy (){
        log.info("customerFilter销毁");
    }
}
