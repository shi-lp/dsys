package com.dsys.api.bean.base;

import java.io.Serializable;
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
public class TreeBean implements Serializable{
    
    private String id;
    
    private String pid;
    
    private String tname;
    
    private boolean open = false;
    
    private boolean isParent = true;
    
    private boolean checked = false;
    
    private int orderNum;
    
    private List<TreeBean> children;
    
}
