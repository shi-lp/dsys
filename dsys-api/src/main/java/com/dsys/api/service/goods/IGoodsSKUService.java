package com.dsys.api.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.goods.GoodsSKU;

/**
 * Title: IGoodsSKUService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/5/19 16:26
 */
public interface IGoodsSKUService extends IService<GoodsSKU>{
    
    public boolean addSKU (GoodsSKU goodsSKU);
}
