package cn.timebusker.mq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.timebusker.TopicRabbitConfiguration;

@Component
public class TopicRabbitMQProducer {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Scheduled(fixedRate = 50000)
	public void sendt() {
		String context = TopicRabbitConfiguration.TOPIC_ROUTING_KEY_TIMEBUSKER + "\t";
		// 指定 消息交换机和路由关键字
		this.rabbitTemplate.convertAndSend(TopicRabbitConfiguration.TOPIC_EXCHANGE, TopicRabbitConfiguration.TOPIC_ROUTING_KEY_TIMEBUSKER, context);
	}

	@Scheduled(fixedRate = 50000)
	public void sendy() {
		String context = TopicRabbitConfiguration.TOPIC_ROUTING_KEY_MINE + "\t";
		// 指定 消息交换机和路由关键字
		this.rabbitTemplate.convertAndSend(TopicRabbitConfiguration.TOPIC_EXCHANGE, TopicRabbitConfiguration.TOPIC_ROUTING_KEY_MINE, context);
	}

	@Scheduled(fixedRate = 50000)
	public void sendo() {
		String context = TopicRabbitConfiguration.TOPIC_ROUTING_KEY_YUJIAOJIAO + "\t";
		// 指定 消息交换机和路由关键字
		this.rabbitTemplate.convertAndSend(TopicRabbitConfiguration.TOPIC_EXCHANGE, TopicRabbitConfiguration.TOPIC_ROUTING_KEY_YUJIAOJIAO, context);
	}
}