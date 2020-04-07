package com.dsys.base.util.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Title: ResponseResult
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/3/25 17:07
 */
@Getter
@Setter
@ToString
public class ResponseResult implements Serializable{
    
    /**
     * 业务响应状态
     */
    public int status = 200;
    
    public boolean responseStatus;
    
    /**
     * 响应消息
     */
    public String msg = "操作成功";
    
    /**
     * 响应数据
     */
    public Object data = null;
    
    /**
     *
     * <p>
     * Title: 响应失败
     * </p>
     *
     * @return ResponseResult
     */
    public static ResponseResult fail() {
        return fail("操作失败");
    }
    
    /**
     *
     * <p>
     * Title: 响应失败，但是自定义响应消息l
     * </p>
     *
     * @param msg 需要自定义的响应消息
     * @return ResponseResult
     */
    public static ResponseResult fail(String msg) {
        return bulid(500, false, msg, null);
    }
    
    /**
     *
     * <p>
     * Title: 成功并且传递数据，并且自定义响应消息内容
     * </p>
     *
     * @param msg  响应消息内容
     * @param data 响应数据
     * @return ResponseResult
     */
    public static ResponseResult ok(String msg, Object data) {
        return bulid(200,true, msg, data);
    }
    
    /**
     *
     * <p>
     * Title: 成功并且传递数据，但是不自定义消息
     * </p>
     *
     * @param data 需要传递的数据
     * @return ResponseResult
     */
    public static ResponseResult ok(Object data) {
        return ok("操作成功", data);
    }
    
    /**
     *
     * <p>
     * Title: 成功，不传递信息，也不自定义信息
     * </p>
     *
     * @return ResponseResult
     */
    public static ResponseResult ok() {
        return ok("操作成功", null);
    }
    
    /**
     * '
     *
     * <p>
     * Title: 成功，不传递数据，但是需要自定响应消息
     * </p>
     *
     * @param msg 需要自定义的响应消息
     * @return ResponseResult
     */
    public static ResponseResult ok(String msg) {
        return ok(msg, null);
    }
    
    /**
     *
     * <p>
     * Title: 自定义响应结构
     * </p>
     *
     * @param status 响应状态
     * @param msg    响应消息
     * @param data   响应数据
     * @return ResponseResult
     */
    public static ResponseResult bulid(int status,boolean responseStatus,String msg, Object data) {
        return new ResponseResult(status,responseStatus, msg, data);
    }
    
    public ResponseResult() {
        super();
    }
    
    public ResponseResult (int status,boolean responseStatus,String msg,Object data){
        super();
        this.status = status;
        this.responseStatus = responseStatus;
        this.msg = msg;
        this.data = data;
    }
    
    
}
