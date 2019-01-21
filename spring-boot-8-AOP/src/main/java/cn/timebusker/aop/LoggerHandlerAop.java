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
import org.springframework.core.annotation.Order;
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
    @Pointcut(value = "execution(* cn.timebusker.service.*.*(..))")
    public void log1() {
    }

    // 匹配任何包下的service包及子包下的任何方法执行(该模式只能匹配到一级的子包，多级子包不适用)
    @Pointcut(value = "execution(* *..service.*.*(..))")
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
     * 可以实现改变方法参数并执行方法
     * @param pjp
     * @return
     */
    @Around(value = "logaa1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) {
        logger.info("LoggerHandlerAop.aroundAdvice...time:" + DateUtil.now());
        Object obj = null;
        try {
            // 获取方法参数信息
            Object[] args = pjp.getArgs();

            System.out.println("MoocAspect around 1.");
            obj = pjp.proceed();
            System.out.println("MoocAspect around 2.");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;
    }
}
