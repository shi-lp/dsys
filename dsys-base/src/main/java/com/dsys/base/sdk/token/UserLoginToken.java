package com.dsys.base.sdk.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**        
 * Title: UserLoginToken.java    
 * Description: 需要登陆验证的注解
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月11日 下午8:33:07 
 * @update 2019年12月11日 下午8:33:07 
 * @version 1.0
*/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {

	boolean required() default true;
}
