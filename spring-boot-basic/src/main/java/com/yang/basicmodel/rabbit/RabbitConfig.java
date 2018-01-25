package com.yang.basicmodel.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
* @author yf
* rabbitmq配置类
* @date 创建时间：2017年12月8日 下午4:02:19 
* @version 1.0   
*/
@Configuration
public class RabbitConfig {
   //队列信息
   @Autowired	
   private RabbitQueue rabbitQueue;
   
   /*
    * Direct Exchange是RabbitMQ默认的交换机模式，也是最简单的模式，根据key全文匹配去寻找队列。
    * */
 
    /**
     * @desc  声明普通消息队列
     * @return
     */
    @Bean
    public Queue messageQueue() {
        return new Queue(rabbitQueue.getMessageQueue(), true);
    }
    
    /**
     * @功能描述
     * @return
     */
    @Bean
    DirectExchange directExchange() {
    	return new DirectExchange("directExchange");
    }
    
    /**
     * @desc 绑定普通消息队列
     * 绑定一个key "direct"，当消息匹配到就会放到这个队列中
     * @return
     */
    @Bean
    public Binding bindingMessageQueue() {
       return BindingBuilder.bind(messageQueue()).to(directExchange()).with(rabbitQueue.getRoutingkeyMessage()); 	
    }

    
    /*
     * Fanout
     * 描述:配置广播模式或者订阅模式队列
     * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
     * */
    
    @Bean
    public Queue queueFanoutku() {
        return new Queue("fanout.yang.queue");
    }
    
    /**
     * 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有队列上。
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }
    
    @Bean
    Binding bindingExchangeQueueSouyunku() {
        return BindingBuilder.bind(queueFanoutku()).to(fanoutExchange());
    }
    
    /*
     * :配置转发消息模式队列
     * */
    
    @Bean
    public Queue queuetopic() {
        return new Queue("topic.message");
    }
    
    @Bean
    public Queue queuetopic1() {
        return new Queue("topic.message.you");
    }
    
    @Bean
    public Queue queuetopic2() {
        return new Queue("topic.me");
    }
    /**
     * 交换机(Exchange) 描述：接收消息并且转发到绑定的队列，交换机不存储消息
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
   
        return new TopicExchange("topicExchange");
    }
    
  //綁定队列 queueMessages() 到 topicExchange 交换机,路由键只接受完全匹配 topic.message 的队列接受者可以收到消息
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(queuetopic()).to(topicExchange()).with("topic.message");
    }

    //綁定队列 queueMessages() 到 topicExchange 交换机,路由键只要是以 topic.message 开头的队列接受者可以收到消息
    @Bean
    Binding bindingExchangeMessages() {
        return BindingBuilder.bind(queuetopic1()).to(topicExchange()).with("topic.message.#");
    }

    //綁定队列 queueYmq() 到 topicExchange 交换机,路由键只要是以 topic 开头的队列接受者可以收到消息
    @Bean
    Binding bindingExchangeYmq() {
        return BindingBuilder.bind(queuetopic2()).to(topicExchange()).with("topic.#");
    }

}
