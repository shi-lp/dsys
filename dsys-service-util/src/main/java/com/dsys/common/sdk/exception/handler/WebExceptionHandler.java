package com.dsys.common.sdk.exception.handler;

import com.dsys.common.sdk.exception.CustomException;
import com.dsys.common.sdk.exception.GlobalExceptionType;
import com.dsys.common.sdk.response.RenderResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: WebExceptionHandler
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 全局异常处理，将异常从dao抛出到service到controller层，再进行全局异常处理
 * @created 2020/4/24 14:32
 */
@ControllerAdvice
public class WebExceptionHandler{
    
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public RenderResponse customException(CustomException e){
//        400异常不用抛出，友好的返回给用户
        if(e.getCode() == GlobalExceptionType.SYSTEM_ERROR.getCode()){
//        将500异常持久化处理，方便运维人员处理
        
        }
        return RenderResponse.fail(e);
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RenderResponse exception(Exception e){
        
        return RenderResponse.fail(new CustomException(GlobalExceptionType.OTHER_ERROR,"系统未知异常"+e.getMessage()));
    }
}
