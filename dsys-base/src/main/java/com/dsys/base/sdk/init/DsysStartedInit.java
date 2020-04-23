package com.dsys.base.sdk.init;

import com.dsys.base.service.IDictInfoService;
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
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("--------初始化信息开始加载-------");
        initDictInfos();
        initDictInfoMap();
        initModels();
        log.info("--------初始化信息加载结束-------");
    }

    /**
     * 加载所有字典信息
     * cache:key:dictInfoAll
     */
    private void initDictInfos() {
        log.info("--------数据字典信息开始加载-------");
//        dictInfoService.getAllDict("All");
    
    }

    /**
     * 生成字典树
     */
    private void initDictInfoMap() {
        log.info("--------数据字典树开始加载-------");
    }

    /**
     * 加载所有模块信息
     */
    private void initModels() {
        log.info("--------模块信息开始加载-------");
    }


}
