package cn.timebusker.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 利用@EnableAsync注解开启异步任务支持
 */
@Configuration
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {

	/**
	 * Set the ThreadPoolExecutor's core pool size.
	 */
	private static final int CORE_POOL_SIZE = 5;

	/**
	 * Set the ThreadPoolExecutor's maximum pool size.
	 */
	private static final int MAX_POOL_SIZE = 20;

	/**
	 * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
	 */
	private static final int QUEUE_CAPACITY = 10;

	/**
	 * 通过重写getAsyncExecutor方法，制定默认的任务执行由该方法产生
	 * 
	 * 配置类实现AsyncConfigurer接口并重写getAsyncExcutor方法，并返回一个ThreadPoolTaskExevutor
	 * 这样我们就获得了一个基于线程池的TaskExecutor
	 */
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(CORE_POOL_SIZE);// 线程池维护线程的最少数量
		taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);// 线程池维护线程的最大数量
		taskExecutor.setQueueCapacity(QUEUE_CAPACITY);// 线程池所使用的缓冲队列
		taskExecutor.initialize();
		return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}

	/**
	 * 自定义任务执行器：在定义了多个任务执行器的情况下，可以使用@Async("getMineAsync")来设定
	 * 
	 * @return
	 */
	@Bean
	public Executor getMineAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(CORE_POOL_SIZE - 4);
		executor.setMaxPoolSize(MAX_POOL_SIZE - 10);
		executor.setQueueCapacity(QUEUE_CAPACITY - 5);
		executor.setThreadNamePrefix("mineAsync-");
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}

}
