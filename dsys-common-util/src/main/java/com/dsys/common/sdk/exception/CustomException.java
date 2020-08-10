package com.dsys.common.sdk.exception;

/**
 * Title: CustomException
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 自定义异常
 * @created 2020/4/24 11:29
 */
public class CustomException extends RuntimeException{
    /**
     * @discription 异常错误编码
    */
    private int code;
    
    /**
     * @discription 异常信息
     */
    private String msg;
    
    private CustomException(){
    
    }
    
    public CustomException(GlobalExceptionType exceptionType,String msg){
        this.code = exceptionType.getCode();
        this.msg = msg;
    }
    
    public int getCode (){
        return code;
    }
    
    public String getMsg (){
        return msg;
    }
}
