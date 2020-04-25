package com.dsys.common.sdk.sms;

import com.dsys.common.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: SmsUtils.java Description: 短信接口工具
 *
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @version 1.0
 * @created 2019年12月13日 下午9:03:04
 * @update 2019年12月13日 下午9:03:04
 */
@Slf4j
public class SmsUtils {

    // 产品名称:云通信短信API产品,开发者无需替换
    static final String product = "";
    // 产品域名,开发者无需替换
    static final String domain = "";

    static final String accessKeyId = "";
    static final String accessKeySecret = "";
    // 必填:短信签名-可在短信控制台中找到
    static final String signName = "dsys验证码签名";
    // 必填:短信模板-可在短信控制台中找到
    static final String templateName = "";

    /**
     * @param smsType     短信类型
     * @param phoneNumber 接收人号码
     * @param params      参数，json格式
     * @param outId       外部流水号
     * @param detail      是否返回详细信息
     * @return
     * @throws ClientException
     */
    public static Map <String, Object> sendSms(String smsType, String[] phoneNumber, String params, String outId, boolean detail) throws ClientException {
        Map <String, Object> returnMap = new HashMap <String, Object>();
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        request.setSysMethod(MethodType.POST);
        // 必填:待发送手机号
        request.setPhoneNumbers(StringUtils.StrArray2Str(phoneNumber));
        request.setSignName(signName);
        request.setTemplateCode(templateName);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(params);

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)如果需要查看短信回复。这个得填上
//		request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        if (StringUtils.isNotBlank(outId)) {
            request.setOutId(outId);
        }

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse response = acsClient.getAcsResponse(request);
        log.info("---短信发送返回---");
        returnMap.put("code", response.getCode());
        returnMap.put("requestId", response.getRequestId());
        returnMap.put("bizId", response.getBizId());
        if (response.getCode() != null && response.getCode().equals("OK") && detail) {
            makeMap(returnMap,phoneNumber);
        }
        return returnMap;
    }

    public static void makeMap(Map<String, Object> returnMap,String[] phoneNumber) throws ClientException {
        QuerySendDetailsResponse response = querySendDetails(returnMap.get("bizId").toString(),phoneNumber);
        log.info("短信明细查询接口返回数据----------------");
        Map<String,Object> midMap = new HashMap <>();
        midMap.put("code",response.getCode());
        midMap.put("message",response.getMessage());
        midMap.put("SmsSendDetailDTO",response.getSmsSendDetailDTOs());
        midMap.put("totalCount",response.getTotalCount());
        midMap.put("requestId",response.getRequestId());
        returnMap.put("detail",midMap);
    }

	/**
	 *
	 * @param bizId
	 * @param phoneNumber
	 * @return
	 * @throws ClientException
	 */
    public static QuerySendDetailsResponse querySendDetails(String bizId,String[] phoneNumber) throws ClientException {

        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        // 必填-号码
        request.setPhoneNumber(StringUtils.StrArray2Str(phoneNumber));
        // 可选-流水号
        request.setBizId(bizId);
        // 必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        // 必填-页大小
        request.setPageSize(10L);
        // 必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        // hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

}
