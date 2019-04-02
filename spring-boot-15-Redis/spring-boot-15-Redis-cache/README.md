### [Redis使用实践](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/spring-boot-15-Redis-cache)

#### 缓存
- 缓存就是数据交换的缓冲区（称作Cache），当某一硬件要读取数据时，会首先从缓存中查找需要的数据，如果找到了则直接执行，找不到的话则从内存中找。由于缓存的运行速度比内存快得多，
  故缓存的作用就是帮助硬件更快地运行。
  
- 因为缓存往往使用的是RAM（断电即掉的非永久储存），所以在用完后还是会把文件送到硬盘等存储器里永久存储。电脑里最大的缓存就是内存条了，最快的是CPU上镶的L1和L2缓存，显卡的显存是给显卡运算芯片用的缓存，
  硬盘上也有16M或者32M的缓存。
  
#### Spring Cache

Spring Cache是作用在方法上的，其核心思想是：当我们在调用一个缓存方法时会把该`方法参数和返回结果作为一个键值对(默认)`存放在缓存中，
等到下次利用同样的参数来调用该方法时将不再执行该方法，而是直接从缓存中获取结果进行返回。

##### @CacheConfig —— 统一配置本类的缓存注解的属性

`@CacheConfig`是一个类级别的注解，允许在`本类内`共享`缓存的名称、KeyGenerator、CacheManager 和CacheResolver`。 不设置将使用默认值。

- cacheNames() default {}; —— 设置缓存名称，
- keyGenerator() default ""; —— 设置缓存的Key值，支持使用spEL表达式嵌入使用方法上的参数。 
- cacheManager() default ""; —— 设置cacheManager
- cacheResolver() default ""; —— 设置cacheResolver

##### @Cacheable —— 注解在方法上，启用设置缓存

启用缓存必须配置缓存名称（`cacheNames`）及该注解中的`value`值，如果使用EHCache，就是ehcache.xml中声明的cache的name。针对类

- value ：缓存的名称，在`spring`配置文件中定义，必须指定至少一个。例如：`@Cacheable(value=”mycache”)、@Cacheable(value={”cache1”,”cache2”}`

- key ： 缓存的`key，可以为空，如果指定要按照`SpEL`表达式编写，如果不指定，则缺省按照方法的所有参数进行组合`@Cacheable(value=”testcache”,key=”#userName”)`

- condition : 触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL。`@Cacheable(value=”testcache”,condition=”#userName.length()>2”)`


##### @CachePut —— 刷新缓存

注释可以确保方法被执行，同时方法的返回值也被录到缓存中，实现缓存与数据库的同步更新spEl表达式

- value ：缓存的名称，在`spring`配置文件中定义，必须指定至少一个。例如：`@Cacheable(value=”mycache”)、@Cacheable(value={”cache1”,”cache2”}`

- key ： 缓存的`key，可以为空，如果指定要按照`SpEL`表达式编写，如果不指定，则缺省按照方法的所有参数进行组合`@Cacheable(value=”testcache”,key=”#userName”)`

- condition : 触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL。`@Cacheable(value=”testcache”,condition=”#userName.length()>2”)`

##### @CacheEvict —— 清空缓存

- value : 缓存的名称，在`spring`配置文件中定义，必须指定至少一个。`@CacheEvict(value=”my cache”)

- key : 缓存的`key`，可以为空，如果指定要按照`SpEL`表达式编写，如果不指定，则缺省按照方法的所有参数进行组合。`@CacheEvict(value=”testcache”,key=”#userName”)`

- condition : 缓存的条件，可以为空，使用`SpEL`编写，返回`true`或者`false`，只有为`true`才进行缓存。`@CacheEvict(value=”testcache”,condition=”#userName.length()>2”)`

- allEntries : 是否清空所有缓存内容，缺省为`false`，如果指定为`true`，则方法调用后将立即清空所有缓存。`@CachEvict(value=”testcache”,allEntries=true)`

- beforeInvocation : 是否在方法执行前就清空，缺省为`false`，如果指定为`true`，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存。
  `@CachEvict(value=”testcache”，beforeInvocation=true)`
  
##### @Caching —— 多注解组合使用
有时候我们可能组合多个Cache注解使用；比如用户新增成功后，我们要添加id–>user；username—>user；email—>user的缓存；此时就需要@Caching组合多个注解标签了。

```
@Caching(put = {
@CachePut(value = "user", key = "#user.id"),
@CachePut(value = "user", key = "#user.username"),
@CachePut(value = "user", key = "#user.email")
})
public User save(User user) {
```

##### 自定义缓存注解

```
@Caching(put = {
@CachePut(value = "user", key = "#user.id"),
@CachePut(value = "user", key = "#user.username"),
@CachePut(value = "user", key = "#user.email")
})
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UserSaveCache {
}
```