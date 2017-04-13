  $$\     $$\                         $$\                           $$\                           
  $$ |    \__|                        $$ |                          $$ |                          
$$$$$$\   $$\ $$$$$$\$$$$\   $$$$$$\  $$$$$$$\  $$\   $$\  $$$$$$$\ $$ |  $$\  $$$$$$\   $$$$$$\  
\_$$  _|  $$ |$$  _$$  _$$\ $$  __$$\ $$  __$$\ $$ |  $$ |$$  _____|$$ | $$  |$$  __$$\ $$  __$$\ 
  $$ |    $$ |$$ / $$ / $$ |$$$$$$$$ |$$ |  $$ |$$ |  $$ |\$$$$$$\  $$$$$$  / $$$$$$$$ |$$ |  \__|
  $$ |$$\ $$ |$$ | $$ | $$ |$$   ____|$$ |  $$ |$$ |  $$ | \____$$\ $$  _$$<  $$   ____|$$ |      
  \$$$$  |$$ |$$ | $$ | $$ |\$$$$$$$\ $$$$$$$  |\$$$$$$  |$$$$$$$  |$$ | \$$\ \$$$$$$$\ $$ |      
   \____/ \__|\__| \__| \__| \_______|\_______/  \______/ \_______/ \__|  \__| \_______|\__|      
                                                                                                  
  __                     _                         _    _     _   _     _       __  _ 
 (_  ._  ._ o ._   _ __ |_)  _   _ _|_   \  / /|  |_     )   |_) |_ |  |_  /\  (_  |_ 
 __) |_) |  | | | (_|   |_) (_) (_) |_    \/   | o _) o /_ o | \ |_ |_ |_ /--\ __) |_ 
     |             _|                                                                 

开发运行环境：eclipse、JDK1.8				   
					   
第一个项目：spring-boot-1-QuickStart spring boot的入门程序
            
			快速入门                    快速开发一个WEB项目
			属性配置文件详解            spring boot在开发、测试、生产环境的配置切换，自定义属性的配置
			构建RESTful API与单元测试   spring boot对RESTful API的支持
			开发Web应用                 spring boot对Velocity、Thymeleaf、FreeMarker模板引擎的支持
			
第二个项目：SpringBoot-Swagger2 
            
			Swagger2可以轻松的整合到Spring Boot中，并与Spring MVC程序配合组织出强大RESTful API文档
			既可以减少我们创建文档的工作量，同时说明内容又整合入实现代码中，让维护文档和修改代码整合为一体，
			可以让我们在修改代码逻辑的同时方便的修改文档说明。另外Swagger2也提供了强大的页面测试功能来调试每个RESTful API。
            		
					
第三个项目：SpringBoot-GlobalException  

            做Web应用的时候，请求处理过程中发生错误是非常常见的情况。Spring Boot提供了一个默认的映射：/error，
			当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。
			
            虽然Spring Boot中实现了默认的error映射，但是在实际应用中，上面你的错误页面对用户来说并不够友好，
			我们通常需要去实现我们自己的异常提示。  

			
第四个项目：SpringBoot-JdbcTemplate  spring boot整合JDBC以及相应的多数据源的配置
            
			在Spring Boot下访问数据库的配置依然秉承了框架的初衷：简单。我们只需要在pom.xml中加入数据库依赖，
			再到application.properties中配置连接信息，不需要像Spring应用中创建JdbcTemplate的Bean，
			就可以直接在自己的对象中注入使用。
			
			对于单个数据源，Spring Boot能够自动在application.properties获取数据库连接信息，生成数据源并注入到
			JdbcTemplate模板中，只需把JdbcTemplate注入DAO/service 层即可实现对数据库的操作
			
			对于多个数据源，需要自己配置多个数据库信息并生成对应的数据源DataSource，
			再将DataSource注入到相应的JdbcTemplate模板中，使用时根据需要注入JdbcTemplate模板
			
			
第五个项目：SpringBoot-SpringData  Spring-data-jpa的整合以及多数据源的配置
            
			Spring Boot中使用Spring-data-jpa让数据访问更简单、更优雅 
			             通过解析方法名创建查询
						 @Query
			Spring-data-jpa实现了面向接口的编写方式，只需要通过编写一个继承自JpaRepository的接口就能完成数据访问
			spring.jpa.properties.hibernate.hbm2ddl.auto=XXX 是hibernate的配置属性，
			            其主要作用是：自动创建、更新、验证数据库表结构
			
			##################################################################################
                        配置多数据源的总结
            1）、需要在配置文件中配置多个数据库连接信息

            2）、通过@Configuration注释类加载数据库信息并生成对应的数据源，使用@Bean注释把数据源注册到Ioc容器中

            3）、在spring-data-jpa中，暂时不清楚具体的框架流程
     
                 注：
                 A、多数据源配置中，实体和Repositry需要按数据源划分开，
				      否则如果@Primary注解将使用默认数据完成数据库操作


			
第六个项目：SpringBoot-Redis  Spring Boot中使用Redis数据库
            
			除了String类型，实战中我们还经常会在Redis中存储对象，
			这时候我们就会想是否可以使用类似RedisTemplate<String, User>来初始化并进行操作。
			但是Spring Boot并不支持直接使用，需要我们自己实现RedisSerializer<T>接口来对传入对象进行序列化和反序列化，
			下面我们通过一个实例来完成对象的读写操作。



			
第七个项目：SpringBoot-MongoDB  Spring Boot中使用MongoDB数据库
            
			MongoDB是一个基于分布式文件存储的数据库，它是一个介于关系数据库和非关系数据库之间的产品，
			其主要目标是在键/值存储方式（提供了高性能和高度伸缩性）和传统的RDBMS系统（具有丰富的功能）
			之间架起一座桥梁，它集两者的优势于一身。
            MongoDB支持的数据结构非常松散，是类似json的bson格式，因此可以存储比较复杂的数据类型，
			也因为他的存储格式也使得它所存储的数据在Nodejs程序应用中使用非常流畅。
			
           既然称为NoSQL数据库，Mongo的查询语言非常强大，其语法有点类似于面向对象的查询语言，
          几乎可以实现类似关系数据库单表查询的绝大部分功能，而且还支持对数据建立索引。
          但是，MongoDB也不是万能的，同MySQL等关系型数据库相比，它们在针对不同的数据类型和事务要求上都存在自己独特的优势。
          在数据存储的选择中，坚持多样化原则，选择更好更经济的方式，而不是自上而下的统一化。
          
		  较常见的，我们可以直接用MongoDB来存储键值对类型的数据，如：验证码、Session等；由于MongoDB的横向扩展能力，
		  也可以用来存储数据规模会在未来变的非常巨大的数据，如：日志、评论等；由于MongoDB存储数据的弱类型，
		  也可以用来存储一些多变json数据，如：与外系统交互时经常变化的JSON报文。而对于一些对数据有复杂的高事务性要求的操作，
		  如：账户交易等就不适合使用MongoDB来存储。



			
第八个项目：SpringBoot-MyBatis Spring Boot整合MyBatis、Spring Boot中使用MyBatis注解配置详解
            
			MyBatis注解传参的方式
                     使用@Param 
					 使用Map
                     使用对象					 

					 
					 
第九个项目：SpringBoot-Transcation Spring Boot中的事务管理
            
			 在Spring Boot中，当我们使用了spring-boot-starter-jdbc或spring-boot-starter-data-jpa依赖的时候，
			 框架会自动默认分别注入DataSourceTransactionManager或JpaTransactionManager。
			 所以我们不需要任何额外配置就可以用@Transactional注解进行事务的使用。					 


第十个项目：SpringBoot-Scheduled Spring Boot中使用@Scheduled创建定时任务
            
			  ● 在Spring Boot的主类中加入@EnableScheduling注解，启用定时任务的配置
			  
              @Scheduled详解          
                  ● @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
                  ● @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
                  ● @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，
				                                   之后按fixedRate的规则每5秒执行一次
                  ● @Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则			  


第十一个项目：SpringBoot-Async Spring Boot中使用@Async实现异步调用
            
			同步调用 指程序按照定义顺序依次执行，每一行程序都必须等待上一行程序执行完成之后才能执行；
			异步调用 指程序在顺序执行时，不等待异步调用的语句返回结果就执行后面的程序。		
            
            异步调用 需要在Spring Boot的主程序中配置@EnableAsync让@Async注解能够生效
                 				
               
第十二个项目：SpringBoot-Logger Spring Boot日志管理

				Spring Boot日志管理
				    Spring Boot在所有内部日志中使用   Commons Logging，但是默认配置也提供了对常用日志的支持，
				    如：Java Util Logging，Log4J, Log4J2和Logback。每种Logger都可以通过配置使用控制台或者文件输出日志内容。
					
				Spring boot中使用log4j记录日志
				        Spring Boot默认的日志框架Logback，在引入log4j之前，需要先排除该包的依赖
						
				Spring Boot中对log4j进行多环境不同日志级别的控制
                          ● 创建多环境配置文件
                          ○ application-dev.properties：开发环境
                          ○ application-test.properties：测试环境
                          ○ application-prod.properties：生产环境	
						  

第十三个项目：SpringBoot-AOP  Spring Boot中使用AOP统一处理Web请求日志

            AOP为Aspect Oriented Programming的缩写。
			     面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。
			  
			AOP是Spring框架中的一个重要内容，它通过对既有程序定义一个切入点，然后在其前后切入不同的执行内容，
				比如常见的有：打开数据库连接/关闭数据库连接、打开事务/关闭事务、记录日志等。
				
			基于AOP不会破坏原来程序逻辑，因此它可以很好的对业务逻辑的各个部分进行隔离，
				从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
				
				
第十四个项目：SpringBoot-LogtoMongoDB Spring Boot中使用log4j实现http请求日志入mongodb

              思路：log4j提供的输出器实现自Appender接口，要自定义appender输出到MongoDB，只需要继承AppenderSkeleton类，
				      并实现几个方法即可完成。
			  该项目主要是提供一个思路去实现自定义日志的输出和管理。我们可以通过jdbc实现日志记录到mongodb，
		  也可以通过spring-data-mongo来记录到mongodb，当然我们也可以输出到其他数据库，或者输出到消息队列等待其他后续处理等。
          同时对于日志记录到mongodb，也可以直接使用log4mongo（github）实现更为方便快捷。
		  
				
第十五个项目：SpringBoot-SpringSecurity Spring Boot中使用Spring Security进行安全控制
               
			Spring Security配置
			    创建Spring Security的配置类WebSecurityConfig
				● 通过@EnableWebSecurity注解开启Spring Security的功能
				● 继承WebSecurityConfigurerAdapter，并重写它的方法来设置一些web安全的细节
				● configure(HttpSecurity http)方法
                ○ 通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。例如以上代码指定了/和/home不需要
					任何认证就可以访问，其他的路径都必须通过身份验证。
                ○ 通过formLogin()定义当需要用户登录时候，转到的登录页面。
                ● configureGlobal(AuthenticationManagerBuilder auth)方法，在内存中创建了一个用户，该用户的名称为user，
				    密码为password，用户角色为USER。
						
			   继承WebMvcConfigurerAdapter可以实现配置MVC的很多配置，此处仅配置了拦截器案例。
			   
			   
第十六个项目：SpringBoot-JavaMailSender  Spring Boot中使用JavaMailSender发送邮件
                
				Spring提供了非常好用的JavaMailSender接口实现邮件发送。在Spring Boot的Starter模块中也为此提供了自动化配置。
				       简单邮件发送
					   发送附件
					   嵌入静态资源
					   模板邮件
					   
				
第十七个项目：SpringBoot-EhCache  Spring Boot中的缓存支持（一）注解配置与EhCache使用
 
              Spring 3开始提供了强大的基于注解的缓存支持，可以通过注解配置方式低侵入的给原有Spring应用增加缓存功能，
			          提高数据访问性能。
			  在Spring Boot中对于缓存的支持，提供了一系列的自动化配置。


第十八个项目：SpringBoot-RedisLump  本项目介绍在Spring Boot的缓存支持中使用Redis进行数据缓存
			
			  由于EhCache是进程内的缓存框架，在集群模式下时，各应用服务器之间的缓存都是独立的，因此在不同服务器的进程间会存在
	      缓存不一致的情况。在一些要求高一致性（任何数据变化都能及时的被查询到）的系统和应用中，就不能再使用EhCache来解决了，
		  这个时候使用集中式缓存是个不错的选择。