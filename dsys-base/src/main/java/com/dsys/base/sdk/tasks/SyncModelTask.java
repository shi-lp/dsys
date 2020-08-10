package com.dsys.base.sdk.tasks;

import com.dsys.api.service.base.IAuthService;
import com.dsys.common.util.DateUtils;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Title: SyncModelTask
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 定时同步任务
 * @created 2020/8/5 10:21
 */
@Component
public class SyncModelTask{
    
    @Autowired
    private IAuthService authService;
    
    /**     
     * @discription fixedDelay:每隔多长时间执行一次
     *              cron 秒	分钟	小时	日	月 星期	年
 *                每秒  *   *   *   *   *   *   *
     *             从-到-  1-2
     *             从-开始，每-执行  1/2
     *             指定 1
     * @author shilp
     * @created 2020/8/5  10:24
     * @Param 
     * @Return 
    */
    @Scheduled(fixedDelay = 3*60*1000)
    public void reportCurrentTime(){
        System.out.println("定时任务执行，当前时间为：" + DateUtils.FormatDate(LocalDateTime.now()));
        authService.syncModelToAuth();
    }
}
