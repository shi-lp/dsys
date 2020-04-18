package com.dsys.base.bean;

import java.util.List;

import lombok.Data;

/**
 * Title: TreeBean.java
 * Description: 构建树
 * @author shilp
 * Company:
 * Copyright: Copyright (c) 2019
 * @created 2019年12月18日 下午3:22:01
 * @update 2019年12月18日 下午3:22:01
 * @version 1.0
 */
@Data
public class TreeBean {
    
    private String id;
    
    private String pid;
    
    private String name;
    
    private boolean open;
    
    private boolean isParent;
    
    private boolean checked;
    
    private int order;
    
    private List<TreeBean> children;
    
}
