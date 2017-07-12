//package cn.timebusker;
//
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.alibaba.fastjson.JSON;
//
//
//
//@Configuration
//public class RabbitConfiguration {
//
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		CachingConnectionFactory factory = new CachingConnectionFactory();
//		factory.setAddresses("127.0.0.1");
//		factory.setPort(5672);
//		factory.setUsername("guest");
//		factory.setPassword("guest");
//		factory.setVirtualHost("/");
//		// 必须要设置
//		factory.setPublisherConfirms(true);
//		System.out.println("\n\n\n\n" + JSON.toJSONString(factory));
//		return factory;
//	}
//	
//    @Bean
//    public AmqpAdmin amqpAdmin() {
//        return new RabbitAdmin(connectionFactory());
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        return new RabbitTemplate(connectionFactory());
//    }
//}