package com.dsys.common.worknode.service.impl;

import com.dsys.common.worknode.service.IUidService;

import com.xfvape.uid.impl.CachedUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**        
 * Title: UidServiceImpl.java    
 * Description: 描述
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月10日 下午10:26:09 
 * @update 2019年12月10日 下午10:26:09 
 * @version 1.0
*/
@Service
public class UidServiceImpl implements IUidService{

    @Autowired
    private CachedUidGenerator cachedUidGenerator;
	
    @Override
    public long getUid() {
        return cachedUidGenerator.getUID();
    }

}
