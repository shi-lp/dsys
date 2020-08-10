package com.dsys.common.util;

import com.dsys.common.sdk.redis.jedis.JedisUtil;

/**
 * Title: CacheUtils
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 缓存工具类
 * @created 2020/6/11 14:38
 */
public class CacheUtils{
    
    
    /**
     * @discription 将用户登陆Token信息插入redis，设置为Token过期日期的2倍
     * @author shilp
     * @created 2020/6/11  14:39
     * @Param  uid:用户id；token 信息
     * @Return boolean
    */
    public static boolean addTokenToRedis(String uid,String token){
        String str = new JedisUtil().strings().setEx(uid,36000000,token);
        return true;
    }
    
    
    /**
     * @discription 检查redis中是否存在uid的Token信息
     * @author shilp
     * @created 2020/6/11  14:42
     * @Param   uid：用户ID
     * @Return
    */
    public static boolean checkTokenInRedis(String uid){
        String str = new JedisUtil().strings().get(uid);
        if(StringUtils.isBlank(str)){
            return false;
        }
        return true;
    }
}
