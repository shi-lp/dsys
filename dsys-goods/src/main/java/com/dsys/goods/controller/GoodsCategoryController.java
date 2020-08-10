package com.dsys.goods.controller;

import com.dsys.api.bean.goods.GoodsCategory;
import com.dsys.api.service.goods.IGoodsCategoryService;
import com.dsys.common.sdk.response.RenderResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Title: GoodsCategoryController
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/7/15 0:55
 */
@RestController
@RequestMapping("/goods")
public class GoodsCategoryController{

    @Autowired
    private IGoodsCategoryService goodsCategoryService;
    
    /**
     * @discription 分类新增
     * @author shilp
     * @created 2020/7/15  0:58
     * @Param
     * @Return
    */
    @PostMapping(value = "/categories")
    public RenderResponse addCategory(@RequestBody GoodsCategory goodsCategory){
        boolean b = goodsCategoryService.addGoodsCategory(goodsCategory);
        if(b) {
            return RenderResponse.success();
        }
        return RenderResponse.fail("新增失败");
    }
    
    /**     
     * @discription 通过等级获取所有分类
     * @author shilp       
     * @created 2020/7/15  10:42
     * @Param 
     * @Return 
    */
    @GetMapping(value = "/categories/{levelNo}")
    public RenderResponse getCategory(@PathVariable(value = "levelNo") String levelNo,
                                      @RequestParam(value = "parentCode") String parentCode){
        List<GoodsCategory> cateList = goodsCategoryService.getCateList(levelNo,parentCode);
        return RenderResponse.success(cateList);
    }
}
