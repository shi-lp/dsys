package com.dsys.common.sdk.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dsys.common.util.DateUtils;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * Title: MyMetaObjectHandler
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: 填充处理器 MyMetaObjectHandler 在 Spring Boot 中需要声明 @Component 或 @Bean 注入
 * strictInsertFill 和 strictUpdateFill 方法第二个参数写的是实体类里的属性名，不是对应数据库字段名。
 * @created 2020/8/7 17:13
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler{
    @Override
    public void insertFill (MetaObject metaObject){
        log.info("start insert fill ....");
        this.fillStrategy(metaObject, "createTime", Timestamp.valueOf(DateUtils.FormatDate(LocalDateTime.now())));
    }
    
    @Override
    public void updateFill (MetaObject metaObject){
        log.info("start update fill ....");
        this.fillStrategy(metaObject, "updateTime",Timestamp.valueOf(DateUtils.FormatDate(LocalDateTime.now())));
    }
}
