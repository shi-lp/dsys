package com.dsys.base.bean.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: RenderResult
 *
 * @author shilp
 * Company: dsys
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 请求返回格式
 * @created 2020/1/19 17:00
 */
@Data
public class RenderResult implements Serializable {

    private int status;

    private String message;

    private String token;

    private Map <String,Object> data = new HashMap <String,Object>();

    public void putData(String key, Object value) {
        data.put(key, value);
    }

    public void removeData(String key) {
        data.remove(key);
    }

    public RenderResult(int status, String message){
        this.status = status;
        this.message = message;
    }
}
