package com.dsys.api.bean.token;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * Title: TokenBean
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: token规则生成类
 * @created 2020/5/15 14:43
 */
public class TokenBean{
    
    private String key;
    
    private String json;
    
    /**盐值，IP+time，*/
    private String salt;
    
    private Map<String,Object> objectMap = new HashMap<>();
    
    private String ip;
    
    private long loginTime = System.currentTimeMillis();
}
