package com.dsys.api.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * Title: DoFlagNum
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: doFlag枚举类型
 * @created 2020/8/7 16:42
 */
@Getter
public enum DoFlagEnum implements IEnum<Integer>{
    
    ENABLED(1, "启用"),
    DELETED(2, "已删除"),
    DISABLED(0, "禁用");
    
    private final int code;
    
    private final String descp;
    
    DoFlagEnum(final int code, final String descp) {
        this.code = code;
        this.descp = descp;
    }
    
    @Override
    public Integer getValue (){
        return code;
    }
}
