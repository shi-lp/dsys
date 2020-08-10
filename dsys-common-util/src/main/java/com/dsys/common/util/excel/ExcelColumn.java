package com.dsys.common.util.excel;

import java.lang.annotation.*;

/**
 * Title: ExcelColumn
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/5/21 0:01
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn{
    /**
     * Excel标题
     *
     * @return
     * @author Lynch
     */
    String value() default "";
    
    /**
     * Excel从左往右排列位置
     *
     * @return
     * @author Lynch
     */
    int col() default 0;
}
