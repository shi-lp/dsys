package com.dsys.base.sdk.common;

import com.dsys.base.bean.DictInfo;
import com.dsys.base.bean.Model;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title: Constants
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/15 8:34
 */
public class Constants{
    /** 角色对应模块的缓存Map,
     *
     */
    public static final ConcurrentHashMap<String, List<Model>> ROLE_MODEL_CACHE = new ConcurrentHashMap<String, List<Model>>();
    
    /**
     * 数据字典缓存模块
     */
    public static final ConcurrentHashMap<String,List<DictInfo>> DICTINFO_CACHE = new ConcurrentHashMap<String, List<DictInfo>>();
    
}
