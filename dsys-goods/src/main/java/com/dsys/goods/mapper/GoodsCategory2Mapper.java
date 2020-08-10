package com.dsys.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.bean.goods.GoodsCategory2;
import java.util.List;

/**     
 * @discription ${在此输入一句话描述此文件的作用}
 * @author shilp       
 * @created 2020/4/24  16:54
 * @Param 
 * @Return 
*/
public interface GoodsCategory2Mapper extends BaseMapper<GoodsCategory2>{
    
    public List<GoodsCategory> selectCateList(String parentCode);
}