### [Redis使用实践](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/)

### Redis简介
 - Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库。它可以用作数据库、缓存和消息中间件。 它支持多种类型的数据结构，如 字符串（strings）， 散列（hashes）， 列表（lists）， 集合（sets）， 有序集合（sorted sets） 与范围查询， bitmaps， hyperloglogs 和 地理空间（geospatial） 索引半径查询。 Redis 内置了 复制（replication），LUA脚本（Lua scripting）， LRU驱动事件（LRU eviction），事务（transactions） 和不同级别的 磁盘持久化（persistence）， 并通过 Redis哨兵（Sentinel）和自动 分区（Cluster）提供高可用性（high availability）。
 - **Redis 优势：**  
    - 性能极高 – Redis能读的速度是110000次/s,写的速度是81000次/s  
    - 丰富的数据类型 – Redis支持二进制案例的 Strings, Lists, Hashes, Sets 及 Ordered Sets 数据类型操作  
    - 原子 – Redis的所有操作都是原子性的，同时Redis还支持对几个操作全并后的原子性执行  
    - 丰富的特性 – Redis还支持 publish/subscribe, 通知, key 过期等等特性   

- #### [Redis.....................简单使用](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/spring-boot-15-Redis-sample/)

- #### [Redis.....................Cache](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/spring-boot-15-Redis-cache)

- #### [Redis.....................Session](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/spring-boot-15-Redis-session)

- #### [Redis.....................MQ](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/spring-boot-15-Redis-mq)

- #### [Redis.....................集群](https://github.com/timebusker/spring-boot/tree/master/spring-boot-15-Redis/spring-boot-15-Redis-cluster)



### 相关文章

 - [Redis 教程](http://www.runoob.com/redis/redis-intro.html)   
 - [Spring Boot with Redis](http://www.jianshu.com/p/a2ab17707eff)   
 - [Spring Boot中使用Redis数据库](http://blog.didispace.com/springbootredis/)    
 - [Spring Boot 整合 Redis 实现缓存操作](http://www.bysocket.com/?p=1756)    