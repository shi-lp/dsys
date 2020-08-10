package com.dsys.base.controller;

import com.dsys.api.bean.base.DictInfo;
import com.dsys.api.bean.base.TreeBean;
import com.dsys.api.service.base.IDictInfoService;
import com.dsys.api.service.uid.IUidService;
import com.dsys.common.sdk.response.RenderResponse;
import com.dsys.common.util.Constants;
import com.dsys.common.util.ToolUtil;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Package: com.dsys.base.controller
 * Description：数据字典操作类
 * @Author: shilp
 * Date:  2019/12/27 17:38
 * Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/base")
public class DictInfoController {

    @Autowired
    private IDictInfoService dictInfoService;
    
    @Reference
    private IUidService uidService;

    /**
     * 新增数据字典
     * @param dictInfo
     * {
     *     "dictCode": "000100030002",
     *     "parentCode": "00010003",
     *     "dictName": "唐山市",
     *     "note": "河北省唐山市",
     *     "orderCode": "2",
     *     "identify":"1",
     *     "createUser": "89754731904950272"
     * }
     * @return
     */
    @PostMapping(value = "/dictInfos")
    public RenderResponse addDict(@RequestBody DictInfo dictInfo){
        dictInfo.setSId(uidService.getUid());
        if(ToolUtil.isNullOrEmpty(dictInfo.getParentCode())){
            if(dictInfo.getDictCode().length() > 4){
                dictInfo.setParentCode(dictInfo.getDictCode().substring(0,dictInfo.getDictCode().length() - 4));
            }else{
                dictInfo.setParentCode("-1");
            }
        }
        if(dictInfoService.addDictInfo(dictInfo)){
            return RenderResponse.success("新增成功");
        }
        return RenderResponse.fail("新增失败");
    }
    
    /**
     * @discription 修改数据字典信息
     * @author shilp
     * @created 2020/5/19  14:31
     * @Param
     * @Return
    */
    @PutMapping(value = "/dictInfos")
    public RenderResponse putDictInfo(@RequestBody DictInfo dictInfo){
        if(dictInfoService.updateDictById(dictInfo)){
            return RenderResponse.success("修改成功");
        }
        return RenderResponse.fail("修改失败");
    }

    /**
     * 获取所有的数据字典
     * @param
     * @return 树形结构的json字符串
     */
    @GetMapping(value = "/dictInfos")
    public RenderResponse findDictTree() throws IOException{
        List<TreeBean> dictInfos = dictInfoService.getDictTree(Constants.SYS_RUN,Constants.BASE_DICT_TREE);
        return RenderResponse.success(dictInfos);
    }
    
    @GetMapping(value = "/list/dictInfos")
    public RenderResponse getDictList() {
        List<DictInfo> dictList = dictInfoService.getDictList(Constants.SYS_RUN,Constants.BASE_DICT_LIST);
        return RenderResponse.success(dictList);
    }
    
    /**
     * @discription ajax生成下一级编码信息
     * @author shilp
     * @created 2020/5/19  14:26
     * @Param
     * @Return
    */
    @GetMapping(value = "/dictInfos/local/{code}")
    public RenderResponse nextDictInfo(@PathVariable("code")String code,
                                       @RequestParam("position")String position){
        return RenderResponse.success(dictInfoService.getDictByParentCode(position,code));
    }
    
    /**
     * @discription 通过id获取详情
     * @author shilp
     * @created 2020/7/27  9:12
     * @Param
     * @Return
    */
    @GetMapping(value = "/dictInfos/info/{id}")
    public RenderResponse getDictInfoById(@PathVariable("id") String id) {
        DictInfo di = dictInfoService.getDictInfoByid(id);
        return RenderResponse.success(di);
    }
    
    /**
     * @discription ajax通过父级编码获取所有下级列表
     * @author shilp
     * @created 2020/5/19  14:26
     * @Param
     * @Return
     */
    @GetMapping(value = "/dictInfos/{code}")
    public RenderResponse childrenDictInfo(@PathVariable("code")String code){
        List<TreeBean> trees = dictInfoService.getDictByCode(code);
        return RenderResponse.success(trees);
    }
    
    @DeleteMapping(value = "/dictInfos/{dictCode}")
    public RenderResponse delDictInfo(@PathVariable("dictCode")String dictCode){
        if(dictInfoService.delDictInfoByDictCode(dictCode)){
            return RenderResponse.success("删除成功");
        }
        return RenderResponse.fail("删除失败");
    }
    
    /**
     * @discription 新增地区数据字典（暂时使用）
     * @author shilp
     * @created 2020/5/22  11:39
     * @Param
     * @Return
    */
    @PostMapping(value= "/dict/country")
    public RenderResponse addCountry(){
        dictInfoService.addCountry();
        return RenderResponse.success();
    }


}
