package com.dsys.base.controller;

import com.dsys.api.bean.base.SysSetup;
import com.dsys.api.service.base.ISysSetupService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.sdk.response.RenderResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Package: com.dsys.base.controller
 * Description：系统配置前端控制
 * @Author: shilp
 * Date:  2019/12/26 9:12
 * Modified By:
 */
@Slf4j
@RequestMapping("/base/sysSetup")
@RestController
public class SysSetupController {
    
    @Autowired
    private ISysSetupService sysSetupService;
    
    @Reference
    private IUidService uidService;

    /**
     * @discription 新增系统配置
     * @author shilp
     * @created 2020/5/19  15:11
     * @Param
     * @Return
    */
    @PostMapping(value = "/sysSetups")
    public RenderResponse addUser(@RequestBody SysSetup sysSetup) {
        sysSetup.setSId(uidService.getUid());
        sysSetup.setActiveTime(new Timestamp(System.currentTimeMillis()));
        if(sysSetupService.insertSysSetup(sysSetup)){
            return RenderResponse.success("新增成功");
        }
        return RenderResponse.fail("新增失败");
    }
    
    /**
     * @discription 更新系统配置
     * @author shilp
     * @created 2020/5/22  17:24
     * @Param
     * @Return
    */
    @PutMapping(value = "/sysSetups")
    public RenderResponse update(@RequestBody SysSetup sysSetup){
        if(sysSetupService.updateSys(sysSetup)){
            return RenderResponse.success();
        }
        return RenderResponse.fail("更新失败");
    }
    
    /**
     * @discription 获取系统配置列表
     * @author shilp
     * @created 2020/5/19  15:13
     * @Param
     * @Return
    */
    @GetMapping(value = "/sysSetups")
    public RenderResponse sysSetupList(){
        List<SysSetup> sysSetupList = sysSetupService.list();
        return RenderResponse.success(sysSetupList);
    }
    
    /**
     * @discription 新增时，查看代码是否重复
     * @author shilp
     * @created 2020/6/11  11:13
     * @Param
     * @Return
    */
    @GetMapping(value = "/sysSetups/{code}")
    public RenderResponse sysSetupCodeExist(@PathVariable(value = "code")String sysCode){
        if(sysSetupService.exist(sysCode)){
            return RenderResponse.success();
        }
        return RenderResponse.fail("编码已经存在");
    }

}
