package com.rz.security.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * Created with IntelliJ IDEA.
 * Description: 消息的生产者
 * User: silence
 * Date: 2019-05-10
 * Time: 上午9:17
 */
@Component
public class RabbitSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * workQueue 工作模式测试
     * @param message
     */
    public void workSend(String message){
        //参数1：队列名称；参数2：消息
        rabbitTemplate.convertAndSend("work-queue",message);
    }

    /**
     * 订阅模式发送信息测试
     * @param message
     */
    public void fanout(String message){
        //参数1：交换机名称 参数2：不论填什么都无效，参数3：消息
        rabbitTemplate.convertAndSend("fanoutExchangeTest","",message);
    }

    /**
     * topic模式  匹配routing_key接受信息
     * @param message
     */
    public void topicSend(String message){
        //参数1：交换机名称，参数2 ： routing_key规则：模糊 *匹配一个单词，#匹配多个单词（或者零个） ，参数
        rabbitTemplate.convertAndSend("topicExchangeTest","topic.message",message);
        rabbitTemplate.convertAndSend("topicExchangeTest","topic.messages",message);
    }

}
