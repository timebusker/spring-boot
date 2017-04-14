package cn.timebusker;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

	/**
	 * @SpringBootApplication注解的类一定要放在自定义包下且属于自定义包的
	 * 
	 * @SpringBootApplication is a convenience annotation that adds all of the following:
	 * @SpringBootApplication是一个方便的注解，增加了所有的以下内容：
	 * 
	 * @Configuration tags the class as a source of bean definitions for the application context.
	 * @Configuration 标记一个类来作为bean定义的应用程序上下文的资源
	 * 
	 * @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
	 * @EnableAutoConfiguration告诉Spring Boot开始加载基于类路径下的配置信息、beans、各种属性配置。
	 * 
	 *  Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds
	 *  it automatically when it sees spring-webmvc on the classpath. This flags the application as a web
	 *  application and activates key behaviors such as setting up a DispatcherServlet
	 *  通常你会添加@EnableWebMvc为Spring MVC的应用程序，但是当Spring Boot在classpath下检索到spring-webmvc时，
	 *  spring boot会自动添加。这标志该应用程序作为Web应用程序，并激活关键行为，如设立的DispatcherServlet
	 * 
	 * @ComponentScan tells Spring to look for other components, configurations, and services in the the hello package, allowing it to find the HelloController.
	 * @ComponentScan 告诉Spring寻找其他组件，配置，以及业务层类,最前面才能加载到所有的类。
	 */
	
	public static void main(String[] args) {
		// devtools：是spring boot的一个热部署工具
		//设置 spring.devtools.restart.enabled 属性为false，可以关闭该特性.
		//System.setProperty("spring.devtools.restart.enabled","false");  
		
		// 启动Sprign Boot
		ApplicationContext ctx = SpringApplication.run(App.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
}
