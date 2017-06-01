----
## [spring-boot-8-AOP spring boot 使用AOP面向切面编程](https://github.com/timebusker/spring-boot/tree/master/spring-boot-8-AOP/)

- #### 关于AOP
       AOP（Aspect-OrientedProgramming，面向方面编程），可以说是OOP（Object-Oriented Programing，面向对象编程）的补充和完善。
OOP引入封装、继承和多态性等概念来建立一种对象层次结构，用以模拟公共行为的一个集合。当我们需要为分散的对象引入公共行为的时候，
OOP则显得无能为力。OOP允许你定义从上到下的关系，但并不适合定义从左到右的关系。例如日志功能，日志代码往往水平地散布在所有对象
层次中，而与它所散布到的对象的核心功能毫无关系。对于其他类型的代码，如安全性、异常处理和透明的持续性也是如此。这种散布在各处
的无关的代码被称为横切（cross-cutting）代码，在OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。

       AOP技术则恰恰相反，它利用一种称为“横切”的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到
一个可重用模块，并将其名为“Aspect”，即方面。所谓“方面”，简单地说，就是将那些与业务无关，却为业务模块所共同调用的逻辑或责任封
装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可操作性和可维护性。AOP代表的是一个横向的关系，如果说“对象”
是一个空心的圆柱体，其中封装的是对象的属性和行为；那么面向方面编程的方法，就仿佛一把利刃，将这些空心圆柱体剖开，以获得其内部
的消息。而剖开的切面，也就是所谓的“方面”了。然后它又以巧夺天功的妙手将这些剖开的切面复原，不留痕迹。
 
       使用“横切”技术，AOP把软件系统分为两个部分：核心关注点和横切关注点。业务处理的主要流程是核心关注点，与之关系不大的部分
是横切关注点。横切关注点的一个特点是，他们经常发生在核心关注点的多处，而各处都基本相似。比如权限认证、日志、事务处理。Aop 的
作用在于分离系统中的各种关注点，将核心关注点和横切关注点分离开来。正如Avanade公司的高级方案构架师Adam Magee所说，AOP的核心思
想就是“将应用程序中的商业逻辑同对其提供支持的通用服务进行分离。”
 
       实现AOP的技术，主要分为两大类：一是采用动态代理技术，利用截取消息的方式，对该消息进行装饰，以取代原有对象行为的执行；
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

 + #### [spring-boot 整合Log4j2](https://github.com/timebusker/spring-boot/tree/master/spring-boot-3-logs/spring-boot-3-logs-Log4j2/)
   * Log4j2配置说明
   * Log4j2配置信息
   * 配置多环境不同日志级别
   
 + #### [spring-boot 整合Logback](https://github.com/timebusker/spring-boot/tree/master/spring-boot-3-logs/spring-boot-3-logs-Logback/)
   * spring boot 1.4.X默认日志框架为 SLF4J+Logback
   * Logback 配置信息
   * 配置多环境不同日志级别
		 
----

### 相关文章
 - [Spring AOP 实现原理](http://blog.csdn.net/moreevan/article/details/11977115/)
 - [为什么要使用SLF4J而不是Log4J](http://note.youdao.com/noteshare?id=f47db61d63b5254c76cd9404ef5c83e6)
 - [在 Web 应用中增加用户跟踪功能——学习在多线程环境下 Apache Log4j 的 NDC和MDC 开发](http://note.youdao.com/noteshare?id=9e15b0c68bedf37147965b213203de99)
 - [log4j+logback+slf4j+commons-logging的关系与调试](http://www.cnblogs.com/zhuawang/p/3999235.html)