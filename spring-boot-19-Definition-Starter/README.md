### [Spring Boot自定义Starter](https://github.com/timebusker/spring-boot/tree/master/spring-boot-19-Definition-Starter/)  

#### 知识扫盲
  - ##### 条件注入
     + @Conditional(TestCondition.class) ： 表示该类下面的所有@Bean都会启用配置，也可以标注在方法上面，只是对该方法启用配置  
     + @ConditionalOnBean ： 仅仅在当前上下文中存在某个对象时，才会实例化一个Bean  
     + @ConditionalOnClass ： 某个class位于类路径上，才会实例化一个Bean   
     + @ConditionalOnExpression ： 当表达式为true的时候，才会实例化一个Bean   
     + @ConditionalOnMissingBean ： 仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean  
     + @ConditionalOnMissingClass : 某个class类路径上不存在的时候，才会实例化一个Bean  
     + @ConditionalOnNotWebApplication : 不是web应用  
     
  - ##### dependencies与dependencyManagement的区别
     + dependencyManagement 元素来提供了一种管理依赖版本号的方式，只是声明依赖，并不实际引入，    
       子项目需要显示的声明需要用的依赖才能够进行使用依赖，    
     + dependencies相对于dependencyManagement，所有声明在dependencies里的依赖都会自动引入，  
       并默认被所有的子项目继承。  
     +  [简单的理解就是：dependencyManagement就是父pom声明共同的版本使用的](#)  
     
#### Spring Boot自定义Starter 
  - ##### 原理
    1、 Spring Boot在启动时扫描项目所依赖的JAR包，寻找包含spring.factories文件的JAR包  
    2、 根据spring.factories配置加载AutoConfigure类  
    3、 根据 @Conditional注解的条件，进行自动配置并将Bean注入Spring Context  
    
  - ##### stater命名说明
    Spring 官方 Starter通常命名为spring-boot-starter-{name}如 spring-boot-starter-web，   
    Spring官方建议非官方Starter命名应遵循{name}-spring-boot-starter的格式。
  
  - ##### 核心注解说明
    解释下用到的几个和Starter相关的注解：  
    @ConditionalOnClass，当classpath下发现该类的情况下进行自动配置    
    @ConditionalOnMissingBean，当Spring Context中不存在该Bean时    
    @ConditionalOnProperty(prefix = "calculate ",value = "enabled",havingValue = "true")，当配置文件中calculate.enabled=true时  
    
  - ##### 编写spring.factories
    在src/main/resources/META-INF下创建spring.factories文件，内容如下：  
    `org.springframework.boot.autoconfigure.EnableAutoConfiguration=cn.timebusker.config.CalculateAutoConfigure`
    
