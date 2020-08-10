package com.dsys.common.util;

import com.dsys.common.sdk.exception.CustomException;
import com.dsys.common.sdk.exception.GlobalExceptionType;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import java.io.File;
import java.net.InetAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Title: GeoIpUtil
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: Geoip工具类
 * @created 2020/6/17 19:51
 */
@Component
@ConfigurationProperties(prefix = "spring.geoip")
public class GeoIpUtil{
    
    private static String geoUrl;
    
    public static String getGeoUrl (){
        return geoUrl;
    }
    
    public void setGeoUrl (String geoUrl){
        GeoIpUtil.geoUrl = geoUrl;
    }
    
    public static String getContentByIp(String ip){
        try{
            return getContentByIp(getGeoUrl(),ip);
        }catch(Exception e){
            return "";
//            throw new CustomException(GlobalExceptionType.SYSTEM_ERROR,"通过ip获取地址信息失败");
        }
    }
    
    public static String getContentByIp(String geoUrl,String ip) throws Exception {
        // 创建 GeoLite2 数据库
        File database = new File(geoUrl);
        if(database.exists()){
            // 读取数据库内容
            DatabaseReader reader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            // 获取查询结果
            CityResponse response = reader.city(ipAddress);
            // 获取国家信息
            Country country = response.getCountry();
            System.out.println(country.getIsoCode()); // 'CN'
            System.out.println(country.getName()); // 'China'
            System.out.println(country.getNames().get("zh-CN")); // '中国'
    
            // 获取省份
            Subdivision subdivision = response.getMostSpecificSubdivision();
            System.out.println(subdivision.getName()); // 'Guangxi Zhuangzu Zizhiqu'
            System.out.println(subdivision.getIsoCode()); // '45'
            System.out.println(subdivision.getNames().get("zh-CN")); // '广西壮族自治区'
    
            // 获取城市
            City city = response.getCity();
            System.out.println(city.getName()); // 'Nanning'
            Postal postal = response.getPostal();
            System.out.println(postal.getCode()); // 'null'
            System.out.println(city.getNames().get("zh-CN")); // '南宁'
            Location location = response.getLocation();
            System.out.println(location.getLatitude()); // 22.8167
            System.out.println(location.getLongitude()); // 108.3167
            return "登录地址为"+country.getNames().get("zh-CN") + subdivision.getNames().get("zh-CN") +
                    city.getNames().get("zh-CN");
        }else{
            return "";
        }
        
    }

}
