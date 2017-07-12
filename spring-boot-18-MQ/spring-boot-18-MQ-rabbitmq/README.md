### [整合RabbitMQ示例](https://github.com/timebusker/spring-boot/tree/master/spring-boot-18-MQ/spring-boot-18-MQ-rabbitmq/)

- #### RabbitMQ基本概念
   + [**Broke：**]()可以理解为消息队列服务器的实体，它是一个中间件应用，负责接收消息生产者的消息，然后将消息发送至消息接收者或者其他的Broker；
   + [**Exchange：**]()消息交换机，是消息第一个到达的地方，消息通过它指定的路由规则，分发到不同的消息队列中去；
   + [**Queue：**]()消息队列，消息通过发送和路由之后最终到达的地方，到达Queue的消息即进入逻辑上等待消费的状态。 每个消息都会被发送到一个或多个队列；
   + [**Binding：**]()绑定，它的作用就是把Exchange和Queue按照路由规则绑定起来，也就是Exchange和Queue之间的虚拟连接；
   + [**Routing Key：**]()路由关键字，Exchange根据这个关键字把消息投递到对应的Queue中；
   + [**Virtual host：**]()虚拟主机，它是对Broker的虚拟划分，将消费者、生产者和它们依赖的AMQP相关结构进行隔离，一般都是为了安全考虚。比如，我们可以在一个Broker中设置多个虚拟主机，对不同用户进行权限的分离；
   + [**Connection：**]()连接，代表生产者、消费者、Broker之间进行通信的物理网络；
   + [**Channel：**]()消息通道，用千连接生产者和消费者的逻辑结构。在客户端的每个连接里，可建立多个Channel, 每个Channel代表一 个会话任务，通过Channel可以隔离同一连接中的不同交互内容。
   + [**Producer：**]()消息生产者， 制造消息并发送消息的程序；  
   + [**Consumer：**]()消息消费者， 接收消息并处理消息的程序。    
   + #### 消息投递到队列的整个过程大致如下：  
      * 客户端连接到消息队列服务器，打开一个Channel;
      * 客户端声明一个Exchange,并设置相关属性;
      * 客户端声明一个Queue,并设置相关属性;
      * 客户端使用Routing Key,在Exchange和Queue之间建立好绑定关系;
      * 客户端投递消息到Exchange;
      * Exchange接收到消息后，根据消息的Key和已经设置的Binding,进行消息路由,将消息投递到一个或多个Queue里。   
   + #### Exchange也有几种类型：
      * [**Direct交换机**]()：完全根据Key进行投递。 比如，绑定时设置了Routing Key为abc,那么客户端提交的消息，只有设置了Key为abc 的才会被投递到队列  
        ![image](https://github.com/timebusker/spring-boot/raw/master/static/18/rabbitmq/direct.png?raw=true)
      * [**Topic交换机**]()：对Key进行模式匹配后进行投递，可以使用符号＃匹配一个或多个词，符号＊匹配正好 一 个词。比如，abc.#匹配abc.def.ghi,abc.*只匹配abc.def  
        ![image](https://github.com/timebusker/spring-boot/raw/master/static/18/rabbitmq/topic.png?raw=true)
      * [**Fanout交换机**]()：不需要任何Key,它采取广播的模式，一个消息进来时，投递到与该交换机绑定的所有队列   
        ![image](https://github.com/timebusker/spring-boot/raw/master/static/18/rabbitmq/fanout.png?raw=true) 
   + #### RabbitMQ支持消息的待久化：    
     为了数据安全考虑，大多数情况下都会选择持久化，将数据写在磁盘上。消息队列持久化包括3个部分：  
     * Exchange 持久化，在声明时指定  durable = > 1
     * Queue 待久化，在声明时指定  durable = > 1
     * 消息持久化，在投递时指定delivery_mode => 2(1是非持久化)   
     如果Exchange和Queue都是持久化的，那么它们之间的Binding也是持久化的。如果Exchange和Queue两者之间有一个是待久化的，一个是非持久化的，就不允许建立绑定。    

### 相关文章
[关于RabbitMQ](http://lynnkong.iteye.com/blog/1699684)    
[Rabbitmq基本原理](http://www.cnblogs.com/jun-ma/p/4840869.html)   
[RabbitMQ Exchange 模式 - direct](http://blog.csdn.net/csethcrm/article/details/51672643)    
[RabbitMQ Exchange 模式 - fanout](http://blog.csdn.net/csethcrm/article/details/51673029)   
[RabbitMQ Exchange 模式 - topic](http://blog.csdn.net/csethcrm/article/details/51673050)   
[RabbitMQ的应用场景以及基本原理介绍](http://blog.csdn.net/whoamiyang/article/details/54954780)    
[RabbitMQ进程结构分析与性能调优](http://www.cnblogs.com/purpleraintear/p/6033136.html)   

