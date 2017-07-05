### [Redis简单使用](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/spring-boot-15-Redis-sample/)

- #### 实践问题
  + 自己实现序列化接口，单元测试通过，service 类测试不通过
     * 配置类  
```
	/**
	 * RedisTemplate配置
	 * 
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, T> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, T> template = new RedisTemplate<String, T>();
		template.setConnectionFactory(factory);
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new JdkSerializationRedisSerializer());
//		template.setValueSerializer(new RedisObjectSerializer());
//		template.afterPropertiesSet();
		return template;
	}
```    
     * 单元测试  
  

```    
	@Test
	public void test() throws Exception {
		System.out.println("\n******************************************************\n");
		AdministratorTest Admin = new AdministratorTest("蝙蝠侠", "20", "1010", "TOOOM");
		redisTemplate.opsForValue().set(Admin.getUsername(), Admin);
		System.out.println("\n----------------\t" + redisTemplate.opsForValue().get("蝙蝠侠"));
		System.out.println("\n================\t" + redisTemplate.opsForValue().get("蝙蝠侠").toString());
		System.out.println("\n++++++++++++++++\t" + (AdministratorTest) redisTemplate.opsForValue().get("蝙蝠侠"));
		System.out.println("\n||||||||||||||||\t" + ((AdministratorTest) redisTemplate.opsForValue().get("蝙蝠侠")).toString());
		AdministratorTest admina = (AdministratorTest) redisTemplate.opsForValue().get("蝙蝠侠");
		System.out.println("\n\n\n" + admina.getUsername());
		System.out.println("\n******************************************************\n");
	}  
```    
     * service类  


```    
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
```