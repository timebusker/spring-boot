package cn.timebusker.mq.sample;

import com.rabbitmq.client.*;

import java.io.IOException;

public class MessageConsumer {

	private final static String ROUTING_KEY = "SAMPLE_RABBITMQ";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("127.0.0.1");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(ROUTING_KEY, false, false, false, null);
		System.out.println("C [*] Waiting for messages. To exit press CTRL+C");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Consumer [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(ROUTING_KEY, true, consumer);
	}
}
