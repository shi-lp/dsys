package com.dsys.goods.util;

import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.common.util.ToolUtil;
import com.dsys.common.util.select.OptionVo;
import java.util.ArrayList;
import java.util.List;

/**
 * Title: GoodsUtils
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/21 16:38
 */
public class GoodsUtils{

    public static List<OptionVo> cateToOption(List<GoodsCategory> cates){
        List<OptionVo> vos = new ArrayList<>();
        if(!ToolUtil.isNullOrEmpty(cates)){
            OptionVo v = null;
            for(GoodsCategory ov : cates){
                v = new OptionVo();
                v.setCode(ov.getCategoryCode());
                v.setName(ov.getCategoryName());
                vos.add(v);
            }
        }
        return vos;
    }
}
