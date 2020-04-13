package com.dsys.common;

import java.util.List;

import com.dsys.base.bean.DictInfo;
import com.dsys.base.bean.TreeBean;

/**        
 * Title: IDictInfoService.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月17日 上午9:52:58 
 * @update 2019年12月17日 上午9:52:58 
 * @version 1.0
*/

public interface IDictInfoService {

	public List<DictInfo> getAllDict(String param);
	
	public boolean initDictInfo(DictInfo dictInfo);

	/**
	 * 获取数据字典树
	 * @return
	 */
	public List<TreeBean> getDictInfoTree();

	public boolean addDictInfo(DictInfo dictInfo);
}
