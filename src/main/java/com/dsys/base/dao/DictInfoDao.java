package com.dsys.base.dao;

import com.dsys.base.bean.DictInfo;
import com.dsys.base.bean.TreeBean;
import java.util.List;

/**        
 * Title: DictInfoDao.java    
 * Description: 数据字典数据库操作类
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月16日 下午7:55:13 
 * @update 2019年12月16日 下午7:55:13 
 * @version 1.0
*/

public interface DictInfoDao {

    int deleteByPrimaryKey(String sId);

    int insert(DictInfo record);

    int insertSelective(DictInfo record);

    List<DictInfo> findAllDictInfo(String dictCode);

    int updateByPrimaryKeySelective(DictInfo record);

    int updateByPrimaryKey(DictInfo record);

    List<TreeBean> dictInfo2Tree(String param);

}
