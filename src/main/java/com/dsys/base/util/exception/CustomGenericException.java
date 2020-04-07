package com.dsys.base.util.exception;

/**
 * Title: CustomGenericException
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/3/18 10:52
 */
public class CustomGenericException extends RuntimeException{
    
    private String errCode;
    
    private String errMsg;
    
    public String getErrCode (){
        return errCode;
    }
    
    public void setErrCode (String errCode){
        this.errCode = errCode;
    }
    
    public String getErrMsg (){
        return errMsg;
    }
    
    public void setErrMsg (String errMsg){
        this.errMsg = errMsg;
    }
    
    public CustomGenericException (String errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    
}
