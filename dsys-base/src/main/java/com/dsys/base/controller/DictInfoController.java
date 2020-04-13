package com.dsys.base.controller;

import com.dsys.base.bean.DictInfo;
import com.dsys.base.service.IDictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Package: com.dsys.base.controller
 * Description：数据字典操作类
 * Author: shilp
 * Date:  2019/12/27 17:38
 * Modified By:
 */
@RestController
@RequestMapping("/dictInfo")
public class DictInfoController {

    @Autowired
    private IDictInfoService dictInfoService;

    /**
     *
     * @param dictInfo
     * @return
     */
    @PostMapping("/addDict")
    public Map <String,Object> addDict(@RequestBody DictInfo dictInfo){
        Map<String,Object> renderJson = new HashMap <String,Object>();
        if(dictInfoService.existDictInfo(dictInfo)){
            dictInfoService.addDictInfo(dictInfo);
        }
        return renderJson;
    }

    /**
     *
     * @param
     * @return
     */
    @PostMapping("/showDictTree")
    public Map <String,Object> findDictTree(@RequestParam String userNumber){
        Map<String,Object> renderJson = new HashMap <String,Object>();
        return renderJson;
    }


}
