package com.rz.security.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Created with IntelliJ IDEA.
 * Description: 发送者将消息发送到交换机中，然后消费者匹配routing_key（队列名）去消费消息，一条消息，多次消费
 * User: silence
 * Date: 2019-05-10
 * Time: 上午9:12
 */
@Configuration
public class TopicConfig {


    final static String message = "topic.message";
    final static String messages = "topic.messages";

    @Bean
    public Queue queueMessage() {
        return new Queue(TopicConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicConfig.messages);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchangeTest");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

}
