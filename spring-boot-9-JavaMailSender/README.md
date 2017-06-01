----
## [spring-boot-9-JavaMailSender 实现简单的邮件发送](https://github.com/timebusker/spring-boot/tree/master/spring-boot-9-JavaMailSender/)

- #### 项目阐述
     相信使用过Spring的众多开发者都知道Spring提供了非常好用的JavaMailSender接口实现邮件发送。
     在Spring Boot的Starter模块中也为此提供了自动化配置。下面通过实例看看如何在Spring Boot中
     使用JavaMailSender发送邮件。
	
- #### 快速入门

+ 在Spring Boot的工程中的pom.xml中引入spring-boot-starter-mail依赖：
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

+ 以QQ邮箱为例,配置发送邮件示例：
```
spring.mail.host=smtp.qq.com
spring.mail.username=用户名
spring.mail.password=密码
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

- #### 进阶使用
   
   + 发送附件
   
   
   + 嵌入静态资源
   
   
   + 模板邮件
   
   
----