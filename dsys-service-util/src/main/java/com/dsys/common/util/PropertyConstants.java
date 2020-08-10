package com.dsys.common.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Title: PropertyConstants
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: properties工作类
 * @created 2020/6/11 20:19
 */
public class PropertyConstants{
    
    private static Properties properties;
    
    private static void setProperty(){
        if (properties==null) {
            properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try {
                properties.load(loader.getResourceAsStream("application.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String getPropertiesKey(String key){
        if (properties==null) {
            setProperty();
        }
        return properties.getProperty(key, "default");
    }

}
