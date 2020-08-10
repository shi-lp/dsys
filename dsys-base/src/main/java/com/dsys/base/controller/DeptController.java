package com.dsys.base.controller;

import com.dsys.api.bean.base.Dept;
import com.dsys.api.common.VxeOption;
import com.dsys.api.service.base.IDeptService;
import com.dsys.common.sdk.response.RenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Title: DeptController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 部门操作类
 * @created 2020/6/16 16:21
 */
@RestController
@RequestMapping("/base")
public class DeptController{
    
    @Autowired
    private IDeptService deptService;
    
    /**
     * @discription 部门新增
     * @author shilp
     * @created 2020/6/16  16:29
     * @Param
     * @Return
    */
    @PostMapping(value = "/depts")
    public RenderResponse addDept(@RequestBody Dept dept){
        return RenderResponse.success();
    }
    
    /**
     * @discription 获取部门列表
     * @author shilp
     * @created 2020/6/16  16:32
     * @Param
     * @Return
    */
    @GetMapping(value = "/depts/{deptId}")
    public RenderResponse getDeptsList(@PathVariable(value = "deptId") String deptId){
        List<Dept> deptList = deptService.list();
        return  RenderResponse.success(deptList);
    }
    
    @GetMapping(value = "/depts/options")
    public RenderResponse getDeptOption(){
        List<VxeOption> options = deptService.getDeptOption();
        return RenderResponse.success(options);
    }
}
