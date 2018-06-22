----
## [spring-boot-11-SpringSecurity 集成SpringSecurity安全框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-11-SpringSecurity)

### Spring Security 简介
Spring Security为基于 Java EE 的企业软件应用程序提供全面的安全服务。特别是使用 Spring Framework 构建的项目，可以更好的使用 Spring Security 来加快构建的速度。   
Spring Security 的出现有有很多原因，但主要是基于 Java EE 的 Servlet 规范或 EJB 规范的缺乏对企业应用的安全性方面的支持。而使用 Spring Security 克服了这些问题，并带来了数十个其他有用的可自定义的安全功能。   

应用程序安全性的两个主要领域是：
  - **身份认证（authentication）** ：“认证”是建立主体 （principal）的过程。主体 通常是指可以在您的应用程序中执行操作的用户、设备或其他系统；
  - **授权（authorization）** ：或称为“访问控制（access-control），“授权”是指决定是否允许主体在应用程序中执行操作。为了到达需要授权决定的点，认证过程已经建立了主体的身份。这些概念是常见的，并不是特定于 Spring Security。

在认证级别，Spring Security 支持各种各样的认证模型。这些认证模型中的大多数由第三方提供，或者由诸如因特网工程任务组的相关标准机构开发。此外，Spring Security 提供了自己的一组认证功能。具体来说，Spring Security 目前支持所有这些技术的身份验证集成：   
  + LDAP（一种非常常见的跨平台身份验证需求，特别是在大型环境中
  + 基于表单的身份验证（用于简单的用户界面需求）
  + OpenID 身份验证
  + 基于预先建立的请求头的验证（例如Computer Associates Siteminder）
  + Jasig Central Authentication Service，也称为CAS，这是一个流行的开源单点登录系统
  + 远程方法调用（RMI）和HttpInvoker（Spring远程协议）的透明认证上下文传播
  + 自动“remember-me”身份验证（所以您可以勾选一个框，以避免在预定时间段内重新验证）
  + 匿名身份验证（允许每个未经身份验证的调用，来自动承担特定的安全身份）
  + Run-as 身份验证（如果一个调用应使用不同的安全身份继续运行，这是有用的）
  + Java认证和授权服务（Java Authentication and Authorization Service，JAAS）
  + Java EE 容器认证（因此，如果需要，仍然可以使用容器管理身份验证）
  + ..................................
  
许多独立软件供应商（ISV）采用Spring Security，是出于这种灵活的认证模型。这样，他们可以快速地将他们的解决方案与他们的最终客户需要进行组合，
从而避免了进行大量的工作或者要求变更。如果上述认证机制都不符合您的需求，Spring Security 作为一个开放平台，可以基于它很容易就实现自己的认证机制。    

Spring Security提供了一组深入的授权功能。有三个主要领域：    
  + 对 Web 请求进行授权
  + 授权某个方法是否可以被调用
  + 授权访问单个领域对象实例

### Spring Security最小化依赖   
```
<dependencies>
    ......
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-web</artifactId>
        <version>4.2.2.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-config</artifactId>
        <version>4.2.2.RELEASE</version>
    </dependency>
    ......
</dependencies>
```  
#### Core - spring-security-core.jar  
包含核心的 authentication 和 authorization 的类和接口、远程支持和基础配置API。任何使用 Spring Security 的应用都需要引入这个 jar。支持本地应用、远程客户端、方法级别的安全和 JDBC 用户配置。主要包含的顶级包为为：   
  - org.springframework.security.core：核心
  - org.springframework.security.access：访问，即 authorization 的作用
  - org.springframework.security.authentication：认证
  - org.springframework.security.provisioning：配置 

#### Remoting - spring-security-remoting.jar   
提供与 Spring Remoting 整合的支持，你并不需要这个除非你需要使用 Spring Remoting 写一个远程客户端。主包为： `org.springframework.security.remoting`   

#### Web - spring-security-web.jar  
包含 filter 和相关 Web安全的基础代码。如果我们需要使用 Spring Security 进行 Web 安全认证和基于URL的访问控制。主包为： `org.springframework.security.web`

##### Config - spring-security-config.jar   
包含安全命名空间解析代码和 Java 配置代码。 如果您使用 Spring Security XML 命名空间进行配置或 Spring Security 的 Java 配置支持，
则需要它。 主包为： `org.springframework.security.config`。我们不应该在代码中直接使用这个jar中的类。   

#### LDAP - spring-security-ldap.jar  
LDAP 认证和配置代码。如果你需要进行 LDAP 认证或者管理 LDAP 用户实体。顶级包为： `org.springframework.security.ldap`  

#### ACL - spring-security-acl.jar   
特定领域对象的ACL(访问控制列表)实现。使用其可以对特定对象的实例进行一些安全配置。顶级包为： `org.springframework.security.acls`   

#### CAS - spring-security-cas.jar   
pring Security CAS 客户端集成。如果你需要使用一个单点登录服务器进行 Spring Security Web 安全认证，需要引入。顶级包为： `org.springframework.security.cas`    

#### OpenID - spring-security-openid.jar     
OpenId Web 认证支持。基于一个外部 OpenId 服务器对用户进行验证。顶级包为： `org.springframework.security.openid`，需要使用 OpenID4Java.    
  
一般情况下，spring-security-core和spring-security-config都会引入，在 Web 开发中，我们通常还会引入spring-security-web。   



