package com.dsys.base.service.impl;

import java.util.List;

import com.dsys.base.bean.TreeBean;
import com.dsys.base.dao.DictInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.dsys.base.bean.DictInfo;
import com.dsys.base.service.IDictInfoService;

/**        
 * Title: DictInfoServiceImpl.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月17日 上午9:55:36 
 * @update 2019年12月17日 上午9:55:36 
 * @version 1.0
*/
@Primary
@Service
public class DictInfoServiceImpl implements IDictInfoService {

	@Autowired
	private DictInfoDao dictInfoDao;

	/**
	 * 启动时缓存查询结果
	 * @param param
	 * @return
	 */
	@Override
	@CachePut(value="dictInfo",key="#p0")
	public List<DictInfo> getAllDict(String param) {
		return dictInfoDao.findAllDictInfo(null);
	}

	@Override
	public boolean initDictInfo(DictInfo dictInfo) {
		if(dictInfoDao.insert(dictInfo) > 0){
			return true;
		}
		return false;
	}

	@Override
	public List <TreeBean> getDictInfoTree() {
		return dictInfoDao.dictInfo2Tree("All");
	}

	@Override
	public boolean addDictInfo(DictInfo dictInfo) {
		if(dictInfoDao.insert(dictInfo) > 0){
			return true;
		}
		return false;
	}

}
