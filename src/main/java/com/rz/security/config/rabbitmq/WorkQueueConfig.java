package com.rz.security.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Created with IntelliJ IDEA.
 * Description: 工作队列模式  一个生产者一个消费者  ，一条消息只能由一个消费者接受执行
 * User: silence
 * Date: 2019-05-10
 * Time: 上午8:52
 */
@Configuration
public class WorkQueueConfig {

    @Bean
    public Queue work(){
        return new Queue("work-queue");
    }

}
