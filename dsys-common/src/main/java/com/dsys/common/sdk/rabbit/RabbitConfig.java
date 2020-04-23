package com.dsys.common.sdk.rabbit;

/**
 * Package: com.dsys.base.sdk.rabbitmq.properties
 * Description：
 * Author: shilp
 * Date:  2019/12/30 11:32
 * Modified By:
 */

import com.dsys.common.sdk.rabbit.properties.RabbitProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

/**
 *Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 *  Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 *  Queue:消息的载体,每个消息都会被投到一个或多个队列。
 *  Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 *  Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
 *  vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 *  Producer:消息生产者,就是投递消息的程序.
 *  Consumer:消息消费者,就是接受消息的程序.
 *  Channel:消息通道,在客户端的每个连接里,可建立多个channel.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitConfig {


    @Autowired
    private RabbitProperties rabbitProperties;

    @Value("${rabbitmq.websocket.msg.queue}")
    private String webSocketMsgQueue;

    @Bean
    public ConnectionFactory connectionFactory() throws IOException {
        log.info("--------RabbitMQ配置加载开始---------");
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUsername(rabbitProperties.getUsername());
        factory.setPassword(rabbitProperties.getPassword());
        factory.setHost(rabbitProperties.getHost());
        factory.setPort(rabbitProperties.getPort());
        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);

        //设置队列参数，是否持久化、队列TTL、队列消息TTL等
        factory.createConnection().createChannel(false).queueDeclare(webSocketMsgQueue, true, false, false, null);
        log.info("--------RabbitMQ配置结束加载---------");
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    // 必须是prototype类型
    public RabbitTemplate rabbitTemplate() throws IOException {
        connectionFactory().isPublisherConfirms();
        connectionFactory().isPublisherReturns();
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }

    /**
     * 多个消费者
     * @return
     * @throws IOException
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() throws IOException {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
//        并发消费者的初始化值
        factory.setConcurrentConsumers(3);
//        并发消费者的最大值
        factory.setMaxConcurrentConsumers(10);
//        每个消费者每次监听时可拉取处理的消息数量
        factory.setPrefetchCount(5);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
