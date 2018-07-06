----
### [Spring Security教程系列请移步到这里](https://github.com/timebusker/Spring-Security-In-Action)
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

#### 注解 @EnableWebSecurity
在 *Spring boot* 应用中使用 *Spring Security*，用到了 `@EnableWebSecurity`注解，官方说明如下：    

```
/**
 * Add this annotation to an {@code @Configuration} class to have the Spring Security
 * configuration defined in any {@link WebSecurityConfigurer} or more likely by extending
 * the {@link WebSecurityConfigurerAdapter} base class and overriding individual methods:
 */
```

意思是说， 该注解和 `@Configuration` 注解一起使用, 注解 `WebSecurityConfigurer` 类型的类，或者利用`@EnableWebSecurity` 
注解继承 `WebSecurityConfigurerAdapter`的类，这样就构成了 *Spring Security* 的配置类。

#### 抽象类 WebSecurityConfigurerAdapter
一般情况，会选择继承 `WebSecurityConfigurerAdapter` 类，其官方说明如下：
```
/**
 * Provides a convenient base class for creating a {@link WebSecurityConfigurer}
 * instance. The implementation allows customization by overriding methods.
 *
 * <p>
 * Will automatically apply the result of looking up
 * {@link AbstractHttpConfigurer} from {@link SpringFactoriesLoader} to allow
 * developers to extend the defaults.
 * To do this, you must create a class that extends AbstractHttpConfigurer and then create a file in the classpath at "META-INF/spring.factories" that looks something like:
 * </p>
 * <pre>
 * org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer = sample.MyClassThatExtendsAbstractHttpConfigurer
 * </pre>
 * If you have multiple classes that should be added you can use "," to separate the values. For example:
 *
 * <pre>
 * org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer = sample.MyClassThatExtendsAbstractHttpConfigurer, sample.OtherThatExtendsAbstractHttpConfigurer
 * </pre>
 *
 */
```
意思是说 `WebSecurityConfigurerAdapter` 提供了一种便利的方式去创建 `WebSecurityConfigurer`的实例，
只需要重写 `WebSecurityConfigurerAdapter` 的方法，即可配置拦截什么URL、设置什么权限等安全控制。

#### 方法 configure(AuthenticationManagerBuilder auth) 和 configure(HttpSecurity http)
重写了 `WebSecurityConfigurerAdapter` 的两个方法：  

```
   /**
	 * 通过 {@link #authenticationManager()} 方法的默认实现尝试获取一个 {@link AuthenticationManager}.
	 * 如果被复写, 应该使用{@link AuthenticationManagerBuilder} 来指定 {@link AuthenticationManager}.
	 *
	 * 例如, 可以使用以下配置在内存中进行注册公开内存的身份验证{@link UserDetailsService}:
	 *
	 * // 在内存中添加 user 和 admin 用户
	 * @Override
	 * protected void configure(AuthenticationManagerBuilder auth) {
	 * 	auth
	 *   	.inMemoryAuthentication().withUser("user").password("password").roles("USER").and()
	 * 		.withUser("admin").password("password").roles("USER", "ADMIN");
	 * }
	 *
	 * // 将 UserDetailsService 显示为 Bean
	 * @Bean
	 * @Override
	 * public UserDetailsService userDetailsServiceBean() throws Exception {
	 * 	return super.userDetailsServiceBean();
	 * }
	 *
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		this.disableLocalConfigureAuthenticationBldr = true;
	}
```

```
	/**
	 * 复写这个方法来配置 {@link HttpSecurity}. 
	 * 通常，子类不能通过调用 super 来调用此方法，因为它可能会覆盖其配置。 默认配置为：
	 * 
	 * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	 *
	 */
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin().and()
			.httpBasic();
	}
```

#### final 类 HttpSecurity
表1 HttpSecurity 常用方法及说明

| 方法 | 说明 |
| ---- | ---- |
| `openidLogin()` | 用于基于 OpenId 的验证 |
| `headers()`| 将安全标头添加到响应 |
| `cors()` | 配置跨域资源共享（ CORS ） |
| `sessionManagement()` | 允许配置会话管理 |
| `portMapper()` | 允许配置一个`PortMapper`(`HttpSecurity#(getSharedObject(class))`)，其他提供`SecurityConfigurer`的对象使用 `PortMapper` 从 HTTP 重定向到 HTTPS 或者从 HTTPS 重定向到 HTTP。默认情况下，Spring Security使用一个`PortMapperImpl`映射 HTTP 端口8080到 HTTPS 端口8443，HTTP 端口80到 HTTPS 端口443 |
| `jee()` | 配置基于容器的预认证。 在这种情况下，认证由Servlet容器管理 |
| `x509()` | 配置基于x509的认证 |
| `rememberMe` | 允许配置“记住我”的验证 |
| `authorizeRequests()` | 允许基于使用`HttpServletRequest`限制访问 | 
| `requestCache()` | 允许配置请求缓存 |
| `exceptionHandling()` | 允许配置错误处理 |
| `securityContext()` |  在`HttpServletRequests`之间的`SecurityContextHolder`上设置`SecurityContext`的管理。 当使用`WebSecurityConfigurerAdapter`时，这将自动应用 | 
| `servletApi()` | 将`HttpServletRequest`方法与在其上找到的值集成到`SecurityContext`中。 当使用`WebSecurityConfigurerAdapter`时，这将自动应用 |
| `csrf()` | 添加 CSRF 支持，使用`WebSecurityConfigurerAdapter`时，默认启用 |
| `logout()` | 添加退出登录支持。当使用`WebSecurityConfigurerAdapter`时，这将自动应用。默认情况是，访问URL"/ logout"，使HTTP Session无效来清除用户，清除已配置的任何`#rememberMe()`身份验证，清除`SecurityContextHolder`，然后重定向到"/login?success" |
| `anonymous()` | 允许配置匿名用户的表示方法。 当与`WebSecurityConfigurerAdapter`结合使用时，这将自动应用。 默认情况下，匿名用户将使用`org.springframework.security.authentication.AnonymousAuthenticationToken`表示，并包含角色 "ROLE_ANONYMOUS" |
| `formLogin()` | 指定支持基于表单的身份验证。如果未指定`FormLoginConfigurer#loginPage(String)`，则将生成默认登录页面 |
| `oauth2Login()` | 根据外部OAuth 2.0或OpenID Connect 1.0提供程序配置身份验证 |
| `requiresChannel()` | 配置通道安全。为了使该配置有用，必须提供至少一个到所需信道的映射 |
| `httpBasic()` | 配置 Http Basic 验证 |
| `addFilterAt()`  | 在指定的Filter类的位置添加过滤器 |

#### 类 AuthenticationManagerBuilder

```
/**
 * {@link SecurityBuilder} used to create an {@link AuthenticationManager}. Allows for
 * easily building in memory authentication, LDAP authentication, JDBC based
 * authentication, adding {@link UserDetailsService}, and adding
 * {@link AuthenticationProvider}'s.
 */
```
意思是，`AuthenticationManagerBuilder` 用于创建一个 `AuthenticationManager`，让我能够轻松的实现内存验证、LADP验证、基于JDBC的验证、
添加`UserDetailsService`、添加`AuthenticationProvider`。   

### Spring Security 校验流程图
![Spring Security 登录流程.jpg](http://upload-images.jianshu.io/upload_images/3424642-7418a70abdfc7287.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
#### AbstractAuthenticationProcessingFilter 抽象类

```
/**
	 * 调用 #requiresAuthentication(HttpServletRequest, HttpServletResponse) 决定是否需要进行验证操作。
	 * 如果需要验证，则会调用 #attemptAuthentication(HttpServletRequest, HttpServletResponse) 方法。
	 * 有三种结果：
	 * 1、返回一个 Authentication 对象。
     * 配置的 SessionAuthenticationStrategy` 将被调用，
     * 然后 然后调用 #successfulAuthentication(HttpServletRequest，HttpServletResponse，FilterChain，Authentication) 方法。
     * 2、验证时发生 AuthenticationException。
     * #unsuccessfulAuthentication(HttpServletRequest, HttpServletResponse, AuthenticationException) 方法将被调用。
     * 3、返回Null，表示身份验证不完整。假设子类做了一些必要的工作（如重定向）来继续处理验证，方法将立即返回。
	 * 假设后一个请求将被这种方法接收，其中返回的Authentication对象不为空。
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (!requiresAuthentication(request, response)) {
			chain.doFilter(request, response);

			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Request is to process authentication");
		}

		Authentication authResult;

		try {
			authResult = attemptAuthentication(request, response);
			if (authResult == null) {
				// return immediately as subclass has indicated that it hasn't completed
				// authentication
				return;
			}
			sessionStrategy.onAuthentication(authResult, request, response);
		}
		catch (InternalAuthenticationServiceException failed) {
			logger.error(
					"An internal error occurred while trying to authenticate the user.",
					failed);
			unsuccessfulAuthentication(request, response, failed);

			return;
		}
		catch (AuthenticationException failed) {
			// Authentication failed
			unsuccessfulAuthentication(request, response, failed);

			return;
		}

		// Authentication success
		if (continueChainBeforeSuccessfulAuthentication) {
			chain.doFilter(request, response);
		}

		successfulAuthentication(request, response, chain, authResult);
	}
```

#### UsernamePasswordAuthenticationFilter（AbstractAuthenticationProcessingFilter的子类）
```
public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
```

`#attemptAuthentication ()` 方法将 request 中的 username 和 password 生成 UsernamePasswordAuthenticationToken 对象，用于 `AuthenticationManager ` 的验证（即 this.getAuthenticationManager().authenticate(authRequest) ）。

默认情况下注入 Spring 容器的 `AuthenticationManager` 是 `ProviderManager`。

#### ProviderManager（AuthenticationManager的实现类）
```
/**
	 * 尝试验证 Authentication 对象
	 * AuthenticationProvider 列表将被连续尝试，直到 AuthenticationProvider 表示它能够认证传递的过来的Authentication 对象。然后将使用该 AuthenticationProvider 尝试身份验证。
	 * 如果有多个 AuthenticationProvider 支持验证传递过来的Authentication 对象，那么由第一个来确定结果，覆盖早期支持AuthenticationProviders 所引发的任何可能的AuthenticationException。 成功验证后，将不会尝试后续的AuthenticationProvider。
	 * 如果最后所有的 AuthenticationProviders 都没有成功验证 Authentication 对象，将抛出 AuthenticationException。
	 */
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Class<? extends Authentication> toTest = authentication.getClass();
		AuthenticationException lastException = null;
		Authentication result = null;
		boolean debug = logger.isDebugEnabled();

		for (AuthenticationProvider provider : getProviders()) {
			if (!provider.supports(toTest)) {
				continue;
			}

			if (debug) {
				logger.debug("Authentication attempt using "
						+ provider.getClass().getName());
			}

			try {
				result = provider.authenticate(authentication);

				if (result != null) {
					copyDetails(authentication, result);
					break;
				}
			}
			catch (AccountStatusException e) {
				prepareException(e, authentication);
				// SEC-546: Avoid polling additional providers if auth failure is due to
				// invalid account status
				throw e;
			}
			catch (InternalAuthenticationServiceException e) {
				prepareException(e, authentication);
				throw e;
			}
			catch (AuthenticationException e) {
				lastException = e;
			}
		}

		if (result == null && parent != null) {
			// Allow the parent to try.
			try {
				result = parent.authenticate(authentication);
			}
			catch (ProviderNotFoundException e) {
				// ignore as we will throw below if no other exception occurred prior to
				// calling parent and the parent
				// may throw ProviderNotFound even though a provider in the child already
				// handled the request
			}
			catch (AuthenticationException e) {
				lastException = e;
			}
		}

		if (result != null) {
			if (eraseCredentialsAfterAuthentication
					&& (result instanceof CredentialsContainer)) {
				// Authentication is complete. Remove credentials and other secret data
				// from authentication
				((CredentialsContainer) result).eraseCredentials();
			}

			eventPublisher.publishAuthenticationSuccess(result);
			return result;
		}

		// Parent was null, or didn't authenticate (or throw an exception).

		if (lastException == null) {
			lastException = new ProviderNotFoundException(messages.getMessage(
					"ProviderManager.providerNotFound",
					new Object[] { toTest.getName() },
					"No AuthenticationProvider found for {0}"));
		}

		prepareException(lastException, authentication);

		throw lastException;
	}
```
从代码中不难看出，由 provider 来验证 authentication， 核心点方法是：
```
Authentication result = provider.authenticate(authentication);
```
此处的 `provider` 是 `AbstractUserDetailsAuthenticationProvider`，
`AbstractUserDetailsAuthenticationProvider` 是AuthenticationProvider的实现，看看它的 `#authenticate(authentication)` 方法：
```
// 验证 authentication
public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
				messages.getMessage(
						"AbstractUserDetailsAuthenticationProvider.onlySupports",
						"Only UsernamePasswordAuthenticationToken is supported"));

		// Determine username
		String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED"
				: authentication.getName();

		boolean cacheWasUsed = true;
		UserDetails user = this.userCache.getUserFromCache(username);

		if (user == null) {
			cacheWasUsed = false;

			try {
				user = retrieveUser(username,
						(UsernamePasswordAuthenticationToken) authentication);
			}
			catch (UsernameNotFoundException notFound) {
				logger.debug("User '" + username + "' not found");

				if (hideUserNotFoundExceptions) {
					throw new BadCredentialsException(messages.getMessage(
							"AbstractUserDetailsAuthenticationProvider.badCredentials",
							"Bad credentials"));
				}
				else {
					throw notFound;
				}
			}

			Assert.notNull(user,
					"retrieveUser returned null - a violation of the interface contract");
		}

		try {
			preAuthenticationChecks.check(user);
			additionalAuthenticationChecks(user,
					(UsernamePasswordAuthenticationToken) authentication);
		}
		catch (AuthenticationException exception) {
			if (cacheWasUsed) {
				// There was a problem, so try again after checking
				// we're using latest data (i.e. not from the cache)
				cacheWasUsed = false;
				user = retrieveUser(username,
						(UsernamePasswordAuthenticationToken) authentication);
				preAuthenticationChecks.check(user);
				additionalAuthenticationChecks(user,
						(UsernamePasswordAuthenticationToken) authentication);
			}
			else {
				throw exception;
			}
		}

		postAuthenticationChecks.check(user);

		if (!cacheWasUsed) {
			this.userCache.putUserInCache(user);
		}

		Object principalToReturn = user;

		if (forcePrincipalAsString) {
			principalToReturn = user.getUsername();
		}

		return createSuccessAuthentication(principalToReturn, authentication, user);
	}
```
`AbstractUserDetailsAuthenticationProvider` 内置了缓存机制，从缓存中获取不到的 UserDetails 信息的话，就调用如下方法获取用户信息，然后和 用户传来的信息进行对比来判断是否验证成功。
```
// 获取用户信息
UserDetails user = retrieveUser(username,
 (UsernamePasswordAuthenticationToken) authentication);
```
`#retrieveUser()` 方法在 `DaoAuthenticationProvider` 中实现，`DaoAuthenticationProvider` 是 `AbstractUserDetailsAuthenticationProvider `的子类。具体实现如下：
```
protected final UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;

		try {
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);
		}
		catch (UsernameNotFoundException notFound) {
			if (authentication.getCredentials() != null) {
				String presentedPassword = authentication.getCredentials().toString();
				passwordEncoder.isPasswordValid(userNotFoundEncodedPassword,
						presentedPassword, null);
			}
			throw notFound;
		}
		catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(
					repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}
```
可以看到此处的返回对象 `userDetails` 是由 `UserDetailsService` 的 `#loadUserByUsername(username)` 来获取的。

到了此处，就很清晰啦，Demo 中自定义了 `AnyUserDetailsService`。    

### 实现自定义登录校验   
相比于上一个demo，在 `WebSecurityConfig` 中添加了如下代码：
```
/**
     * 添加 UserDetailsService， 实现自定义登录校验
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(anyUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

```

> BCryptPasswordEncoder相关知识：

> 用户表的密码通常使用MD5等不可逆算法加密后存储，为防止彩虹表破解更会先使用一个特定的字符串（如域名）加密，然后再使用一个随机的salt（盐值）加密。

> 特定字符串是程序代码中固定的，salt是每个密码单独随机，一般给用户表加一个字段单独存储，比较麻烦。

> BCrypt算法将salt随机并混入最终加密后的密码，验证时也无需单独提供之前的salt，从而无需单独处理salt问题。

#### BCryptPasswordEncoder 是在哪里使用的？
登录时用到了 `DaoAuthenticationProvider` ，它有一个方法
`#additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)`，此方法用来校验从数据库取得的用户信息和用户输入的信息是否匹配。

#### 在注册时，对用户密码加密
应用 `BCryptPasswordEncoder ` 之后，明文密码是无法被识别的，就会校验失败，只有存入密文密码才能被正常识别。所以，应该在注册时对用户密码进行加密。
```
/**
     * 加密密码
     */
    private void encryptPassword(UserEntity userEntity){
        String password = userEntity.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        userEntity.setPassword(password);
    }
```

### 自定义的 `Filter`   
Spring Security 默认的过滤器链：[官网位置](http://docs.spring.io/spring-security/site/docs/5.0.0.M1/reference/htmlsingle/#ns-custom-filters)    
  
|执行顺序|别名|类名称|Namespace Element or Attribute
|--|--|--|--|
|1|CHANNEL_FILTER|ChannelProcessingFilter|http/intercept-url@requires-channel
|2|SECURITY_CONTEXT_FILTER|SecurityContextPersistenceFilter|http
|3|CONCURRENT_SESSION_FILTER|ConcurrentSessionFilter|session-management/concurrency-control
|4|HEADERS_FILTER|HeaderWriterFilter|http/headers
|5|CSRF_FILTER|CsrfFilter|http/csrf
|6|LOGOUT_FILTER|LogoutFilter|http/logout
|7|X509_FILTER|X509AuthenticationFilter|http/x509
|8|PRE_AUTH_FILTER|AbstractPreAuthenticatedProcessingFilter( Subclasses)|N/A
|9|CAS_FILTER|CasAuthenticationFilter|N/A
|10|FORM_LOGIN_FILTER|UsernamePasswordAuthenticationFilter|http/form-login
|11|BASIC_AUTH_FILTER|BasicAuthenticationFilter|http/http-basic
|12|SERVLET_API_SUPPORT_FILTER|SecurityContextHolderAwareRequestFilter|http/@servlet-api-provision
|13|JAAS_API_SUPPORT_FILTER|JaasApiIntegrationFilter|http/@jaas-api-provision
|14|REMEMBER_ME_FILTER|RememberMeAuthenticationFilter|http/remember-me
|15|ANONYMOUS_FILTER|AnonymousAuthenticationFilter|http/anonymous
|16|SESSION_MANAGEMENT_FILTER|SessionManagementFilter|session-management
|17|EXCEPTION_TRANSLATION_FILTER|ExceptionTranslationFilter|http
|18|FILTER_SECURITY_INTERCEPTOR|FilterSecurityInterceptor|http
|19|SWITCH_USER_FILTER|SwitchUserFilter|N/A

-------
自定义的 `Filter` 建议继承 `GenericFilterBean`，本文示例：
```
public class BeforeLoginFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("This is a filter before UsernamePasswordAuthenticationFilter.");
        // 继续调用 Filter 链
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
```

配置自定义 Filter 在 Spring Security 过滤器链中的位置
------
配置很简单，本文示例：
```
protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        // 在 UsernamePasswordAuthenticationFilter 前添加 BeforeLoginFilter
        http.addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class);

        // 在 CsrfFilter 后添加 AfterCsrfFilter
        http.addFilterAfter(new AfterCsrfFilter(), CsrfFilter.class);
    }
```
说明：
`HttpSecurity` 有三个常用方法来配置：
 - addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter)
在 beforeFilter 之前添加 filter
 - addFilterAfter(Filter filter, Class<? extends Filter> afterFilter)
在 afterFilter 之后添加 filter
 - addFilterAt(Filter filter, Class<? extends Filter> atFilter)
在 atFilter 相同位置添加 filter， 此 filter 不覆盖 filter

> 通过在不同 `Filter` 的 `doFilter()` 方法中加断点调试，可以判断哪个 filter 先执行，从而判断 filter 的执行顺序 。