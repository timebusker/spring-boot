package cn.timebusker.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.timebusker.utils.DateUtil;

/**
 * 面向切面编程 主要功能：日志记录、性能统计、安全控制、事务处理、异常处理等等
 */

public class ServiceHandlerAop {

	private final static Logger logger = LoggerFactory.getLogger(ServiceHandlerAop.class);
	
	/**
	 * AOP切面中的同步问题
	 */
	ThreadLocal<Long> startTime = new ThreadLocal<>();

	/**
	 * 切入点：匹配连接点的断言
	 */
	@Pointcut("execution(public * .*.*(..))")
	public void log() {
	}

	/**
	 * 前置通知：在连接点执行前的通知，但不能阻止连接点前的执行（除非它抛出一个异常）
	 */
	@Before("")
	public void beforeAdvice() {
		logger.info("ServiceHandlerAop.beforeAdvice...time:" + DateUtil.now());
	}

	/**
	 * 返回后通知：在连接点正常执行完后执行的通知
	 */
	public void afterReturningAdvice() {
		logger.info("ServiceHandlerAop.afterReturningAdvice...time:" + DateUtil.now());
	}

	/**
	 * 抛出异常后通知：在连接节点抛出异常退出时执行的通知
	 */
	public void afterThrowingAdvice() {
		logger.info("ServiceHandlerAop.afterThrowingAdvice...time:" + DateUtil.now());
	}

	/**
	 * 后置通知：当某连接节点退出的时候执行的通知（不论是正常返回还是异常退出）
	 */
	public void afterFinallyAdvice() {
		logger.info("ServiceHandlerAop.afterFinallyAdvice...time:" + DateUtil.now());
	}

	/**
	 * 环绕通知：包围一个连接点（join point）的通知
	 * 
	 * @param pjp
	 * @return
	 */
	public Object aroundAdvice(ProceedingJoinPoint pjp) {
		logger.info("ServiceHandlerAop.aroundAdvice...time:" + DateUtil.now());
		Object obj = null;
		try {
			System.out.println("MoocAspect around 1.");
			obj = pjp.proceed();
			System.out.println("MoocAspect around 2.");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}
}
