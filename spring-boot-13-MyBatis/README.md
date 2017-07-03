----
## [集成MyBatis持久层框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-13-MyBatis/)

### [Spring Boot整合MyBatis使用实践总结](https://github.com/timebusker/spring-boot-Mybaits)

### 关于MyBatis
 - ![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-13-MyBatis/mybatis-logo.png?raw=true)

 - MyBatis 是支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架。MyBatis 避免了几乎所有的 JDBC 
代码和手动设置参数以及获取结果集。MyBatis 可以对配置和原生Map使用简单的 XML 或注解，将接口和 
Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。

### 整合MyBatis
#### **pom.xml**中引入依赖
+ 集成spring-boot-starter基础和spring-boot-starter-test用来做单元测试验证数据访问 
+ 集成mysql-connector-java连接mysql的必要依赖 
+ 整合MyBatis的核心依赖mybatis-spring-boot-starter 
+ 不引入spring-boot-starter-jdbc依赖，是由于mybatis-spring-boot-starter中已经包含了此依赖 

***spring-boot-starter-jdbc的作用就是：引入tomcat-jdbc数据源（spring-boot默认数据源）和引入spring-jdbc持久化操作***
***在本案例中，排除tomcat-jdbc，使用druid***
```xml
		<!-- Spring Boot 核心三个模块 START -->
		<!-- 核心模块，包括自动配置支持、日志和YAML -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
			<exclusions>
				<!-- 排除默认日志框架 spring boot 1.4.X默认日志框架为 SLF4J+Logback,这里先排除 -->
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<!-- spring-boot-starter-test：测试模块，包括JUnit、Hamcrest、Mockito -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- 引入Web模块，需添加spring-boot-starter-web模块： -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<!-- END -->
		<!-- 排除Tomcat,使用jetty -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jetty</artifactId>
		</dependency>
		<!-- devtools：是spring boot的一个热部署工具 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<optional>true</optional>
		</dependency>
		<!-- MyBatis -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.0</version>
			<exclusions>
				<exclusion>
					<groupId>org.apache.tomcat</groupId>
					<artifactId>tomcat-jdbc</artifactId>
				</exclusion>
				<!-- <exclusion> <groupId>org.springframework</groupId> <artifactId>spring-jdbc</artifactId> </exclusion> -->
			</exclusions>
		</dependency>
		<!-- 指定使用C3P0数据源：性能差,不建议使用 -->
		<!-- <dependency> <groupId>com.mchange</groupId> <artifactId>c3p0</artifactId> <version>0.9.5.2</version> </dependency> -->
		<!-- 指定使用C3P0数据源 -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.0.31</version>
		</dependency>
```

#### 传参方式
+ 使用@Param —— @Param中定义的name对应了SQL中的#{name}
```
	@Select("SELECT * FROM user_info WHERE username = #{name}")
	List<UserInfo> findByName(@Param("name") String name);
```

+ 使用Map —— 通过Map对象来作为传递参数的容器
```
    // 两个语句实现效果一致
	//@Insert("INSERT INTO user_info(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR}, #{usertype,jdbcType=VARCHAR},#{enabled,jdbcType=INTEGER}, #{realname,jdbcType=VARCHAR},#{email,jdbcType=VARCHAR}, #{tel,jdbcType=VARCHAR})")
	@Insert("INSERT INTO user_info(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id}, #{username},#{password}, #{usertype},#{enabled}, #{realname},#{email}, #{tel})")
	int insertByMap(Map<String, Object> map);
```
```
	@Test
	public void insertByMap() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 13);
		map.put("username", "***");
		map.put("password", "password");
		System.out.println("\n\n\n\n\n");
		System.out.println("输出结果：" + dao.insertByMap(map));
		System.out.println("\n\n\n\n\n");
	}
```

+ 使用对象 —— 直接使用普通的Java对象来作为查询条件的传参
```
	@Insert("INSERT INTO user_info(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id}, #{username},#{password}, #{usertype},#{enabled}, #{realname},#{email}, #{tel})")
	int insert(UserInfo ui);
	
	@Update("UPDATE user_info SET password=#{password} WHERE username=#{username}")
	void update(UserInfo ui);
```

+ 返回结果的绑定 —— @Result中的property属性对应User对象中的成员名，column对应SELECT出的字段名
对于增、删、改操作相对变化较小。而对于“查”操作，我们往往需要进行多表关联，汇总计算等操作，
那么对于查询的结果往往就不再是简单的实体对象了，往往需要返回一个与数据库实体不同的包装类，
那么对于这类情况，就可以通过@Results和@Result注解来进行绑定，具体如下：
```
	// 单标查询可以不使用@Results进行属性绑定，多表待测试
	@Results({@Result(property = "username", column = "username"),@Result(property = "realname", column = "realname")})
	@Select("SELECT username,realname FROM user_info WHERE 1=1")
	List<UserInfo> queryById();
```

### 关于rollback和commit的用法  

+ 所谓 **DML** 语句就是 **INSERT/DELETE/UPDATE/SELECT**，而 **CREATE TABLE/VIEW** 之类的语句，是 **DDL**。

+ DML语句，执行完之后，处理的数据，都会放在回滚段中（除了 SELECT 语句），等待用户进行提交（COMMIT）或者回滚（ROLLBACK），
当用户执行 COMMIT/ROLLBACK 后，放在回滚段中的数据就会被删除。

所有的 DML 语句都是要显式提交了，所谓“显式提交”，就是要执行 COMMIT/ROLLBACK 。
而其他的诸如 DDL 语句的，都是隐式提交的。就是说，不用进行 COMMIT / ROLLBACK 。在运行那些非
DML 语句后，ORACLE 已经进行了隐式提交，例如 CREATE TABLE，在运行脚本后，表已经建好了，并不在需要你再进行显式提交。

可以形象的理解成
commit就是将数据库中的数据提交到回滚段中，会覆盖原先数据
rollback 就是将回滚段中的数据回滚到数据库中，不会覆盖数据，不管先前有没有设置回滚点，rollback回滚的数据都不会变，除非commit覆盖了原先的数据

### 相关文章

[MyBatis Generator 详解](http://blog.csdn.net/isea533/article/details/42102297)

----