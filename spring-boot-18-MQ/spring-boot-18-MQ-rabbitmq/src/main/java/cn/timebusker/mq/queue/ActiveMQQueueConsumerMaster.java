package cn.timebusker.mq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQQueueConsumerMaster {

	/**
	 * @JmsListener进行监听
	 */
	@JmsListener(destination = "timebusker.queue")
    public void receiveQueue(String text) {
       System.out.println("Master\t\t"+text);
    }
}
