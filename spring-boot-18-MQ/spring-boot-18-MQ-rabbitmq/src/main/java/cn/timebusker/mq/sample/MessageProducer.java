package cn.timebusker.mq.sample;

import java.util.Date;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageProducer {

	private final static String ROUTING_KEY = "SAMPLE_RABBITMQ";

	@SuppressWarnings("unused")
	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		String pf = JSON.toJSONString(factory);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(ROUTING_KEY, false, false, false, null);
		for (;;) {
			Random random = new Random();
			Thread.sleep(random.nextInt(10000));
			String message = "Hello World,this time is " + new Date();
			channel.basicPublish("", ROUTING_KEY, null, message.getBytes("UTF-8"));
			System.out.println("Producer [x] Sent '" + message + "'");
		}
		// channel.close();
		// connection.close();
	}
}
