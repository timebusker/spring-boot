----
## [spring-boot-3-logs-Log4j spring boot 集成Log4j2日志框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-3-logs/spring-boot-3-logs-Log4j2/)

### 项目阐述
   ![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-3-logs/spring-boot-3-logs-Log4j2/Log4j2.png?raw=true)
 
 + #### SLF4J+Log4j2配置说明
    * [配置Log4j2 教程](http://www.cnblogs.com/leo-lsw/p/log4j2tutorial.html)
    * [Log4j2 使用总结](http://blog.csdn.net/jiangguilong2000/article/details/11397557)
	* [聊一聊log4j2配置文件log4j2.xml](http://www.cnblogs.com/hafiz/p/6170702.html)
 
 + #### Log4j2 配置信息<br/>
	* log4j 2.0与以往的1.x有一个明显的不同，其配置文件只能采用.xml, .json或者 .jsn。<br/>在默认情况下，系统选择configuration文件的优先级如下：（classpath为scr文件夹）
	  - classpath下名为 log4j-test.json 或者log4j-test.jsn文件
	  - classpath下名为 log4j2-test.xml
	  - classpath下名为 log4j.json 或者log4j.jsn文件
	  - classpath下名为 log4j2.xml
	  
 + #### 配置多环境不同日志级别
    * [Spring Boot支持多环境的配置以及属性文件中的参数引用](https://github.com/timebusker/spring-boot/tree/master/spring-boot-1-QuickStart/)<br/>
	
	* 对于不同环境的使用也不需要改变代码或打包文件，只需要通过执行命令中参加参数即可，
	  比如我想采用生产环境的级别，那么我可以这样运行应用：java -jar xxx.jar --spring.profiles.active=prod
	  
	  ***application-dev.properties*配置**
	  ```java
	  ## 配置多环境日志输出格式
	  logging.config=classpath:log4j2-dev.xml

	  ## 配置包下日志输出格式,可在日志配置文件中直接引用
	  logging.level.cn.timebusker=ERROR
	  ```
	  
	  ***log4j2-{profile}.xml*配置**
	  ```xml
	  <?xml version="1.0" encoding="UTF-8"?>
	  <!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
	  <!--Configuration后面的status，这个用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出 -->
	  <!--monitorInterval：Log4j能够自动检测修改配置 文件和重新配置本身，设置间隔秒数 -->
	  <configuration status="WARN" monitorInterval="30">
		  <!--先定义所有的appender -->
		  <appenders>
			  <!--这个输出控制台的配置 -->
			  <console name="Console" target="SYSTEM_OUT" follow="false">
				  <!--输出日志的格式 -->
				  <PatternLayout pattern="%date{yyyy-MM-dd HH:mm:ss.SSS} %level [%thread][%file:%line] - %msg%n" />
			  </console>
			  <!--文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，这个也挺有用的，适合临时测试用 -->
			  <File name="log" fileName="logs/test.log" append="false">
			  	<PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n" />
			  </File>
			  <!-- 这个会打印出所有的info及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档 -->
			  <RollingFile name="RollingFileInfo" fileName="logs/info.log" filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log">
		  		<!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch） -->
		  		<ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY" />
		  		<PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n" />
			  	<Policies>
			  		<!-- 基于时间的触发策略。该策略主要是完成周期性的log文件封存工作 interval，integer型，指定两次封存动作之间的时间间隔;modulate，boolean型，说明是否对封存时间进行调制 -->
			  		<TimeBasedTriggeringPolicy interval="4" modulate="true"/>
			  		<SizeBasedTriggeringPolicy size="100 MB" />
			  	</Policies>
			  </RollingFile>
		  	<RollingFile name="RollingFileWarn" fileName="logs/warn.log" filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/warn-%d{yyyy-MM-dd}-%i.log">
			    	<ThresholdFilter level="warn" onMatch="ACCEPT" onMismatch="DENY" />
			  	<PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n" />
		  		<Policies>
			  		<TimeBasedTriggeringPolicy />
			  		<SizeBasedTriggeringPolicy size="100 MB" />
			  	</Policies>
			  	<!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件，这里设置了20 -->
			  	<DefaultRolloverStrategy max="20" />
			  </RollingFile>
			  <RollingFile name="RollingFileError" fileName="logs/error.log" filePattern="${sys:user.home}/logs/$${date:yyyy-MM}/error-%d{yyyy-MM-dd}-%i.log">
			  	<ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY" />
			  	<PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n" />
			  	<Policies>
			  		<TimeBasedTriggeringPolicy />
			  		<SizeBasedTriggeringPolicy size="100 MB" />
			  	</Policies>
		  	</RollingFile>
		  </appenders>
	  	<!--然后定义logger，只有定义了logger并引入的appender，appender才会生效 -->
		  <loggers>
		  	<!--过滤掉spring和mybatis的一些无用的DEBUG信息 -->
		  	<logger name="org.springframework" level="INFO"></logger>
		  	<logger name="org.mybatis" level="INFO"></logger>
			  <root level="all">
			  	<appender-ref ref="Console" />
			  	<appender-ref ref="RollingFileInfo" />
			  	<appender-ref ref="RollingFileWarn" />
			  	<appender-ref ref="RollingFileError" />
			 </root>
			 <logger name="cn.timebusker.util" level="INFO">
			  	<appender-ref ref="RollingFileInfo" />
			 </logger>
		  </loggers>
	  </configuration>
	  ```
	
----