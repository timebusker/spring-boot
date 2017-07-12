package cn.timebusker;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 简单定义消息队列
 */
@Configuration
public class DirectRabbitConfiguration {

	/**
	 * Direct:RabbitMQ默认的 Exchange(消息交换机):只需指定消息队列，消息直接通过默认的Direct Exchange消息交换机进行转发（点-点）
	 * 
	 * 1. 消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。
	 *  任何发送到Direct Exchange的消息都会被转发到RouteKey中指定的Queue。
	 * 
	 * 2.如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。
	 * 
	 * 3.所谓的路由关键字 RouteKey ，可以理解为 消息队列的名称 Queue
	 */
	
	public static final String DIRECT_ROUTING_KEY_TIMEBUSKER = "DIRECT.TIMEBUSKER";

	public static final String DIRECT_ROUTING_KEY_YUJIAOJIAO = "DIRECT.YUJIAOJIAO";

	public static final String DIRECT_ROUTING_KEY_OBJECT = "DIRECT.OBJECT";

	/**
	 * 必须设置消息队列
	 * 队列名称需要与路由关键字相匹配
	 */
	@Bean("timebuskerd")
	public Queue timebusker() {
		return new Queue(DIRECT_ROUTING_KEY_TIMEBUSKER);
	}

	@Bean("yujiaojiaod")
	public Queue yujiaojiao() {
		return new Queue(DIRECT_ROUTING_KEY_YUJIAOJIAO);
	}

	@Bean("objectd")
	public Queue object() {
		return new Queue(DIRECT_ROUTING_KEY_OBJECT);
	}
}