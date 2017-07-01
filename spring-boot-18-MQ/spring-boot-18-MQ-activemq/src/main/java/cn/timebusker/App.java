package cn.timebusker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.timebusker.mq.sample.JmsConsumer;
import cn.timebusker.mq.sample.JmsProducer;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		// MQ简单模型
		JmsProducer.run();
		JmsConsumer.run();
		// spring boot实践
		SpringApplication.run(App.class, args);
	}
}