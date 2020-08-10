package com.dsys.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsys.api.bean.base.Model;
import com.dsys.api.bean.base.TreeBean;
import com.dsys.api.common.VxeOption;
import com.dsys.api.service.base.IDictInfoService;
import com.dsys.api.service.base.IModelService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.sdk.response.RenderResponse;
import java.awt.image.RenderedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Title: ModelController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 模块操作
 * @created 2020/5/20 9:30
 */
@RestController
@RequestMapping(value = "/base")
public class ModelController{
    
    @Autowired
    private IModelService modelService;
    
    @Autowired
    private IDictInfoService dictInfoService;
    
    @Reference
    private IUidService uidService;
    
    /**
     *
     * @discription model新增
     * @author shilp
     * @created 2019年12月17日 下午2:59:41
     * @update 2019年12月17日 下午2:59:41
     * @return
     */
    @PostMapping(value="/models")
    public RenderResponse addModel(@RequestBody Model model){
        model.setSId(uidService.getUid());
        if(modelService.addModel(model)){
            return RenderResponse.success();
        }else{
            return RenderResponse.fail("新增模块失败");
        }
    }
    
    @PutMapping(value = "/models")
    public RenderResponse updateModel(@RequestBody Model model) {
        if(modelService.updateModel(model)){
            return RenderResponse.success();
        }
        return RenderResponse.fail("更新失败");
    }
    
    @DeleteMapping(value = "/models/{sid}")
    public RenderResponse delModel(@PathVariable("sid") String sid){
        if(modelService.delModel(sid)){
            return RenderResponse.success();
        }
        return RenderResponse.fail("删除失败");
    }
    
    /**
     * @discription 获取所有的菜单
     * @author shilp
     * @created 2020/7/22  15:31
     * @Param
     * @Return
    */
    @GetMapping(value = "/models")
    public RenderResponse getModels(){
        List<Model> models = modelService.getModels("");
        return RenderResponse.success(models);
    }
    
    @GetMapping(value = "/list/models")
    public RenderResponse getModelLists(@RequestParam("pageSize") Long pageSize,
                                        @RequestParam("current") Long current){
        Page<Model> page = new Page<Model>(current,pageSize);
        IPage<Model> models = modelService.getModelList(page);
        return RenderResponse.success(models);
    }
    
    /**
     * @discription select选项卡
     * @author shilp
     * @created 2020/7/30  9:22
     * @Param
     * @Return
    */
    @GetMapping(value = "/options")
    public RenderResponse getSelectModel(){
        Map<String,List<VxeOption>> renderMap = new HashMap<>();
        VxeOption zeroVo = new VxeOption();
        zeroVo.setValue("");
        zeroVo.setLabel("请输入选择项");
        List<VxeOption> parentList = modelService.getParentOptions("-1","");
        parentList.add(zeroVo);
        List<TreeBean> dictByCode = dictInfoService.getDictByCode("000200010001");
        List<VxeOption> dictOptions = new ArrayList<>();
        VxeOption vo = null;
        for(TreeBean tb : dictByCode){
            vo = new VxeOption();
            vo.setLabel(tb.getTname());
            vo.setValue(tb.getId());
            dictOptions.add(vo);
        }
        dictOptions.add(zeroVo);
        renderMap.put("parentList",parentList);
        renderMap.put("clientTypeList",dictOptions);
        return RenderResponse.success(renderMap);
    }
    
}
