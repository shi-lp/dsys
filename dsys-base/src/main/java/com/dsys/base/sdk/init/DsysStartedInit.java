package com.dsys.base.sdk.init;

import com.dsys.api.service.base.IDictInfoService;
import com.dsys.api.service.base.IModelService;
import com.dsys.api.service.base.ISysSetupService;
import com.dsys.common.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 
 * ClassName: DsysStart 
 * @Description: 启动后加载信息
 * @author shilp
 * @date 2019年11月5日
 */
@Slf4j
@Component
public class DsysStartedInit implements ApplicationRunner {

    @Autowired
    private IDictInfoService dictInfoService;
    
    @Autowired
    private IModelService modelService;
    
    @Autowired
    private ISysSetupService sysSetupService;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化启动参数"+args.getOptionNames());
        log.info("--------初始化信息开始加载-------");
        initDictInfos(Constants.SYS_START);
        initDictInfoMap(Constants.SYS_START);
        initModels(Constants.SYS_START);
        initSysSetup(Constants.SYS_START);
        log.info("--------初始化信息加载结束-------");
    }
    
    /**
     * @discription 查询所有的系统配置表信息
     * @author shilp
     * @created 2020/6/10  15:28
     * @Param
     * @Return
    */
    private void initSysSetup (String sysStatus){
        log.info("-------系统配置加载 start------");
        log.info("系统配置列表加载");
        sysSetupService.insertListCache(Constants.BASE_SYS_LIST);
        log.info("系统配置Map加载");
        sysSetupService.insertMapCache(Constants.BASE_SYS_MAP);
        log.info("-------系统配置加载 end------");
    }
    
    /**
     * 加载所有字典信息
     * cache:key:dictInfoAll
     */
    private void initDictInfos(String sysStatus) {
        log.info("--------数据字典信息开始加载-------");
        dictInfoService.getDictList(sysStatus,Constants.BASE_DICT_LIST);
        log.info("--------数据字典信息加载完成-------");
        log.info("--------数据字典树开始加载-------");
        dictInfoService.getDictTree(sysStatus,Constants.BASE_DICT_TREE);
        log.info("--------数据字典树加载完成-------");
    }

    /**
     * 生成字典树
     */
    private void initDictInfoMap(String sysStatus) {
    
    }

    /**
     * 加载所有模块信息
     */
    private void initModels(String sysStatus) {
        log.info("--------模块信息开始加载-------");
        modelService.StatInitModels(sysStatus,Constants.BASE_MODEL_LIST);
        log.info("--------模块信息加载完成-------");
    }


}
