package com.dsys.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: IPUtil
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: ip工具类
 * @created 2020/6/17 17:16
 */
public class IPUtil{
    
    /**
     * 获取IP
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request){
        String headerIP = request.getRemoteAddr();
        if(headerIP == null || "".equals(headerIP) || "null".equals(headerIP)){
            headerIP = request.getHeader("x-real-ip");;
        }
        if(headerIP == null || "".equals(headerIP) || "null".equals(headerIP)){
            headerIP = request.getHeader("x-forwarded-for");
        }
        if(headerIP !=null && !"".equals(headerIP) && !"null".equals(headerIP)){
            headerIP = request.getHeader("Proxy-Client-IP");
        }
        if(headerIP !=null && !"".equals(headerIP) && !"null".equals(headerIP)){
            headerIP = request.getHeader("WL-Proxy-Client-IP");
        }
        return headerIP;
    }

}
