package cn.timebusker.mq.sample;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息的生产者（发送者）
 *
 */
public class JmsProducer {

	// 默认连接用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	// 默认连接密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	// 默认连接地址
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	// 发送的消息数量
	private static final int SENDNUM = 10;

	public static void run() {
		// 连接工厂
		ConnectionFactory connectionFactory;
		// 连接
		Connection connection = null;
		// 会话接受或者发送消息的线程
		Session session;
		// 消息的目的地
		Destination destination;
		// 消息生产者
		MessageProducer messageProducer;
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(JmsProducer.USERNAME, JmsProducer.PASSWORD, JmsProducer.BROKEURL);
		try {
			// 通过连接工厂获取连接
			connection = connectionFactory.createConnection();
			// 启动连接
			connection.start();
			// 创建session
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// 创建一个名称为SAMPLE_MQ_QUEUE的消息队列
			destination = session.createQueue("SAMPLE_MQ_QUEUE");
			// 创建消息生产者
			messageProducer = session.createProducer(destination);
			// 发送消息
			sendMessage(session, messageProducer, "QUEUE");

			// 创建一个名称为SAMPLE_MQ_TOPIC的消息主题
			destination = session.createTopic("SAMPLE_MQ_TOPIC");
			// 创建消息生产者
			messageProducer = session.createProducer(destination);
			// 发送消息
			sendMessage(session, messageProducer, "TOPIC");
			session.commit();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 发送消息
	 * 
	 * @param session
	 * @param messageProducer
	 *            消息生产者
	 * @throws Exception
	 */
	public static void sendMessage(Session session, MessageProducer messageProducer, String type) throws Exception {
		for (int i = 0; i < JmsProducer.SENDNUM; i++) {
			Date date = new Date();
			// 创建一条文本消息
			TextMessage message = session.createTextMessage("ActiveMQ 发送消息时间：" + date);
			System.out.println("\t" + type + "\t发送消息：Activemq 发送消息时间：" + date);
			// 通过消息生产者发出消息
			messageProducer.send(message);
		}
	}
}
