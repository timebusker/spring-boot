package cn.timebusker;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfiguration {

	/**
	 * Topic交换机
	 * 
	 * 对Key进行模式匹配后进行投递，可以使用符号＃匹配一个或多个词，符号＊匹配正好 一 个词。
	 * 比如，abc.#匹配abc.def.ghi,abc.*只匹配abc.def；
	 * 
	 * 任何发送到Topic Exchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上
	 * 
	 * 1. 这种模式需要RouteKey，要提前绑定Exchange与Queue。
	 * 
	 * 2.如果Exchange没有发现能够与RouteKey匹配的Queue，则会抛弃此消息。
	 * 
	 * 3. 在进行绑定时，要提供一个该队列关心的主题，如“#.log.#”表示该队列关心所有涉及log的消息(一个RouteKey为”MQ.log.
	 * error”的消息会被转发到该队列)。
	 * 
	 * 4. “#”表示0个或若干个关键字，“*”表示一个关键字。如“log.*”能与“log.warn”匹配，无法与“log.warn.timeout”
	 * 匹配；但是“log.#”能与上述两者匹配。
	 * 
	 */

	public static final String TOPIC_ROUTING_KEY_TIMEBUSKER = "TOPIC.TIMEBUSKER";

	public static final String TOPIC_ROUTING_KEY_YUJIAOJIAO = "TOPIC.YUJIAOJIAO";

	public static final String TOPIC_ROUTING_KEY_MINE = "TOPIC.MINE";
	
	public static final String TOPIC_EXCHANGE = "TOPIC.EXCHANGE";

	@Bean("timebuskert")
	public Queue timebusker() {
		return new Queue(TOPIC_ROUTING_KEY_TIMEBUSKER);
	}

	@Bean("yujiaojiaot")
	public Queue yujiaojiao() {
		return new Queue(TOPIC_ROUTING_KEY_YUJIAOJIAO);
	}

	@Bean("minet")
	public Queue mine() {
		return new Queue(TOPIC_ROUTING_KEY_MINE);
	}

	@Bean("topicExchange")
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	
	/**
	 * 进入TOPIC_EXCHANGE消息交换机的消息，会根据route key与"TOPIC.*"进行匹配，路由到消息队列中
	 */
	@Bean
	Binding bindingExchange1(Queue yujiaojiaot, TopicExchange exchange) {
		return BindingBuilder.bind(yujiaojiaot).to(exchange).with("TOPIC.*");
	}
	
	@Bean
	Binding bindingExchange2(Queue timebuskert, TopicExchange exchange) {
		return BindingBuilder.bind(timebuskert).to(exchange).with("TOPIC.*");
	}
	
	@Bean
	Binding bindingExchange3(Queue minet, TopicExchange topicExchange) {
		return BindingBuilder.bind(minet).to(topicExchange).with("TOPIC.*");
	}
}