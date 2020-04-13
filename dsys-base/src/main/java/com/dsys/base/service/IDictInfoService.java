package com.dsys.base.service;

import com.baomidou.mybatisplus.service.IService;
import com.dsys.base.bean.DictInfo;

/**
 * Title: IDictInfoService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/13 16:30
 */
public interface IDictInfoService extends IService<DictInfo>{
    
    /**     
     * @discription 数据字典编码是否重复
     * @author shilp       
     * @created 2020/4/13  16:40
     * @Param 
     * @Return 
    */
    boolean existDictInfo (DictInfo dictInfo);
    
    void addDictInfo (DictInfo dictInfo);
}
