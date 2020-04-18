package com.dsys.common.sdk.response;

/**
 * Title: Mate
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 请求元数据
 * @created 2020/4/14 22:30
 */
public class Meta{
    private boolean success;
    private String message;
    public Meta(boolean success) {
        this.success = success;
    }
    public Meta(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
}
