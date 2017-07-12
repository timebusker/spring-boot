package cn.timebusker.mq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cn.timebusker.DirectRabbitConfiguration;
import cn.timebusker.model.MessageEntity;

@Component
public class DirectRabbitMQConsumer {

	/**
	 * 监听方法传入的参数需要和消息生产者的一致
	 * 
	 * @param obj
	 */
	@RabbitHandler
	@RabbitListener(queues = DirectRabbitConfiguration.DIRECT_ROUTING_KEY_TIMEBUSKER)
	public void processt(Object obj) {
		System.out.println("T\t   Receiver  : " + obj.toString());
	}

	@RabbitHandler
	@RabbitListener(queues = DirectRabbitConfiguration.DIRECT_ROUTING_KEY_YUJIAOJIAO)
	public void processy(String context) {
		System.out.println("Y\t    Receiver  : " + context.toString());
	}

	@RabbitHandler
	@RabbitListener(queues = DirectRabbitConfiguration.DIRECT_ROUTING_KEY_OBJECT)
	public void processo(MessageEntity message) {
		System.out.println("O\t    Receiver  : " + message.toString());
	}
}
