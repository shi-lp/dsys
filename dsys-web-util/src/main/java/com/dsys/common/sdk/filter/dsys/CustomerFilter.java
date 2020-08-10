package com.dsys.common.sdk.filter.dsys;


import com.dsys.common.util.StringUtils;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: CustomerFilter
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 编码格式化过滤器
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
    public void doFilter (ServletRequest req,ServletResponse res,FilterChain chain) throws IOException, ServletException{
        log.info("customerFilter请求处理前");
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
    
    
        // 不使用*，自动适配跨域域名，避免携带Cookie时失效
        String origin = request.getHeader("Origin");
        if(StringUtils.isNotBlank(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
    
        // 自适应所有自定义头
        String headers = request.getHeader("Access-Control-Request-Headers");
        if(StringUtils.isNotBlank(headers)) {
            response.setHeader("Access-Control-Allow-Headers", headers);
            response.setHeader("Access-Control-Expose-Headers", headers);
        }
    
        // 允许跨域的请求方法类型
        response.setHeader("Access-Control-Allow-Methods", "*");
        // 预检命令（OPTIONS）缓存时间，单位：秒
        response.setHeader("Access-Control-Max-Age", "3600");
        // 明确许可客户端发送Cookie，不允许删除字段即可
        response.setHeader("Access-Control-Allow-Credentials", "true");

    
        // 链路，直接传给下一个filter
        chain.doFilter(request,response);
        log.info("customerFilter请求处理后");
    }
    
    @Override
    public void destroy (){
        log.info("customerFilter销毁");
    }
}
