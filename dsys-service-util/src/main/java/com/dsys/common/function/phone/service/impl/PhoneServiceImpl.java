package com.dsys.common.function.phone.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.dsys.common.function.phone.service.IPhoneService;
//import com.dsys.common.sdk.redisson.lock.IDistributedLocker;
import com.dsys.common.sdk.sms.SmsUtils;
import com.dsys.common.util.Constants;
import java.util.HashMap;
import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.dsys.base.service.impl
 * Description：短信功能实现
 * Date:  2019/12/25 23:36
 * Modified By:
 * @author shilp
 */
@Service
public class PhoneServiceImpl implements IPhoneService{


//    @Autowired
//    private IDistributedLocker distributedLocker;

    @Override
    public Map <String,Object> sendSMS(String phoneNumber, String signType) throws ClientException {
        Map<String,Object> returnMap = new HashMap <>();
        phoneTimeOut(phoneNumber,returnMap);
        return returnMap;
    }

    // 手机发送验证码
    private void phoneTimeOut(String phoneNumber, Map<String, Object> returnMap) throws ClientException {
        // 生成手机验证码
        int code = (int)((Math.random()*9+1)*100000);
        returnMap.put("phoneSign",code);
        Map <String, Object> map = SmsUtils.sendSms(Constants.SMSTYPE_SIGN,new String[]{phoneNumber},
                "{code:"+code+"}",phoneNumber+code,false);
        if("OK".equals(map.get("code"))){
            returnMap.put("status",Constants.STATUS_SUCCESS);
            // 通过redis设置存储验证码并设置过期时间
//            distributedLocker.lock(phoneNumber,)
        }else{
            returnMap.put("status",Constants.STATUS_ERROR);
            returnMap.put("msg",map);
        }
    }
}
