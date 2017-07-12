package cn.timebusker;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfiguration {
	
	/**
	 * 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上,不需要路由关键字匹配
	 *  
	 * 1.这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定
	 * 
	 * 2.这种模式不需要RouteKey
	 * 
	 * 3.如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃。
	 */
	
	public static final String FANOUT_ROUTING_KEY_TIMEBUSKER = "FANOUT.TIMEBUSKER";

	public static final String FANOUT_ROUTING_KEY_YUJIAOJIAO = "FANOUT.YUJIAOJIAO";

	public static final String FANOUT_ROUTING_KEY_MINE = "FANOUT.MINE";
	
	public static final String FANOUT_EXCHANGE = "FANOUT.EXCHANGE";
		
	@Bean("timebuskerf")
	public Queue timebusker() {
		return new Queue(FANOUT_ROUTING_KEY_TIMEBUSKER);
	}

	@Bean("yujiaojiaof")
	public Queue yujiaojiao() {
		return new Queue(FANOUT_ROUTING_KEY_YUJIAOJIAO);
	}

	@Bean("minef")
	public Queue mine() {
		return new Queue(FANOUT_ROUTING_KEY_MINE);
	}

	@Bean("fanoutExchange")
	FanoutExchange exchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	
    @Bean
    Binding bindingExchangeA(Queue timebuskerf,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(timebuskerf).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue yujiaojiaof, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(yujiaojiaof).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue minef, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(minef).to(fanoutExchange);
    }
	
}