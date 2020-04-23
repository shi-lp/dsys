package com.dsys.common.sdk.response;

import lombok.Data;

/**
 * Title: RenderResponse
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/22 15:00
 */
@Data
public class RenderResponse{
    
    private boolean responseStatus;
    
    private int code;
    
    private String msg;
    
    private Object data;
    
    private String token;
    
    private RenderResponse(){
    
    }
    
    public static RenderResponse success(){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(true);
        resultBean.setCode(200);
        resultBean.setMsg("success");
        return resultBean;
    }
    
    public static RenderResponse success(Object data){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(true);
        resultBean.setCode(200);
        resultBean.setMsg("success");
        resultBean.setData(data);
        return resultBean;
    }
    
    public static RenderResponse fail(){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(false);
        resultBean.setCode(400);
        resultBean.setMsg("fail");
        return resultBean;
    }
    
    public static RenderResponse fail(String msg){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(false);
        resultBean.setCode(400);
        resultBean.setMsg(msg);
        return resultBean;
    }
    
    public static RenderResponse failService(){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(false);
        resultBean.setCode(500);
        resultBean.setMsg("failService");
        return resultBean;
    }
    
    public static RenderResponse failService(String msg){
        RenderResponse resultBean = new RenderResponse();
        resultBean.setResponseStatus(false);
        resultBean.setCode(500);
        resultBean.setMsg(msg);
        return resultBean;
    }
}
