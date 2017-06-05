----
## [spring-boot-17-monitor-actuator 使用spring-boot-actuator实现应用简单的监控](https://github.com/timebusker/spring-boot/tree/master/spring-boot-17-monitor/spring-boot-17-monitor-actuator/)

### spring-boot-actuator 使用
+ 新建简单的 spring boot web项目

+ 在pom文件中添加spring-boot-starter-actuator依赖
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
```

+ spring-boot-starter-actuator这个库让我们可以访问应用的很多信息，
包括：/env、/info、/metrics、/health等。

具体描述如下：

| URL          | 描述           | 敏感（Sensitive）  | 
| :------: | --------------- | :----: | 
| autoconfig  | 显示一个auto-configuration的报告，该报告展示所有auto-configuration候选者及它们被应用或未被应用的原因 | true | 
| beans       | 显示一个应用中所有Spring Beans的完整列表 | true | 
| configprops | 显示一个所有@ConfigurationProperties的整理列表 | true | 
| dump        | 执行一个线程转储 | true | 
| env         | 暴露来自Spring　ConfigurableEnvironment的属性 | true | 
| health      | 展示应用的健康信息（当使用一个未认证连接访问时显示一个简单的’status’，使用认证连接访问则显示全部信息详情）| false | 
| info        | 显示任意的应用信息 | false | 
| metrics     | 展示当前应用的’指标’信息|true| 
| mappings    | 显示一个所有@RequestMapping路径的整理列表 | true | 
| shutdown    | 允许应用以优雅的方式关闭（默认情况下不启用） | true | 
| trace       | 显示trace信息（默认为最新的一些HTTP请求） | true | 

+ 源码具体实现

![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-17-monitor/spring-boot-17-monitor-actuator/spring-boot-actuator.png?raw=true)

----