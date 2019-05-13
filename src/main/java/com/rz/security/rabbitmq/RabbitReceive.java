package com.rz.security.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-05-10
 * Time: 上午9:58
 */
@Component
public class RabbitReceive {

    /**
     * 订阅模式的接收者
     */
    @RabbitListener(queues = "fanout-1")
    public void processA(String message) {
        System.out.println("fanout-1接收ReceiveA:" + message);
    }

    @RabbitListener(queues = "fanout-2")
    public void processB(String message) {
        System.out.println("fanout-2接收ReceiveB:" + message);
    }

    @RabbitListener(queues = "fanout-3")
    public void processC(String message) {
        System.out.println("fanout-3接收ReceiveC:" + message);
    }


    /**
     * topic模式接收
     * @param context
     */
    @RabbitListener(queues = "topic.message")
    public void process(String context) {
        System.out.println("队列topic.message接收到的消息：" + context);
    }

    @RabbitListener(queues = "topic.messages")
    public void process2(String context) {
        System.out.println("队列topic.messages接收到的消息：" + context);
    }

    @RabbitListener(queues = "work-queue")
    @RabbitHandler
    public void work(String message){
        System.out.println("收到的消息：" + message);
    }

}
