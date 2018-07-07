#### Spring-Boot多数据源
使用JdbcTemplate和Spring-data-jpa时，都使用了单数据源。在单数据源的情况下，Spring Boot的配置非常简单，
只需要在application.properties文件中配置连接参数即可。但是往往随着业务量发展，我们通常会进行数据库拆分或是引入其他数据库，
从而我们需要配置多个数据源。     

#### 多数据源配置使用的核心是根据配置文件，初始化特定数据源，并绑定到特定的数据服务层，（dao、repository、mapper）,因此
spring-jdbc、Spring-data-jpa、Mybatis各自实现存在一定差异。其中多数据源又分静态数据源和动态数据源。

- 静态数据源：跨库使用数据库比较常用
- 动态数据源：读写分离操作比较常用 

##### [JdbcTemplate和Spring-data-jpa](https://www.cnblogs.com/fengmao/p/7538854.html)
##### [Spring Boot 集成Mybatis实现多数据源](https://blog.csdn.net/maoyeqiu/article/details/74011626)
##### [Spring Boot 集成Mybatis实现多数据源](https://blog.csdn.net/neosmith/article/details/61202084)
