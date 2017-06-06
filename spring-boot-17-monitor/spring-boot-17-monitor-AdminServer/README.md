----
## [使用spring-boot-admin应用监控服务端](https://github.com/timebusker/spring-boot/tree/master/spring-boot-17-monitor/spring-boot-17-monitor-AdminServer/)

### 项目构建
+ 新建简单的 spring boot web项目

+ 在pom文件中添加spring-boot-starter-admin依赖
```xml
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server-ui</artifactId>
			<version>1.5.0</version>
		</dependency>
```

+ 在主类上添加 @EnableAdminServer 注解

+ 在application.properties文件中添加如下配置
```
server.port = 8080
spring.application.name=Spring Boot Admin Web
spring.boot.admin.url=http://localhost:${server.port}
spring.jackson.serialization.indent_output=true
endpoints.health.sensitive=false
```

----