package cn.timebusker.mq.sample;

import java.util.Date;
import java.util.Random;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageProducer {

	private final static String QUEUE_NAME = "SAMPLE-RABBITMQ";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		for(;;){
			Random random = new Random();
			Thread.sleep(random.nextInt(10000));
			String message = "Hello World,this time is " + new Date();
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.out.println("Producer [x] Sent '" + message + "'");
		}
//		channel.close();
//		connection.close();
	}
}
