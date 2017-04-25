----
## [spring-boot-3-logs-Log4j spring boot 集成Log4j日志框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-3-logs/spring-boot-3-logs-Log4j/)

### 项目阐述
   ![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-3-logs/spring-boot-3-logs-Log4j/Log4j.png?raw=true)
 
 + #### SLF4J+Log4j配置说明
   * [配置Log4j](http://note.youdao.com/share/?id=01bc7e875753bdef14ed9609027ab0a7&type=note#/)</br>
   * [Log4j 详细配置](http://blog.csdn.net/azheng270/article/details/2173430/)</br>
 
 + #### Log4j配置信息<br/>
	Log4j建议只使用四个级别，优先级从高到低分别是 ERROR、WARN、INFO、DEBUG。
	定义了INFO级别，则应用程序中所有DEBUG级别的日志信息将不被打印出来，也是说大于等于的级别的日志才输出。
	![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-3-logs/spring-boot-3-logs-Log4j/log4j-root.png?raw=true)
	![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-3-logs/spring-boot-3-logs-Log4j/log4j-mine.png?raw=true)
	
 + #### 配置多环境不同日志级别
    * [Spring Boot支持多环境的配置以及属性文件中的参数引用](https://github.com/timebusker/spring-boot/tree/master/spring-boot-1-QuickStart/)<br/>
	
	* 对于不同环境的使用也不需要改变代码或打包文件，只需要通过执行命令中参加参数即可，
	  比如我想采用生产环境的级别，那么我可以这样运行应用：java -jar xxx.jar --spring.profiles.active=prod
	  
	  ***application-dev.properties*配置**
	  ```java
	  ## 配置多环境日志输出格式
	  logging.config=classpath:log4j-dev.properties

	  ## 配置包下日志输出格式,可在日志配置文件中直接引用
	  logging.level.cn.timebusker=ERROR
	  ```
	  
	  ***log4j-dev.properties*配置**
	  ```java
	  ## 自定义包下日志级别
	  ## log4j.category.cn.timebusker.util=debug,Errorfile,Infofile,Debugfile
	  ## 应用开发环境设定日志级别变量
	  log4j.category.cn.timebusker.util=${logging.level.cn.timebusker},Errorfile,Infofile,Debugfile
	  ```
	
----