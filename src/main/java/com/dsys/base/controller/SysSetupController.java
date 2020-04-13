package com.dsys.base.controller;

import com.dsys.base.bean.SysSetup;
import com.dsys.common.ISysSetupService;
import com.dsys.base.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.dsys.base.controller
 * Description：
 * Author: shilp
 * Date:  2019/12/26 9:12
 * Modified By:
 */
@Slf4j
@RequestMapping("/sysSetup")
@RestController
public class SysSetupController {
    
    private ISysSetupService sysSetupService;

    @PostMapping(value = "/addSysSetup")
    public Map <String,Object> addUser(@RequestBody SysSetup sysSetup) {
        Map<String,Object> renderMap = new HashMap <String,Object>();
        if(sysSetupService.insertSysSetup(sysSetup)){
            renderMap.put("status", Constants.STATUS_SUCCESS);
            renderMap.put("msg","新增成功");
        }else{
            renderMap.put("status", Constants.STATUS_ERROR);
            renderMap.put("msg","新增失败");
        }
        return renderMap;
    }

}
