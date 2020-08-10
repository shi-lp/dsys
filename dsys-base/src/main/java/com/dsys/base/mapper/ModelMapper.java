package com.dsys.base.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsys.api.bean.base.Model;
import com.dsys.api.common.VxeOption;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**        
 * Title: ModelDao.java    
 * Description: 模块数据库操作类
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月16日 下午7:54:21 
 * @update 2019年12月16日 下午7:54:21 
 * @version 1.0
*/
public interface ModelMapper extends BaseMapper<Model>{
    
    public IPage<Model> selectModelList (Page<Model> page,@Param(Constants.WRAPPER) Wrapper<Model> wrapper);
    
    public List<VxeOption> getOptions(@Param(Constants.WRAPPER) Wrapper<Model> entityWrapper);
}
