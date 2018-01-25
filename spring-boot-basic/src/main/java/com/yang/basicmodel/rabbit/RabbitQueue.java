package com.yang.basicmodel.rabbit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @desc 与rabbitMQ相关配置变量
 * @date 2017-12-14 16:17:09
 *
 */
@Component
public class RabbitQueue {

	/**
	 * 普通消息队列
	 */
	 @Value("${spring.rabbitmq.messageQueue}")
	private String messageQueue;	

	
	/**
	 * 普通消息的routingkey
	 */
	@Value("${spring.rabbitmq.routingkeyMessage}")
	private String routingkeyMessage;


    public String getMessageQueue() {
        return messageQueue;
    }

    public String getRoutingkeyMessage() {
        return routingkeyMessage;
    }

}
