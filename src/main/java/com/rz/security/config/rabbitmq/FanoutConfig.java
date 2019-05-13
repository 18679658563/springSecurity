package com.rz.security.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Created with IntelliJ IDEA.
 * Description: 发布/订阅模式，生产者将消息发送到交换机中，交换器绑定多个队列,然后被监听该队列的消费者所接收并消费.
 * User: silence
 * Date: 2019-05-10
 * Time: 上午8:59
 */
@Configuration
public class FanoutConfig {

    /**
     * 配置接受交换机的队列
     */

    @Bean(name = "fanoutA")
    public Queue fanoutA(){
        return new Queue("fanout-1");
    }

    @Bean(name = "fanoutB")
    public Queue fanoutB(){
        return new Queue("fanout-2");
    }

    @Bean(name = "fanoutC")
    public Queue fanoutC(){
        return new Queue("fanout-3");
    }

    /**
     * 配置交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchangeTest");
    }

    /**
     * 绑定交换机
     */

    @Bean
    Binding bindingExchangeA(@Qualifier("fanoutA")Queue fanoutA,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutA).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeB(@Qualifier("fanoutB")Queue fanoutB,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutB).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeC(@Qualifier("fanoutC")Queue fanoutC,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutC).to(fanoutExchange);
    }

}
