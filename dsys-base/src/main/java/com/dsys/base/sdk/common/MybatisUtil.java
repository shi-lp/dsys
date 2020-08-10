package com.dsys.base.sdk.common;

import com.dsys.api.bean.base.User;
import com.dsys.common.util.ToolUtil;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Title: MybatisUtil.java    
 * Description: Mybatis工具包
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月12日 下午2:49:01 
 * @update 2019年12月12日 下午2:49:01 
 * @version 1.0
*/

public class MybatisUtil {
	
	public static Object updateUserInfo(Object clazzInstance) throws IllegalArgumentException, IllegalAccessException{
		List<Field> fields = Arrays.asList(clazzInstance.getClass().getDeclaredFields());
		for(Field field : fields){
			// 获取类型
			System.out.println(field.getGenericType().toString());
			// 获取属性名
			System.out.println(field.getName());
			if("createUser".equals(field.getName())){
				// 对于私有变量的访问权限，在这里设置，这样即可访问Private修饰的变量
				field.setAccessible(true);
				if(ToolUtil.isNullOrEmpty(field.get("createUser"))){
					field.set("createUser", "");
					field.set("createTime", new Date());
					break;
				}else{
					field.set("updateUser", "");
					field.set("updateTime", new Date());
					break;
				}
			}
		}
		return clazzInstance;
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		User u = new User();
		u.setUserName("admin");
		MybatisUtil.updateUserInfo(u);
		System.out.println(u.getCreateTime());
	}

}
