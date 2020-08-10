package com.dsys.uid.service.impl;

import com.dsys.api.service.uid.IUidService;
import com.xfvape.uid.impl.CachedUidGenerator;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
