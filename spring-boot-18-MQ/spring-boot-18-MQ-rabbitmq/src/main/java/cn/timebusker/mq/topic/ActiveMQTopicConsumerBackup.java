package cn.timebusker.mq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQTopicConsumerBackup {

	/**
	 * @JmsListener进行监听
	 */
	@JmsListener(destination = "timebusker.topic")
    public void receiveQueue(String text) {
       System.out.println("Backup\t\t"+text);
    }
}
