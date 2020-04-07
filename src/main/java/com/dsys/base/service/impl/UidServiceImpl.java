package com.dsys.base.service.impl;

import javax.annotation.Resource;

import com.xfvape.uid.impl.CachedUidGenerator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.xfvape.uid.UidGenerator;

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
@Primary
@Service
public class UidServiceImpl{

	@Resource(name="cachedUidGenerator")
    private CachedUidGenerator cachedUidGenerator;
	
    public long getUid() {
        return cachedUidGenerator.getUID();
    }

}
