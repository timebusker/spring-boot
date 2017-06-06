### [spring-boot-4-Scheduled spring boot 使用定时任务](https://github.com/timebusker/spring-boot/tree/master/spring-boot-4-Scheduled/)

### 定时任务的几种实现方式
- Timer：Java自带的java.util.Timer类，这个类允许你调度一个java.util.TimerTask任务。
	           使用这种方式可以让你的程序按照某一个频度执行，但不能在指定时间运行。一般用的较少。

- Quartz：是一个功能完善的任务调度框架，它支持集群环境下的任务调度，需要将任务调度状态序列化到数据库。。

- Spring Task：Spring3.0以后自带的task，可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单许多，支持固定时间
	            (支持cron表达式)和固定时间间隔调度任务，支持线程池管理。
  
#### [关于spring整合 quartz 项目示例](https://github.com/timebusker/Synthesis/tree/master/Synthesis-spring-quartz/)

### 本案例主要讲解 Quartz 和 Spring Task 的使用和技巧

- ### Quartz 介绍及使用技巧
     * Quartz框架需要10多张表协同，配置繁多，它完全由Java编写的开源作业调度框架，为在Java应用程序中进行作业调度提供了
	   简单却强大的机制。Quartz允许开发人员根据时间间隔来调度作业。它实现了作业和触发器的多对多的关系，还能把多个作业与
	   不同的触发器关联，具有分布式和集群能力。

     * **Quartz专用词汇**
	    scheduler： 任务调度器
	    trigger：   触发器，用于定义任务调度时间规则
	    job：       任务，即被调度的任务
	    misfire：   错过的，指本来应该被执行但实际没有被执行的任务调度
		
	 * **Quartz任务调度基本实现原理**：
	  Quartz 任务调度的核心元素是scheduler,trigger和job。
	  其中trigger和job是任务调度的元数据，scheduler是实际执行调度的控制器。
	  
	  在 Quartz 中，trigger 是用于定义调度时间的元素，即按照什么时间规则去执行任务。Quartz 中主要提供了四种类型的 
	  trigger：SimpleTrigger，CronTirgger，DateIntervalTrigger，和 NthIncludedDayTrigger。
	  这四种 trigger 可以满足企业应用中的绝大部分需求。

	  在 Quartz 中，job 用于表示被调度的任务。主要有两种类型的 job：无状态的（stateless）和有状态的（stateful）。
	  对于同一个 trigger 来说，有状态的 job 不能被并行执行，只有上一次触发的任务被执行完之后，才能触发下一次执行。
	  Job 主要有两种属性：volatility 和 durability，其中 volatility 表示任务是否被持久化到数据库存储，而 durability 
	  表示在没有 trigger 关联的时候任务是否被保留。两者都是在值为 true 的时候任务被持久化或保留。一个 job 可以被多个
	  trigger 关联，但是一个 trigger 只能关联一个 job。

      在 Quartz 中， scheduler 由 scheduler 工厂创建：DirectSchedulerFactory 或者 StdSchedulerFactory。第二种工厂 
	  StdSchedulerFactory 使用较多，因为 DirectSchedulerFactory 使用起来不够方便，需要作许多详细的手工编码设置。 
	  Scheduler 主要有三种：RemoteMBeanScheduler， RemoteScheduler 和 StdScheduler。
	  本项目以最常用的 StdScheduler 为例讲解。
	  
* **Quartz 使用—— 引入核心依赖**:
```
<!-- 添加Quartz依赖 -->
<dependency>
	<groupId>org.quartz-scheduler</groupId>
	<artifactId>quartz</artifactId>
	<version>2.3.0</version>
</dependency>
```

- #### Spring Task 简单使用、动态修改cron表达式、动态添加修改删除定时任务

+ 关于 spring task的注解使用--@Scheduled详解      
  - @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
  - @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
  - @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
  - @Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则

+ 使用spring task不需要引用额外的jar包，spring-core中已经完成封装
![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-4-Scheduled/spring-core-tast.png?raw=true)
	   
+ 核心代码如下：
```
package cn.timebusker.springTask;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SpringTaskSample {

	/**
	 * 每10秒打印一次:时间取决于运行系统时间
	 */
	@Scheduled(cron = "0/10 * * * * *")
	public void task1() {
		System.out.println(getClass()+ "----task1" + new Date());
	}

	/**
	 * 每1分钟打印一次:时间取决于运行系统时间
	 */
	@Scheduled(cron = "0 0/1 * * * *")
	public void task2() {
		System.out.println(getClass()+ "----task2" + new Date());
	}
}
```  
+ spring task 在计算时间的时候，是根据当前服务器的系统时间进行计算,运行效果图如下图所示：
![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-4-Scheduled/spring-core-tast-res.png?raw=true)

+ Spring的Scheduled Task实现集群思路
实现任务互斥，通过声明 **全局锁** 作为互斥变量，获得锁的变量才有权执行任务。



	