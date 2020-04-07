package com.dsys.base.dao;

import java.util.List;
import com.dsys.base.bean.Model;

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
public interface ModelDao {

	public List<Model> findAllModel();

	int deleteByPrimaryKey(String sId);

	public int addModel(Model record);

	int insertSelective(Model record);

	Model selectByPrimaryKey(String sId);

	int updateByPrimaryKeySelective(Model record);

	int updateByPrimaryKey(Model record);

}
