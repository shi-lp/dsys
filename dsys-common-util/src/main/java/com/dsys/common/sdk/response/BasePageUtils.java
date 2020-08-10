package com.dsys.common.sdk.response;

import java.io.Serializable;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import java.util.List;

/**
 * Title: BasePageUtils
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 分页工具类
 * @created 2020/7/30 15:34
 */
@Data
public class BasePageUtils implements Serializable{
    
    private Long currentPage;
    
    private Long pageSize;
    
    private Long total;
    
    private List<T> datas;
    
}
