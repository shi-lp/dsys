package com.dsys.base.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.dsys.base.sdk.redisson.lock.IDistributedLocker;
import com.dsys.base.sdk.sms.SmsUtils;
import com.dsys.base.service.IDictInfoService;
import com.dsys.base.service.IPhoneService;
import com.dsys.base.service.IUserService;
import com.dsys.base.util.Constants;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.dsys.base.service.impl
 * Description：短信功能实现
 * Author: shilp
 * Date:  2019/12/25 23:36
 * Modified By:
 */
@Service
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private IUserService userService;

    private RedissonClient redissonClient;

    @Autowired
    private IDistributedLocker distributedLocker;

    @Override
    public Map <String,Object> sendSMS(String phoneNumber, String signType) throws ClientException {
        Map<String,Object> returnMap = new HashMap <>();

        // 查看手机号是否在系统内
        if(userService.existPhone(phoneNumber)){
            // 登录验证
            // 手机验证码实现
            phoneTimeOut(phoneNumber,returnMap);
        }else{
            // 返回注册页面
            returnMap.put("action","register");
        }
        return returnMap;
    }

    // 手机发送验证码
    private void phoneTimeOut(String phoneNumber, Map<String, Object> returnMap) throws ClientException {
        // 生成手机验证码
        int code = (int)((Math.random()*9+1)*100000);
        returnMap.put("phoneSign",code);
        Map <String, Object> map = SmsUtils.sendSms(Constants.SMSTYPE_SIGN,new String[]{phoneNumber},
                "{code:"+code+"}",phoneNumber+code,false);
        if(map.get("code").equals("OK")){
            returnMap.put("status",Constants.STATUS_SUCCESS);
            // 通过redis设置存储验证码并设置过期时间
//            distributedLocker.lock(phoneNumber,)
        }else{
            returnMap.put("status",Constants.STATUS_ERROR);
            returnMap.put("msg",map);
        }
    }
}
