----
## [spring-boot-7-EhCache spring boot集成Ehcache缓存框架](https://github.com/timebusker/spring-boot/tree/master/spring-boot-7-EhCache/)

#### - 项目阐述
    EhCache 是一个纯Java的进程内缓存框架，具有快速、精干等特点，是Hibernate中默认的CacheProvider；
	Ehcache提供了多种缓存策略，主要分为内存和磁盘两级，所以无需担心容量问题。
    ![image](https://github.com/timebusker/spring-boot/raw/master/static/spring-boot-7-EhCache/chcache.jpg?raw=true)
 
 + #### 引入核心依赖
```
		<!-- 引入Ehcache 的支持和依赖 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
		<dependency>
			<groupId>net.sf.ehcache</groupId>
			<artifactId>ehcache</artifactId>
		</dependency>
```
	 
 + #### 配置缓存策略
```
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="ehcache.xsd"
	updateCheck="false">

	<!-- 设置缓存路径（ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置） -->
	<!-- 
	user.home – 用户主目录
    user.dir  – 用户当前工作目录
    java.io.tmpdir – 默认临时文件路径 
    -->
	<diskStore path="java.io.tmpdir/ehcache-temp" />

	<!-- 默认缓存策略：当ehcache找不到定义的缓存时，则使用这个缓存策略 -->
	<defaultCache maxElementsInMemory="10000" eternal="false" timeToIdleSeconds="120" timeToLiveSeconds="120"
		overflowToDisk="true" maxElementsOnDisk="10000000" diskPersistent="false" diskExpiryThreadIntervalSeconds="120"
		memoryStoreEvictionPolicy="LRU" />

	<!-- 自定缓存策略 -->
	<cache name="user-cache" maxElementsInMemory="10000" eternal="false" timeToIdleSeconds="15" timeToLiveSeconds="20"
		overflowToDisk="true" maxElementsOnDisk="10000000" diskPersistent="true" diskExpiryThreadIntervalSeconds="120"
		memoryStoreEvictionPolicy="LRU" />

	<!-- 
	
	name：缓存名称；
	
	maxElementsInMemory：内存中最大缓存对象数；
	
	maxElementsOnDisk：硬盘中最大缓存对象数，若是0表示无穷大；
	
	eternal：true表示对象永不过期，此时会忽略timeToIdleSeconds和timeToLiveSeconds属性，默认为false；
	
	overflowToDisk：true表示当内存缓存的对象数目达到了maxElementsInMemory界限后，会把溢出的对象写到硬盘缓存中。注意：如果缓存的对象要写入到硬盘中的话，则该对象必须实现了Serializable接口；
	
	diskSpoolBufferSizeMB：磁盘缓存区大小，默认为30MB；
	
	diskPersistent：是否缓存虚拟机重启期数据；
	
	diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认为120秒；
	
	timeToIdleSeconds： 设定允许对象处于空闲状态的最长时间，以秒为单位。当对象自从最近一次被访问后，如果处于空闲状态的时间超过了timeToIdleSeconds属性值，这个对象就会过期，EHCache将把它从缓存中清空；
	
	timeToLiveSeconds：设定对象允许存在于缓存中的最长时间，以秒为单位。当对象自从被存放到缓存中后，如果处于缓存中的时间超过了 timeToLiveSeconds属性值，这个对象就会过期，EHCache将把它从缓存中清除，timeToLiveSeconds必须大于timeToIdleSeconds属性，才有意义；
	
	memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。可选策略有：LRU（最近最少使用，默认策略）、FIFO（先进先出）、LFU（最少访问次数）。
	
	-->
</ehcache>
```
 + #### 开启缓存
```
package cn.timebusker.conf;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class EhcacheConfiguration implements CachingConfigurer {

	/*
	 * ehcache 主要的管理器
	 */
	@Bean
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
		return new EhCacheCacheManager(bean.getObject());
	}

	/*
	 * 据shared与否的设置,Spring分别通过CacheManager.create() 或new
	 * CacheManager()方式来创建一个ehcache基地.
	 */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		// 设置cache的基地是Spring独立用,还是与hibernate的Ehcache共享
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}

	/**
	 * 制定生成缓存KEY策略，在默认情况下，将直接使用该策略
	 * 
	 * @return
	 */
	@Bean
	public KeyGenerator defaultKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				// 生成 类名+方法名+加参数 的KEY
				StringBuilder builder = new StringBuilder();
				builder.append(o.getClass().getName());
				builder.append(method.getName());
				for (Object obj : objects) {
					builder.append(obj.toString());
				}
				return builder.toString();
			}
		};
	}

	@Bean
	@Override
	public CacheManager cacheManager() {
		EhCacheCacheManager cacheManager = new EhCacheCacheManager();
		return cacheManager;
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return new SimpleKeyGenerator();
	}
}

```
 + #### 使用注释标注缓存
```
package cn.timebusker.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.timebusker.dao.UserRepository;
import cn.timebusker.entity.User;
import cn.timebusker.service.UserService;

@Service
@CacheConfig(cacheNames = "user-cache")
public class UserServiceImpl implements UserService {
	
	public static final String CACHE_KEY = "USERS_CACHE_KEY";

	@Autowired
	UserRepository repository;

	/**
	 * 使用ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	@Override
	@Cacheable(value = {})
	public User findUserById(long id) {
		User u = repository.findOne(id);
		return u;
	}
	
	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@Override
	@Cacheable
	public List<User> findAllUser() {
		List<User> list = repository.findAll();
		return list;
	}

	/**
	 * 更新或者新增用户信息
	 * 
	 * @param user
	 */
	@Override
	@CachePut
	public void saveAndFlush(User user) {
		repository.saveAndFlush(user);
	}

	/**
	 * 通过ID删除一个用户
	 * 
	 * @param id
	 */
	@Override
	@CacheEvict
	public void deleteUserById(long id) {
		repository.delete(id);
	}
}

```
		 
----

 - #### 相关文章
 - [Ehcache详细解读](http://raychase.iteye.com/blog/1545906#comments)
 - [Spring Boot中的缓存支持（一）注解配置与EhCache使用](http://www.jianshu.com/p/64f684bd0ce9)
 - [spring-boot集成ehcache实现缓存机制](http://www.tuicool.com/articles/m2qAfqn)
