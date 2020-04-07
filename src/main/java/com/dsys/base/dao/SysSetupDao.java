package com.dsys.base.dao;

import com.dsys.base.bean.SysSetup;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Package: com.dsys.base.dao
 * Description：
 * Author: shilp
 * Date:  2019/12/26 1:09
 * Modified By:
 */
//@Mapper
public interface SysSetupDao {

    int insert(SysSetup record);

    int insertSelective(SysSetup record);

    List<SysSetup> findSysSetup(String sysCode);
}
