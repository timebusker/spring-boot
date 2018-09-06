## Spring Boot
## [我的blog地址：https://www.timebusker.top/，持续更新.....](https://timebusker.github.io/)

-----------------

<h2 align="center">:heart::heart::heart:如果觉得我的文章或者代码对您有帮助,可以请我喝杯咖啡哦:heart::heart::heart:</h2>
<div  align="center">    
  <img src="https://raw.githubusercontent.com/timebusker/timebusker.github.io/master/mine/wxpay.png?raw=true" width = "200" height = "200" alt="WXPAY" align=center />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://raw.githubusercontent.com/timebusker/timebusker.github.io/master/mine/alipay.png?raw=true" width = "200" height = "200" alt="ALIPAY" align=center />
</div>  
<h2 align="center">:smile::smile::smile:您的支持将鼓励我继续创作...谢谢!:smile::smile::smile:</h2>

-----------------


Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。
该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，  
Spring Boot致力于在蓬勃发展的快速应用开发领域（rapid application development）成为领导者。  

Spring Boot 项目旨在简化创建产品级的 Spring 应用和服务。你可通过它来选择不同的 Spring 平台。
可创建独立的 Java 应用和 Web 应用，同时提供了命令行工具来允许 'spring scripts'。  
下图显示 Spring Boot 在 Spring 生态中的位置：  
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
![image](https://github.com/timebusker/spring-boot/raw/master/static/logo.png?raw=true)

该项目主要的目的是：

+ 为 Spring 的开发提供了更快更广泛的快速上手

+ 使用默认方式实现快速开发

+ 提供大多数项目所需的非功能特性，诸如：嵌入式服务器、安全、心跳检查、外部配置等

### 模块列表
----
#### 第一个模块：[....................................入门程序](https://github.com/timebusker/spring-boot/tree/master/spring-boot-1-QuickStart/)

#### 第二个模块：[....................................完美支持RESTful API](https://github.com/timebusker/spring-boot/tree/master/spring-boot-2-RESTful/)

#### 第三个模块：[....................................整合多个日志框架：Log4j、Log4j2、Logback](https://github.com/timebusker/spring-boot/tree/master/spring-boot-3-logs/)

#### 第四个模块：[....................................使用定时任务](https://github.com/timebusker/spring-boot/tree/master/spring-boot-4-Scheduled/)

#### 第五个模块：[....................................使用@Async实现异步调用](https://github.com/timebusker/spring-boot/tree/master/spring-boot-5-Async/)

#### 第六个模块：[....................................统一异常捕获处理](https://github.com/timebusker/spring-boot/tree/master/spring-boot-6-GlobalException/)

#### 第七个模块：[....................................集成Ehcache缓存框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-7-EhCache/)

#### 第八个模块：[....................................集成AOP面向切面编程](https://github.com/timebusker/spring-boot/tree/master/spring-boot-8-AOP/)

#### 第九个模块：[....................................集成JavaMailSender](https://github.com/timebusker/spring-boot/tree/master/spring-boot-9-JavaMailSender/)

#### 第十个模块：[....................................使用spring-data持久层](https://github.com/timebusker/spring-boot/tree/master/spring-boot-10-SpringData/)

#### 第十一个模块：[................................集成SpringSecurity安全框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-11-SpringSecurity/)

#### 第十二个模块：[................................集成Swagger2构建强大的RESTful API](https://github.com/timebusker/spring-boot/tree/master/spring-boot-12-Swagger2/)

#### 第十三个模块：[................................集成MyBatis持久层框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-13-MyBatis/)

#### 第十四个模块：[................................使用spring JdbcTemplate持久层框架](https://github.com/timebusker/spring-boot/tree/spring-boot-14-JdbcTemplate/)

#### 第十五个模块：[................................集成Redis中间件项目实践](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/)

#### 第十六个模块：[................................使用Transcation保证数据一致性](https://github.com/timebusker/spring-boot/tree/master/spring-boot-16-Transcation/)

#### 第十七个模块：[................................应用的健康监控](https://github.com/timebusker/spring-boot/tree/master/spring-boot-17-monitor/)

#### 第十八个模块：[................................消息队列实践](https://github.com/timebusker/spring-boot/tree/master/spring-boot-18-MQ/)

#### 第十九个模块：[................................Spring Boot自定义Starter](https://github.com/timebusker/spring-boot/tree/master/spring-boot-19-Definition-Starter/)

#### 第二十个模块：[................................Spring Boot结合Freemaker使用](https://github.com/timebusker/spring-boot/tree/master/spring-boot-20-Freemarker/)

#### 第二十一个模块：[................................Spring Boot多数据源配置使用原理](https://github.com/timebusker/spring-boot/tree/master/spring-boot-21-MultiDataSource/)

#### 第二十二个模块：[................................Spring Boot基于Vue快速搭建Web管理系统](https://github.com/timebusker/spring-boot/tree/master/spring-boot-22-FarstPlus/)

#### 第二十三个模块：[................................Spring Boot多种方式连接MongoDB操作](https://github.com/timebusker/spring-boot/tree/master/spring-boot-23-MongoDB/)

----  

### 关于Spring Boot模板引擎   
- 虽然现在很多开发，都采用了前后端完全分离的模式，即后端只提供数据接口，前端通过AJAX请求获取数据，完全不需要用的模板引擎。
这种方式的优点在于前后端完全分离，并且随着近几年前端工程化工具和MVC框架的完善，使得这种模式的维护成本相对来说也更加低一点。
但是这种模式不利于SEO，并且在性能上也会稍微差一点，还有一些场景，使用模板引擎会更方便，比如说邮件模板。   

- 总体来讲，Spring boot对thymeleaf和Freemaker支持比较友好，配置相对也简单一点，Spring Boot不建议使用JSP，因为在使用嵌入式servlet容器时，有一些使用限制。
在实际的开发中，大多也以这两种模板引擎为主，很少有用jsp的，jsp现在可能更多是在实验或者学习阶段使用。   

![image](https://github.com/timebusker/spring-boot/raw/master/static/111111.png?raw=true)
[性能对比测试](https://github.com/jreijn/spring-comparing-template-engines)
