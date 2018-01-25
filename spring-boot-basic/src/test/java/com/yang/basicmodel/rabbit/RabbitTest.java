package com.yang.basicmodel.rabbit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 测试类
 * 用于单元测试
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTest {
    
    @Autowired
    RabbitAdmin admin;
    
    @Test
    public void redisTest(){
        Queue queue = new Queue("myQueue");
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange("myExchange");
        admin.declareExchange(exchange);
        admin.declareBinding(
            BindingBuilder.bind(queue).to(exchange).with("foo.*"));

        // set up the listener and container
//        SimpleMessageListenerContainer container =
//                new SimpleMessageListenerContainer(cf);
//        Object listener = new Object() {
//            public void handleMessage(String foo) {
//                System.out.println(foo);
//            }
//        };
//        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
//        container.setMessageListener(adapter);
//        container.setQueueNames("myQueue");
//        container.start();
//
//        // send something
//        RabbitTemplate template = new RabbitTemplate(cf);
//        template.convertAndSend("myExchange", "foo.bar", "Hello, world!");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        container.stop();
    }
}
