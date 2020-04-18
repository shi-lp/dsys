package com.dsys.common.sdk.response;

import java.io.Serializable;

/**
 * Title: DsysResponse
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 统一响应结构
 * 使用REST框架实现前后端分离架构，我们需要首先确定返回的JSON响应结构是统一的，
 * 也就是说，每个REST请求将返回相同结构的JSON响应结构。不妨定义一个相对通用的JSON响应结构，
 * 其中包含两部分：元数据与返回值，其中，元数据表示操作是否成功与返回值消息等，返回值对应服务端方法所返回的数据。
 * { "meta": { "success": true, "message": "ok" }, "data": ... }
 * @created 2020/4/14 22:25
 */
public class DsysResponse implements Serializable{
    
    private static final String OK = "ok";
    
    private static final String ERROR = "error";
    
    /**
     * @discription 元数据
    */
    private Meta meta;
    
    /**
     * @discription 响应内容
    */
    private Object data;
    
    public DsysResponse success() {
        this.meta = new Meta(true, OK);
        return this;
    }
    
    public DsysResponse success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }
    
    public DsysResponse failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }
    public DsysResponse failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }
    
    public Meta getMeta() {
        return meta;
    }
    public Object getData() {
        return data;
    }
}
