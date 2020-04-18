package com.dsys.common.sdk.uniquecode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xfvape.uid.impl.CachedUidGenerator;

/**        
 * Title: CachedUidGeneratorConfig.java    
 * Description: 百度id生成器
 * 两种生成器: DefaultUidGenerator、CachedUidGenerator。如对UID生成性能有要求, 请使用CachedUidGenerator
 * 对应Spring配置分别为: default-uid-spring.xml、cached-uid-spring.xml
 * @author shilp    
 * Company:   
 * Copyright: Copyright (c) 2019
 * @created 2019年12月10日 下午9:08:14 
 * @update 2019年12月10日 下午9:08:14 
 * @version 1.0
*/

@Configuration
//@ImportResource(locations = { "classpath:config/cached-uid-spring.xml" })
public class CachedUidGeneratorConfig {
	
	private static final Logger log = LoggerFactory.getLogger(CachedUidGeneratorConfig.class);
	/**
     * 用完即弃的WorkerIdAssigner, 依赖DB操作
     * @return
     */
    @Bean
    public MyDisposableWorkerIdAssigner disposableWorkerIdAssigner(){
        return new MyDisposableWorkerIdAssigner();
    }
    
    @Bean
    public CachedUidGenerator cachedUidGenerator(){
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner());
        //以下为可选配置, 如未指定将采用默认值
        cachedUidGenerator.setTimeBits(29);
        cachedUidGenerator.setWorkerBits(21);
        cachedUidGenerator.setSeqBits(13);
        cachedUidGenerator.setEpochStr("2019-11-20");
 
        //RingBuffer size扩容参数, 可提高UID生成的吞吐量
        //默认:3， 原bufferSize=8192, 扩容后bufferSize= 8192 << 3 = 65536
        cachedUidGenerator.setBoostPower(3);
        // 指定何时向RingBuffer中填充UID, 取值为百分比(0, 100), 默认为50
        // 举例: bufferSize=1024, paddingFactor=50 -> threshold=1024 * 50 / 100 = 512.
        // 当环上可用UID数量 < 512时, 将自动对RingBuffer进行填充补全
        //<property name="paddingFactor" value="50"></property>
 
        //另外一种RingBuffer填充时机, 在Schedule线程中, 周期性检查填充
        //默认:不配置此项, 即不实用Schedule线程. 如需使用, 请指定Schedule线程时间间隔, 单位:秒
        cachedUidGenerator.setScheduleInterval(60L);
 
        //拒绝策略: 当环已满, 无法继续填充时
        //默认无需指定, 将丢弃Put操作, 仅日志记录. 如有特殊需求, 请实现RejectedPutBufferHandler接口(支持Lambda表达式)
        //<property name="rejectedPutBufferHandler" ref="XxxxYourPutRejectPolicy"></property>
        //cachedUidGenerator.setRejectedPutBufferHandler();
        //拒绝策略: 当环已空, 无法继续获取时 -->
        //默认无需指定, 将记录日志, 并抛出UidGenerateException异常. 如有特殊需求, 请实现RejectedTakeBufferHandler接口(支持Lambda表达式) -->
        //<property name="rejectedTakeBufferHandler" ref="XxxxYourTakeRejectPolicy"></property>
        log.info("-----cachedUidGenerator---唯一ID配置类加载完成");
        return cachedUidGenerator;
    }

}
