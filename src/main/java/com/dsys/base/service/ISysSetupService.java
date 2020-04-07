package com.dsys.base.service;

import com.dsys.base.bean.SysSetup;
import java.util.List;

/**
 * Package: com.dsys.base.service
 * Description：
 * Author: shilp
 * Date:  2019/12/26 1:15
 * Modified By:
 */
public interface ISysSetupService {

    List<SysSetup> findSysSetup(String sysCode);

    boolean insertSysSetup(SysSetup sysSetup);
}
