package com.dsys.base.sdk.rabbitmq.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Package: com.dsys.base.sdk
 * Description：
 * Author: shilp
 * Date:  2019/12/30 11:31
 * Modified By:
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitProperties {

    private String host;

    private int port;

    private String username;

    private String password;

}
