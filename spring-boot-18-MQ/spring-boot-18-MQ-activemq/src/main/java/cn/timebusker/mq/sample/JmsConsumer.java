package cn.timebusker.mq.sample;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息的消费者（接受者）
 *
 */
public class JmsConsumer {

	// 默认连接用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	// 默认连接密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	// 默认连接地址
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void run() {
		// 连接工厂
		ConnectionFactory connectionFactory;
		// 连接
		Connection connection = null;
		// 会话 接受或者发送消息的线程
		Session session;
		// 消息的目的地
		Destination destination;
		// 消息的消费者
		MessageConsumer messageConsumer;
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(JmsConsumer.USERNAME, JmsConsumer.PASSWORD, JmsConsumer.BROKEURL);
		try {
			// 通过连接工厂获取连接
			connection = connectionFactory.createConnection();
			// 启动连接
			connection.start();
			// 创建session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建一个连接SAMPLE_MQ的消息队列
			destination = session.createQueue("SAMPLE_MQ_QUEUE");
			// 创建消息消费者
			messageConsumer = session.createConsumer(destination);
			while (true) {
				TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
				if (textMessage != null) {
					System.out.println("QUEUE\t收到的消息:" + textMessage.getText());
				} else {
					break;
				}
			}

			// 创建一个连接SAMPLE_MQ的消息队列
			destination = session.createTopic("SAMPLE_MQ_TOPIC");
			// 创建消息消费者
			messageConsumer = session.createConsumer(destination);
			while (true) {
				TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
				if (textMessage != null) {
					System.out.println("TOPIC\t收到的消息:" + textMessage.getText());
				} else {
					break;
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
}