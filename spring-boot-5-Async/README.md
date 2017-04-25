----
## [spring-boot-3-logs spring boot 整合多个日志框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-3-logs/)

## 良好日志架构：SLF4J + Log4j/Log4j2/Logback
  
## 日志架构性能比较：日志性能比较：Log4j < Logback < Log4j2

### 项目阐述——spring boot 整合多个日志框架：Log4j、Log4j2、Logback
   ![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-3-logs/logging.png?raw=true)
   ![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-3-logs/SLF4J.png?raw=true)
 
 + #### [spring-boot 整合Log4j](https://github.com/timebusker/spring-boot/tree/master/spring-boot-3-logs/spring-boot-3-logs-Log4j/)
   * spring-boot 1.4.x.RELEASE 将不再支持
   * Log4j配置说明
   * Log4j配置信息
   * 配置多环境不同日志级别
	 
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
 - [混乱的 Java 日志体系](http://note.youdao.com/noteshare?id=8ee5d113de15c2bee1d36be76dddd717)
 - [为什么要使用SLF4J而不是Log4J](http://note.youdao.com/noteshare?id=f47db61d63b5254c76cd9404ef5c83e6)
 - [在 Web 应用中增加用户跟踪功能——学习在多线程环境下 Apache Log4j 的 NDC和MDC 开发](http://note.youdao.com/noteshare?id=9e15b0c68bedf37147965b213203de99)
 - [log4j+logback+slf4j+commons-logging的关系与调试](http://www.cnblogs.com/zhuawang/p/3999235.html)