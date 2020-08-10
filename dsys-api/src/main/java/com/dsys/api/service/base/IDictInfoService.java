package com.dsys.api.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dsys.api.bean.base.DictInfo;

import com.dsys.api.bean.base.TreeBean;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Title: IDictInfoService
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 数据字典编码类
 * @created 2020/4/13 16:30
 */
public interface IDictInfoService extends IService<DictInfo>{
    
    /**     
     * @discription 数据字典编码是否重复
     * @author shilp       
     * @created 2020/4/13  16:40
     * @Param 
     * @Return 
    */
    public boolean existDictInfo (DictInfo dictInfo);
    
    /**     
     * @discription 新增数据字典编码
     * @author shilp       
     * @created 2020/5/19  10:23
     * @Param 
     * @Return 
    */
    public boolean addDictInfo (DictInfo dictInfo);
    
    /**
     * @discription 通过父级编码获取最后值
     * @author shilp
     * @created 2020/5/19  10:30
     * @Param
     * @Return
    */
    public DictInfo getDictByParentCode(String position,String parentCode);
    
    /**     
     * @discription 获取所有的数据字典列表，并加入缓存中
     * @author shilp       
     * @created 2020/5/19  10:32
     * @Param 
     * @Return 
    */
    public List<TreeBean> getDictTree(String sysStatus,String cacheName);
    
    /**
     * @discription 新增地区数据字典
     * @author shilp
     * @created 2020/5/22  11:40
     * @Param
     * @Return
    */
    public void addCountry ();
    
    /**
     * @discription 通过编码获取当前下级
     * @author shilp
     * @created 2020/5/22  11:41
     * @Param
     * @Return
    */
    public List<TreeBean> getDictByCode (String code);
    
    /**
     * @discription 获取所有的数据字典列表
     * @author shilp
     * @created 2020/6/1  16:51
     * @Param
     * @Return
    */
    public List<DictInfo> getDictList (String sysStatus,String dictName);
    
    public DictInfo getDictInfoByid (String pid);
    
    /**
     * @discription 通过dictCode修改
     * @author shilp
     * @created 2020/7/29  16:07
     * @Param
     * @Return
    */
    public boolean updateDictById(DictInfo info);
    
    /**
     * @discription 通过dictCode删除
     * @author shilp
     * @created 2020/7/29  16:07
     * @Param
     * @Return
    */
    boolean delDictInfoByDictCode (String dictCode);
}
