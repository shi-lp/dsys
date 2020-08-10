package com.dsys.common.util;

import com.dsys.common.sdk.exception.CustomException;
import com.dsys.common.sdk.exception.GlobalExceptionType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * Title: HttpClientUtil
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: httpclient工具类，使用java发送http请求
 * @created 2020/5/18 14:39
 */
public class HttpClientUtil{

    public static String doGet(String url){
        String renderString = null;
        /** 创建HttpClient对象*/
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /** 创建httpGet请求*/
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try{
            /** 执行请求*/
            response = httpClient.execute(httpGet);
            /** 判断返回状态是否为200（成功）*/
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity,"utf-8");
                EntityUtils.consume(entity);
                renderString = result;
            }
            httpClient.close();
        }catch(IOException e){
            throw new CustomException(GlobalExceptionType.SYSTEM_ERROR,"httpClient发送请求失败");
//            e.printStackTrace();
        }
        return renderString;
    }
    
    public static String doPost(String url,Map<String,String> paramMap){
        String renderString = null;
        /** 创建HttpClient对象*/
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /** 创建httpPost请求*/
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try{
            List<BasicNameValuePair> list = new ArrayList<>();
            for(Map.Entry<String,String> entry : paramMap.entrySet()){
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
            HttpEntity httpEntity = new UrlEncodedFormEntity(list,"UTF-8");
            httpPost.setEntity(httpEntity);
            response = httpClient.execute(httpPost);
            /** 判断返回状态是否为200（成功）*/
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity,"utf-8");
                EntityUtils.consume(entity);
                renderString = result;
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return renderString;
    }
}
