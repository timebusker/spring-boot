----
## [spring-boot-8-AOP spring boot 使用AOP面向切面编程](https://github.com/timebusker/spring-boot/tree/master/spring-boot-8-AOP/)

![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-8-AOP/aop-mine.png?raw=true)

- #### 关于AOP
    + AOP（Aspect-OrientedProgramming，面向方面编程），可以说是OOP（Object-Oriented Programing，面向对象编程）的补充和完善。
OOP引入封装、继承和多态性等概念来建立一种对象层次结构，用以模拟公共行为的一个集合。当我们需要为分散的对象引入公共行为的时候，
OOP则显得无能为力。OOP允许你定义从上到下的关系，但并不适合定义从左到右的关系。例如日志功能，日志代码往往水平地散布在所有对象
层次中，而与它所散布到的对象的核心功能毫无关系。对于其他类型的代码，如安全性、异常处理和透明的持续性也是如此。这种散布在各处
的无关的代码被称为横切（cross-cutting）代码，在OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。

    + AOP技术则恰恰相反，它利用一种称为“横切”的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到
一个可重用模块，并将其名为“Aspect”，即方面。所谓“方面”，简单地说，就是将那些与业务无关，却为业务模块所共同调用的逻辑或责任封
装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可操作性和可维护性。AOP代表的是一个横向的关系，如果说“对象”
是一个空心的圆柱体，其中封装的是对象的属性和行为；那么面向方面编程的方法，就仿佛一把利刃，将这些空心圆柱体剖开，以获得其内部
的消息。而剖开的切面，也就是所谓的“方面”了。然后它又以巧夺天功的妙手将这些剖开的切面复原，不留痕迹。
 
    + 使用“横切”技术，AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分
是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多处，而各处都基本相似。比如权限认证、日志、事务处理。Aop 的
作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。正如Avanade公司的高级方案构架师Adam Magee所说，AOP的核心思
想就是“将应用程序中的商业逻辑同对其提供支持的通用服务进行分离。”
 
    + 实现AOP的技术，主要分为两大类：一是采用动态代理技术，利用截取消息的方式，对该消息进行装饰，以取代原有对象行为的执行；
二是采用静态织入的方式，引入特定的语法创建“方面”，从而使得编译器可以在编译期间织入有关“方面”的代码。
 
 + #### AOP使用场景
   * Authentication 权限
   * Caching 缓存
   * Context passing 内容传递
   * Error handling 错误处理
   * Lazy loading　懒加载
   * Debugging　　调试
   * logging, tracing, profiling and monitoring　记录跟踪　优化　校准
   * Performance optimization　性能优化
   * Persistence　　持久化
   * Resource pooling　资源池
   * Synchronization　同步
   * Transactions 事务

 + #### Spring AOP实战使用
```
package cn.timebusker.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.timebusker.utils.DateUtil;

/**
 * 面向切面编程 主要功能：日志记录、性能统计、安全控制、事务处理、异常处理等等
 */
@Component
@Aspect
//使用 order对多个切面进行排序,参数越小，越在前
@Order(1)
public class LoggerHandlerAop {

	private final static Logger logger = LoggerFactory.getLogger(LoggerHandlerAop.class);

	/**
	 * AOP切面中的同步问题：用于监控业务处理性能
	 */
	ThreadLocal<Long> startTime = new ThreadLocal<>();

// ==========================================================匹配切点表达式========================================================================================
/**
 * 一、execution(方法表达式)
 * 1、匹配方法时，只能匹配到实现类，匹配到接口类不能成功
 * 2、匹配方法执行
 */
	// 匹配cn.timebusker.service包及子包下的任何方法执行
	@Pointcut(value="execution(* cn.timebusker.service.*.*(..))")
	public void log1() {
	}
	
	// 匹配任何包下的service包及子包下的任何方法执行(该模式只能匹配到一级的子包，多级子包不适用)
	@Pointcut(value="execution(* *..service.*.*(..))")
	public void log2() {
	}
	
	// 匹配任何包下的service包及子包下的任何方法执行(该模式能匹配到任何多级的子包下的方法执行)
	@Pointcut(value = "execution(* *..service..*(..))")
	public void log3() {
	}
	
	// 匹配返回值类型为java.lang.String的任何包下的service包及子包下的方法执行
	@Pointcut(value = "execution(java.lang.String *..service..*(..))")
	public void log4() {
	}
	
	// 匹配返回值类型为int的任何包下的service包及子包下的方法执行
	@Pointcut(value = "execution(int *..service..*(..))")
	public void log5() {
	}
	
	// 匹配任何返回值类型的cn.timebusker包及任何子包下的以add开头的参数为Strign类型的方法执行
	@Pointcut(value = "execution(* cn.timebusker..add*(String))")
	public void log6() {
	}
	
	// 匹配 OR、AND
	@Pointcut(value = "execution(* cn.timebusker.service.*.add*(int))")
	public void log7() {
	}
	
	// 匹配 OR、AND、
	@Pointcut(value = "execution(* cn.timebusker.service.*.add*(int)) OR execution(* cn.timebusker..add*(String))")
	public void log8() {
	}
	
/**
 * 二、within(类型表达式)
 * 1、匹配类型时，只能匹配到实现类，匹配到接口类不能成功
 * 2、匹配指定类型内的方法执行；
 */
	// 匹配指定类型内的方法执行--只能匹配类型
	@Pointcut(value = "within(cn.timebusker.service.order.Impl.OrderInfoServiceImpl)")
	public void logw1() {
	}
	
	// 匹配指定类型内的方法执行(包下所有的类)
	@Pointcut(value = "within(cn.timebusker.service.order.Impl.*)")
	public void logw2() {
	}

/**
 * 三、this(类型全限定名)
 * 1、可以直接匹配接口类型完成  类型全名限定匹配
 * 2、注意是AOP代理对象的类型匹配，这样就可能包括引入接口方法也可以匹配；注意this中使用的表达式必须是类型全限定名，不支持通配符
 */
	// 匹配指定类型内的方法执行(包下所有的类)
	@Pointcut(value = "this(cn.timebusker.service.order.OrderInfoService)")
	public void logt1() {
	}
	
/**
* 四、target(类型全限定名)--匹配当前目标对象类型的执行方法
* 1、可以直接匹配接口类型完成  类型全名限定匹配
* 2、注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；注意target中使用的表达式必须是类型全限定名，不支持通配符
*/
		// 匹配指定类型内的方法执行(包下所有的类)
		@Pointcut(value = "target(cn.timebusker.service.order.OrderInfoService)")
		public void logt2() {
		}
		
/**
* 五、args(参数类型列表)--匹配当前执行的方法传入的参数为指定类型的执行方法
* 1、注意是匹配传入的参数类型，不是匹配方法签名的参数类型；参数类型列表中的参数必须是类型全限定名，通配符不支持；
* 2、args属于动态切入点，是在运行时动态匹配的，这种切入点开销非常大，非特殊情况最好不要使用；
* 3、此处不作示例
*/
		
/**
* 六、@within(注解类型)--匹配所以持有指定注解类型内的方法；注解类型也必须是全限定类型名；
* 1、注解类型也必须是全限定类型名;
*/
		// 匹配被org.springframework.stereotype.Service这个注解标注的类----注解标注在接口上不起作用
		@Pointcut(value = "@within(org.springframework.stereotype.Service)")
		public void logaw1() {
		}
		
		// 匹配 自定义注解标注的类----注解标注在接口上不起作用
		@Pointcut(value = "@within(cn.timebusker.annotation.timebuskerBean)")
		public void logaw2() {
		}
		
/**
* 七、@target(注解类型)--匹配当前目标对象类型的执行方法
* 1、目标对象持有指定的注解；
* 2、注解类型也必须是全限定类型名；
* 3、此处不作示例
*/		

/**
* 八、@annotation(注解类型)--匹配当前执行方法持有指定注解的方法
* 1、注解类型也必须是全限定类型名；
*/	
		// 匹配 自定义注解标注的类----注解标注在接口的方法上不起作用
		@Pointcut(value = "@annotation(cn.timebusker.annotation.timebuskerMethod)")
		public void logaa1() {
		}

// ==========================================================匹配切点表达式========================================================================================

	/**
	 * 前置通知：在连接点执行前的通知，但不能阻止连接点前的执行（除非它抛出一个异常）
	 */

// 引用多个切入点
//	@Before("log6() OR log7()")
// 引用单个切入点
	@Before("logaa1()")
	public void beforeAdvice(JoinPoint point) {
		logger.info("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
		logger.info("LoggerHandlerAop.beforeAdvice...time:" + DateUtil.now());
		Signature signature = point.getSignature();
		logger.info("所属类名称：" + signature.getDeclaringTypeName() + "\n代理类：" + signature.getClass() + "\n方法名称：" + signature.getName() + "\n所属类：" + signature.getDeclaringType());
		Object[] args = point.getArgs();
		logger.info("参数是：" + JSON.toJSONString(args));
		logger.info("被织入的对象是：" + point.getTarget());
		logger.info("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
	}

	/**
	 * 返回后通知：在连接点正常执行完后执行的通知
	 */
	@AfterReturning("logaa1()")
	public void afterReturningAdvice() {
		logger.info("LoggerHandlerAop.afterReturningAdvice...time:" + DateUtil.now());
	}

	/**
	 * 抛出异常后通知：在连接节点抛出异常退出时执行的通知
	 */
	@AfterThrowing("logaa1()")
	public void afterThrowingAdvice() {
		logger.info("LoggerHandlerAop.afterThrowingAdvice...time:" + DateUtil.now());
	}

	/**
	 * 后置通知：当某连接节点退出的时候执行的通知（不论是正常返回还是异常退出）
	 */
	@After("logaa1()")
	public void afterFinallyAdvice() {
		logger.info("LoggerHandlerAop.afterFinallyAdvice...time:" + DateUtil.now());
	}

	/**
	 * 环绕通知：包围一个连接点（join point）的通知
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("logaa1()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) {
		logger.info("LoggerHandlerAop.aroundAdvice...time:" + DateUtil.now());
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

```
		 
----

### 相关文章
 - [Spring AOP 实现原理](http://blog.csdn.net/moreevan/article/details/11977115/)
 - [Spring 之AOP AspectJ切入点语法详解](http://jinnianshilongnian.iteye.com/blog/1415606)