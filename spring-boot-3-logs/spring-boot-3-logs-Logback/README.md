----
## [spring-boot-3-logs-Logback 集成Logback日志框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-3-logs/spring-boot-3-logs-Logback/)

### 项目阐述
   ![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-3-logs/spring-boot-3-logs-Logback/Logback.png?raw=true)
 
 + #### SLF4J+Logback配置说明
   * [logback日志分开纪录](http://www.cnblogs.com/DeepLearing/p/5664941.html)</br>
   * [logback节点配置详解](http://www.cnblogs.com/DeepLearing/p/5663178.html)
   * [logback 中文手册.pdf](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-3-logs/spring-boot-3-logs-Logback/logback_cn.pdf?raw=true)
	
 + #### 配置多环境不同日志级别
	  ***logback.xml*配置讲解**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<!-- 文件输出格式 -->
	<property name="PATTERN" value="%-12(%d{yyyy-MM-dd HH:mm:ss.SSS}) |-%-5level [%thread] %c [%L] -| %msg%n" />
	<!-- test文件路径 -->
	<property name="TEST_FILE_PATH" value="c:/logs/test.log" />
	<!-- pro文件路径 -->
	<property name="PRO_FILE_PATH" value="c:/logs/prod.log" />

	<!-- 开发环境 -->
	<springProfile name="dev">
		<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
			<encoder>
				<pattern>%date [%thread] %-5level %logger{80} || %msg%n</pattern>
			</encoder>
		</appender>
		<logger name="cn.timebusker.util" level="debug" />
		<root level="info">
			<appender-ref ref="CONSOLE" />
		</root>
	</springProfile>

	<!-- 测试环境 -->
	<springProfile name="test">
		<!-- 每天产生一个文件 -->
		<appender name="TEST-FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
			<!-- 文件路径 -->
			<file>${TEST_FILE_PATH}</file>
			<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
				<!-- 文件名称 -->
				<fileNamePattern>${TEST_FILE_PATH}/info.%d{yyyy-MM-dd}.log</fileNamePattern>
				<!-- 文件最大保存历史数量 -->
				<MaxHistory>100</MaxHistory>
			</rollingPolicy>
			<layout class="ch.qos.logback.classic.PatternLayout">
				<pattern>${PATTERN}</pattern>
			</layout>
		</appender>
		<root level="info">
			<appender-ref ref="TEST-FILE" />
		</root>
	</springProfile>

	<!-- 生产环境 -->
	<springProfile name="prod">
		<appender name="PROD_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
			<file>${PRO_FILE_PATH}</file>
			<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
				<fileNamePattern>${PRO_FILE_PATH}/warn.%d{yyyy-MM-dd}.log</fileNamePattern>
				<MaxHistory>100</MaxHistory>
			</rollingPolicy>
			<layout class="ch.qos.logback.classic.PatternLayout">
				<pattern>${PATTERN}</pattern>
			</layout>
		</appender>
		<root level="warn">
			<appender-ref ref="PROD_FILE" />
		</root>
	</springProfile>
</configuration>
```
	
----   

```$xslt
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<!-- 开发环境 -->
	<springProfile name="dev">
		<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
			<encoder>
				<pattern>%date [%thread] %-5level %logger{80} || %msg%n</pattern>
			</encoder>
		</appender>
		<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
			<file>/logs/timebusker.log</file>
			<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
				<fileNamePattern>/logs/timebusker-%d{yyyy-MM-dd}.log</fileNamePattern>
				<maxHistory>30</maxHistory>
			</rollingPolicy>
			<encoder><!-- 必须指定，否则不会往文件输出内容 -->
				<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{5} - %msg%n</pattern>
			</encoder>
			<append>false</append>
			<prudent>false</prudent>
		</appender>
		<root level="info">
			<appender-ref ref="CONSOLE" />
			<appender-ref ref="FILE" />
		</root>
	</springProfile>
</configuration>
```