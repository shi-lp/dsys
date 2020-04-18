package com.dsys.base.sdk.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.dsys.base.bean.User;

import java.util.Date;
import java.util.HashMap;

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
    
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "qsxcvhji29751415792ijcco";
    
    /**
     * 通过用户获取token
     * @param user
     * @return
     */
    public static String getToken (User user){
        String token = "";
        // 过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头信息
        HashMap<String, Object> header = new HashMap<String, Object>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");
        // 附带username和userID生成签名
        token = JWT.create().withHeader(header)
                .withClaim("loginName",user.getAccount())
                .withAudience(user.getSId())
                .withExpiresAt(date).sign(algorithm);
        return token;
    }
    
    /**
     * token校验
     * @param token
     * @return
     */
    public static boolean verity (String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }catch(JWTVerificationException e){
            return false;
        }
    }
}
