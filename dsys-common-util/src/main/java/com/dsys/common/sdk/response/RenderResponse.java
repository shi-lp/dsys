package com.dsys.common.sdk.response;

import com.dsys.common.sdk.exception.CustomException;
import com.dsys.common.sdk.exception.GlobalExceptionType;
import lombok.Data;

/**
 * Title: RenderResponse
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: RESTFul风格输出json格式
 * @created 2020/4/22 15:00
 */
@Data
public class RenderResponse{
    
    /** ajax请求是否成功*/
    private boolean responseStatus;
    
    /**
     * @discription http status code
    */
    private int code;
    
    /**
     * @discription 请求失败信息
    */
    private String msg;
    
    /** 请求成功时，返回给前端的数据 */
    private Object data;
    
    /** 请求头客户信息 */
    private String token;
    
    private RenderResponse (){
    
    }
    
    /**
     * @discription 请求成功时，没有相应数据（修改成功）
     * @author shilp
     * @created 2020/4/24  14:19
     * @Param
     * @Return
    */
    public static RenderResponse success(){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(true);
        resultBean.setCode(200);
        resultBean.setMsg("success");
        return resultBean;
    }
    
    /**
     * @discription 请求成功时，返回响应数据
     * @author shilp
     * @created 2020/4/24  14:19
     * @Param
     * @Return
    */
    public static RenderResponse success(Object data){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(true);
        resultBean.setCode(200);
        resultBean.setMsg("success");
        resultBean.setData(data);
        return resultBean;
    }

    
    /**
     * @discription 请求出现异常时进行响应数据封装
     * @author shilp
     * @created 2020/4/24  14:18
     * @Param
     * @Return
    */
    public static RenderResponse fail(CustomException e){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(false);
        if(e.getCode() == GlobalExceptionType.USER_INPUT_ERROR.getCode()){
            resultBean.setMsg(e.getMsg());
        }else if(e.getCode() == GlobalExceptionType.SYSTEM_ERROR.getCode()){
            resultBean.setMsg(e.getMsg()+"，系统内部错误,联系管理员进行处理");
        }else{
            resultBean.setMsg(e.getMsg()+"，系统未知错误,联系管理员进行处理");
        }
        resultBean.setCode(e.getCode());
        return resultBean;
    }
    
    public static RenderResponse fail(String msg){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(false);
        resultBean.setMsg(msg);
        resultBean.setCode(400);
        return resultBean;
    }
    
   
}
