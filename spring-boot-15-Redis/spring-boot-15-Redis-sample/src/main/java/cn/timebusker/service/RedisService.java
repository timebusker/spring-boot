package cn.timebusker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.timebusker.Entity.AdministratorTest;

@Service
public class RedisService {

	/**
	 * 对象存储测试 StringRedisTemplate就相当于RedisTemplate<String, String>的实现
	 * spring-data-redis中有RedisTemplate<K, V>接口
	 * 除了String类型，我们还经常会在Redis中存储对象，使用类似RedisTemplate<String,User>来初始化并进行操作。
	 * 但是Spring Boot并不支持直接使用，需要我们自己实现RedisSerializer <T>接口来对传入对象进行序列化和反序列化。
	 * 
	 * 总之，存储在redis中的对象需要序列化为字符串，类似于IO流里传输对象 以特定的方式对类实例的瞬时状态进行编码保存的一种操作
	 * 序列化作用的对象是类的实例 对实例进行序列化,就是保存实例当前在内存中的状态.包括实例的每一个属性的值和引用等
	 */

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, AdministratorTest> redisTemplate;

	public Object addString(String key) throws Exception {
		String value = System.currentTimeMillis() + "";
		stringRedisTemplate.opsForValue().set(key, value);
		return value;
	}

	public Object addListString(String key) throws Exception {
		String value = System.currentTimeMillis() + "";
		stringRedisTemplate.opsForList().rightPush("list", key + "--" + value);
		return value;
	}

	public Object getString(String key) throws Exception {
		String v = stringRedisTemplate.opsForValue().get("key");
		return v;
	}

	public Object getString(List<String> keys) throws Exception {
		Object v = stringRedisTemplate.opsForValue().multiGet(keys);
		return v;
	}

	public Object updateString(String key, String value) throws Exception {
		stringRedisTemplate.opsForValue().set(key, value);
		String v = stringRedisTemplate.opsForValue().get("key");
		return v;
	}

	public Object deleteString(String key) throws Exception {
		stringRedisTemplate.opsForValue().getOperations().delete(key);
		String v = stringRedisTemplate.opsForValue().get("key");
		return v;
	}

	public Object addObject(String key) throws Exception {
		key = "蝙蝠侠";
		AdministratorTest admin = new AdministratorTest("蝙蝠侠", "20", "1010", "TOOOM");
		redisTemplate.opsForValue().set(key, admin);
		
		Object object = redisTemplate.opsForValue().get("蝙蝠侠");
		System.out.println(JSON.toJSONString(object));
		admin = (AdministratorTest)object;
		System.out.println("\n----------------\t" + redisTemplate.opsForValue().get("蝙蝠侠"));
		System.out.println("\n================\t" + redisTemplate.opsForValue().get("蝙蝠侠").toString());
		System.out.println("\n++++++++++++++++\t" + (AdministratorTest) redisTemplate.opsForValue().get("蝙蝠侠"));
		System.out.println("\n||||||||||||||||\t" + ((AdministratorTest) redisTemplate.opsForValue().get("蝙蝠侠")).toString());
		
		AdministratorTest admina = (AdministratorTest)redisTemplate.opsForValue().get("蝙蝠侠");
		System.out.println(admina.getUsername());
		return redisTemplate.opsForValue().get(key);
	}
}