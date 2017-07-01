package cn.timebusker.mq.topic;

import java.util.Date;

import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ActiveMQTopicProducer {

	/**
	 * 使用JmsMessagingTemplate  进行消息的操作
	 */
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Topic mineTopic;

	// 每3s执行1次
	@Scheduled(fixedDelay = 5000)
	public void send() {
		this.jmsMessagingTemplate.convertAndSend(this.mineTopic, "this is mineTopic activeMQ!\t" + new Date());
	}
}
