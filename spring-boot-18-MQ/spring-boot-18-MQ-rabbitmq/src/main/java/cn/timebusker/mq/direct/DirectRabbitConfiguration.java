package cn.timebusker.mq.direct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfiguration {

	/**
	 * Direct:RabbitMQ默认的 Exchange(消息交换机)
	 * 
	 * 1. 消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
	 * 任何发送到Direct Exchange的消息都会被转发到RouteKey中指定的Queue。
	 * 
	 * 2.如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。
	 */
	@Bean("mineQueue")
	public Queue queue() {
		return new Queue("timebusker.queue");
	}

	@Bean("mineTopic")
	public Queue topic() {
		return new Queue("timebusker.topic");
	}
}