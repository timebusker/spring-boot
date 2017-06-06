----
## [使用spring-boot-admin应用监控客户端](https://github.com/timebusker/spring-boot/tree/master/spring-boot-17-monitor/spring-boot-17-monitor-AdminClient/)

### 项目构建
+ 新建简单的 spring boot web项目

+ 在pom文件中添加spring-boot-starter-admin依赖
```xml
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-starter-client</artifactId>
			<version>1.5.0</version>
		</dependency>
```


+ 在application.properties文件中添加如下配置
```
# endpoints
endpoints.beans.id=springbeans
endpoints.beans.sensitive=false
# 需要使用POST发起请求
endpoints.shutdown.enabled=true
endpoints.health.sensitive=false
endpoints.health.time-to-live=1000

spring.application.name=@project.artifactId@
# 服务注册链接与server端首页 地址一致
spring.boot.admin.url=http://localhost:8080/admin
```
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



----