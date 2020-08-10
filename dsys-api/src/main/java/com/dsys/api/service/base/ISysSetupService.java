package com.dsys.api.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.base.SysSetup;
import java.util.List;
import java.util.Map;

/**
 * Package: com.dsys.base.service
 * Description：系统配置参数
 * Date:  2019/12/26 1:15
 * Modified By:
 * @author shilp
 */
public interface ISysSetupService extends IService<SysSetup>{


    /**
     * @discription 新增系统配置参数
     * @author shilp
     * @created 2020/5/22  17:14
     * @Param
     * @Return
    */
    public boolean insertSysSetup (SysSetup sysSetup);
    
    /**
     * @discription 更新系统配置参数
     * @author shilp
     * @created 2020/5/22  17:21
     * @Param
     * @Return
    */
    public boolean updateSys (SysSetup sysSetup);
    
    /**
     * @discription 判断系统配置参数是否存在
     * @author shilp
     * @created 2020/6/11  11:08
     * @Param 不存在 返回true  存在 false
     * @Return
    */
    public boolean exist (String sysCode);
    
    /**
     * @discription 将系统配置添加至缓存中
     * @author shilp
     * @created 2020/6/11  11:17
     * @Param
     * @Return
    */
    public List<SysSetup> insertListCache(String sysList);
    
    /**
     * @discription 将系统配置map添加至缓存中
     * @author shilp
     * @created 2020/6/11  11:20
     * @Param
     * @Return
    */
    public Map<String,SysSetup> insertMapCache(String sysMap);
    
    
    /**
     * @discription 通过Map获取值
     * @author shilp
     * @created 2020/6/12  15:01
     * @Param
     * @Return
    */
    public String getSysValue(String sysMap,String sysCode);
}
