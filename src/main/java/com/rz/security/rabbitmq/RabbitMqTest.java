package com.rz.security.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {


	@Autowired
	private RabbitSender sender;

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Test
	public void createExchange() {
		amqpAdmin.declareExchange(new DirectExchange("amqp.exchange"));
		System.out.println("创建一个direct交换机完成");
	}

	@Test
	public void createQueue() {
		amqpAdmin.declareBinding(
				new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqp.exchange", "amqp.mytest", null));
		System.out.println("绑定成功");
	}

	@Test
	public void delete() {
		amqpAdmin.deleteQueue("fanout-2");
		System.out.println("删除队列成功！");
	}

	@Test
	public void hello() throws Exception {
		sender.workSend("work queue test");
	}

	@Test
	public void topic() throws Exception {
		sender.topicSend("topic测试：相视而笑，莫逆于心！");
	}

	@Test
	public void fanout() throws Exception {
		sender.fanout("广播：狼来了。。");
	}


}
