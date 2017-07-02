package cn.timebusker;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfiguration {

	@Bean("mineQueue")
	public Queue queue() {
		return new Queue("timebusker.queue");
	}

	@Bean("mineTopic")
	public Queue topic() {
		return new Queue("timebusker.topic");
	}
}