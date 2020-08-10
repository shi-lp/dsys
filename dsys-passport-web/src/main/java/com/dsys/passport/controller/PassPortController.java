package com.dsys.passport.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.dsys.api.bean.cim.CustomerInfo;
import com.dsys.common.sdk.redis.jedis.JedisUtil;
import com.dsys.common.sdk.response.RenderResponse;
import com.dsys.common.sdk.token.TokenUtil;
import com.dsys.common.util.CacheUtils;
import com.dsys.common.util.Constants;
import com.dsys.common.util.StringUtils;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: PassPortController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 认证中心
 * @created 2020/5/15 15:32
 */
@RestController
@RequestMapping(value="/dsys/passport")
public class PassPortController{
    
    

    /**     
     * @discription 用户登录，校验是否有Token信息，没有，生成Token返回
     * @author shilp       
     * @created 2020/5/15  16:01
     * @Param 
     * @Return 
    */
    @GetMapping(value="/customer")
    public RenderResponse login(CustomerInfo customerInfo){
        
        return RenderResponse.success();
    }
    
    /**
     * @discription token校验
     * @author shilp
     * @created 2020/5/18  17:01
     * @Param
     * @Return
    */
    @GetMapping(value = "/verify")
    public RenderResponse verify(@RequestParam(value = "token") String token){
        DecodedJWT jwt = TokenUtil.getJWT(token);
        // 不为空，校验成功
        if(jwt != null){
            // 过期时间
            Date expiresAt = jwt.getExpiresAt();
            long uid = jwt.getClaim("uid").asLong();
            String account = jwt.getClaim("account").asString();
            String userName = jwt.getClaim("userName").asString();
            // token设置的过期时间
            long exprieToken = 7 * 60 * 1000;
            long midExprie = (long)(exprieToken * 0.25);
            Date date = new Date(expiresAt.getTime() - midExprie);
            // 0-0.75   不更新Token
            if(new Date(System.currentTimeMillis()).before(date)){
                return RenderResponse.success(token);
                // 0.75 - 1 更新token
            }else if(new Date(System.currentTimeMillis()).after(date) && new Date(System.currentTimeMillis()).before(expiresAt)){
                token = TokenUtil.getToken(account,userName,uid);
            }else{ // >1 查询redis，存在更新，不存在，返回失败
                String s = new JedisUtil().strings().get(Constants.BASE_TOKEN + Constants.USER_LOGIN_TOKEN + uid);
                // 缓存中查询不到，没有登录信息，或登录未过期
                if(StringUtils.isBlank(s)){
                    return RenderResponse.fail("没有登录信息，请重新登陆");
                }else{
                    token = TokenUtil.getToken(account,userName,uid);
                }
            }
            CacheUtils.addTokenToRedis(Constants.BASE_TOKEN +
                    Constants.USER_LOGIN_TOKEN +String.valueOf(uid),token);
            return RenderResponse.success(token);
        }else if(StringUtils.isNotBlank(token)){
        
        }
        return RenderResponse.fail("登录信息为空，请登陆");
    }
}
