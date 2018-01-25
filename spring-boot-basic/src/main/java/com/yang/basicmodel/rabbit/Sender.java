package com.yang.basicmodel.rabbit;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc 向rabbitMQ中发送消息的类
 * 参考资料 http://www.jianshu.com/p/e1258c004314
 * @author momei
 * @date 2017-12-14 16:13:25
 *
 */
@Component
public class Sender implements ConfirmCallback, ReturnCallback{
	
	Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    
    public void send(String msg,String routingkey) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        
        System.out.println("开始发送消息 : " + msg.toLowerCase());
        String response = rabbitTemplate.convertSendAndReceive("directExchange", routingkey, msg, correlationId).toString();
        System.out.println("结束发送消息 : " + msg.toLowerCase());
        System.out.println("消费者响应 : " + response + " 消息处理完成");
    }


	@Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getCorrelationIdString() + "发送失败!");
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {  
            System.out.println("消息发送成功:" + correlationData);
        } else {  
            System.out.println("消息发送失败:" + cause);
        }
    }
    
    
}
