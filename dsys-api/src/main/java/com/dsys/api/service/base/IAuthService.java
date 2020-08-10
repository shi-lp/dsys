package com.dsys.api.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.base.Auth;
import com.dsys.api.bean.base.Model;
import java.util.List;
import java.util.Map;

/**
 * Title: IAuthService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 权限表接口
 * @created 2020/5/19 16:20
 */
public interface IAuthService extends IService<Auth>{
    
    /**
     * @discription 新增权限信息
     * @author shilp
     * @created 2020/5/19  16:44
     * @Param
     * @Return
    */
    public boolean addAuth (Auth auth);
    
    /**
     * @discription 获取所有的模块权限列表
     * @author shilp
     * @created 2020/6/16  17:08
     * @Param
     * @Return
    */
    public Map<Model, List<Auth>> getAuthModel (String roleId);
    
    /**
     * @discription 添加定时任务，同步模块与权限
     * @author shilp
     * @created 2020/8/5  11:07
     * @Param
     * @Return
     */
    public void syncModelToAuth();
    
    public List<Auth> getAuthList ();
    
    /**     
     * @discription 查看编码是否存在
     * @author shilp       
     * @created 2020/8/6  17:15
     * @Param 
     * @Return 
    */
    public boolean existCode (String code);
}
