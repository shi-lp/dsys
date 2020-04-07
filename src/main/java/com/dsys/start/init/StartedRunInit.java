package com.dsys.start.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Package: com.dsys.start.init
 * Description：启动后加载
 * Author: shilp
 * Date:  2019/12/27 14:50
 * Modified By:
 */
@Slf4j
@Component
public class StartedRunInit implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("启动后执行1");
    }
}
