package com.dsys.common.sdk.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Title: JWTToken
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: token密钥
 * @created 2020/6/9 17:40
 */
public class JWTToken implements AuthenticationToken{
    
    /**     
     * @discription 密钥
     * @author shilp
     * @created 2020/6/9  17:41
     * @Param 
     * @Return 
    */
    private String token;
    
    public JWTToken(String token){
        this.token = token;
    }
    
    @Override
    public Object getPrincipal (){
        return token;
    }
    
    @Override
    public Object getCredentials (){
        return token;
    }
}
