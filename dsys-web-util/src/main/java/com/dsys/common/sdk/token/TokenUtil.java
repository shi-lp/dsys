package com.dsys.common.sdk.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import com.dsys.common.util.StringUtils;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Title: TokenUtil.java Description: Token工具类
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月11日 下午8:31:01
 * @update 2019年12月11日 下午8:31:01
 */

public class TokenUtil{
    
    /**
     * 过期时间为一天
     * 正式上线更换为30分钟
     */
    private static final long EXPIRE_TIME_DEV = 24 * 60 * 60 * 1000;
    
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "qsxcvhji2a9s7d5f1g4h1j5k7l9t2bijcco";
    
    
    /**
     * 通过获取token
     * @param userName
     * @return
     */
    public static String getToken (String account,String userName,Long uid){
        String token = "";
        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头信息
        Map<String, Object> header = new HashMap<String, Object>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");
        // 附带account和passtoken生成签名
        /** 头信息，公共部分，加密信息*/
        token = JWT.create().withHeader(header)
                /**私有业务信息*/
                .withClaim("uid",uid)
                .withClaim("account",account)
                .withClaim("userName",userName)
                /** 过期时间*/
                .withExpiresAt(date)
                /** 加密算法签名部分*/
                .sign(algorithm);
        return token;
    }
    
    /**
     * token校验
     * @param token
     * @return
     */
    public static boolean verity (String token){
        DecodedJWT jwt = getJWT(token);
        if(null != jwt){
            return true;
        }
        return false;
    }
    
    /**
     * @discription 获取jwt对象
     * @author shilp
     * @created 2020/6/11  9:38
     * @Param
     * @Return
    */
    public static DecodedJWT getJWT(String token){
        DecodedJWT jwt = null;
        try{
            if(!StringUtils.isBlank(token)){
                Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
                JWTVerifier verifier = JWT.require(algorithm).build();
                jwt = verifier.verify(token);
            }
        }catch(IllegalArgumentException e){
            jwt = null;
        }catch(JWTVerificationException e){
            jwt = null;
        }
        return jwt;
    }
    
    
    /**
     * @discription 获取过期时间
     * @author shilp
     * @created 2020/6/11  10:09
     * @Param
     * @Return
    */
    public static Date getExpirationDateFromToken(String token) {
        DecodedJWT jwt = getJWT(token);
        return jwt.getExpiresAt();
    }
    
    /**
     * @discription 获取用户UID
     * @author shilp
     * @created 2020/6/11  10:15
     * @Param
     * @Return
    */
    public static Long getUidFromToken(String token){
        DecodedJWT jwt = getJWT(token);
        return jwt.getClaim("uid").asLong();
    }
    
    
}
