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
