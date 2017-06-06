----
### [实现应用的健康监控](https://github.com/timebusker/spring-boot/tree/master/spring-boot-17-monitor/)

#### [使用spring-boot-actuator实现应用简单的监控](https://github.com/timebusker/spring-boot/tree/master/spring-boot-17-monitor/spring-boot-17-monitor-actuator/)
spring-boot-actuator模块提供了一个监控和管理生产环境的模块，可以使用http、jmx、ssh、telnet等拉管理和监控应用。
审计（Auditing）、 健康（health）、数据采集（metrics gathering）会自动加入到应用里面。

#### [Spring-Boot-Admin 监控服务端](https://github.com/timebusker/spring-boot/tree/master/spring-boot-17-monitor/spring-boot-17-monitor-AdminServer/)
Spring Boot Admin就是将Spring Boot Actuator中提供的endpoint信息可视化表示，在BookPub应用（被监控）的这一端，只需要进行一点配置即可。

+ spring-boot-admin-starter-client，作为客户端，用于与Spring Boot Admin Web的服务器沟通；

+ spring.boot.admin.url=http:localhost:8090用于将当前应用注册到Spring Boot Admin；

+ 如果希望通过Web控制系统的日志级别，则需要在应用中添加Jolokia JMX库（org.jolokia:jolokia-core）；
  spring-boot-admin-starter-client 会自动依赖 jolokia-core，jolokia是用于 JMX-bean 管理的。
	
**POM.xml**
```
<dependency>
    <groupId>org.jolokia</groupId>
    <artifactId>jolokia-core</artifactId>
    <version>1.3.6</version>
</dependency>
```
**logback.xml**
```
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>
    <jmxConfigurator/>
</configuration>
```


### 相关文章

[Spring Boot应用的健康监控](http://www.jianshu.com/p/734519d3c383)
[Spring Boot Admin的使用](http://www.jianshu.com/p/e20a5f42a395)
[Spring Boot Admin 的使用](http://blog.csdn.net/kinginblue/article/details/52132113)

----