package com.dsys.api.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.base.Dept;
import com.dsys.api.common.VxeOption;
import java.util.List;

/**
 * Title: IDeptService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/6/16 16:23
 */
public interface  IDeptService extends IService<Dept>{
    
    public List<VxeOption> getDeptOption ();
}
