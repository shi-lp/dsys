package com.dsys.common.sdk.exception;

/**
 * Title: GlobalExceptionType
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 全局异常类型
 * @created 2020/4/24 11:19
 */
public enum GlobalExceptionType{
    
    USER_INPUT_ERROR(400,"用户输入异常"),
    SYSTEM_ERROR(500,"系统服务异常"),
    OTHER_ERROR(999,"其他未指定异常");
    
    private int code;
    
    private String typeDesc;
    
    public int getCode (){
        return code;
    }
    
    public String getTypeDesc (){
        return typeDesc;
    }
    
    GlobalExceptionType(int code,String typeDesc){
        this.code = code;
        this.typeDesc = typeDesc;
    }
    
}
