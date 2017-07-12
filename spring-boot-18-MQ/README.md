### [消息队列实践](https://github.com/timebusker/spring-boot/tree/master/spring-boot-18-MQ/)  

- #### Message Broker与AMQP简介
  + Message Broker是一种消息验证、传输、路由的架构模式，其设计目标主要应用于下面这些场景：
     * 消息路由到一个或多个目的地  
	 * 消息转化为其他的表现方式  
	 * 执行消息的聚集、消息的分解，并将结果发送到他们的目的地，然后重新组合相应返回给消息用户  
	 * 调用Web服务来检索数据  
	 * 响应事件或错误  
	 * 使用发布-订阅模式来提供内容或基于主题的消息路由  
	 
  + AMQP是Advanced Message Queuing Protocol的简称，它是一个面向消息中间件的开放式标准应用层协议。AMQP定义了这些特性：
     * 消息方向  
     * 消息队列  
     * 消息路由（包括：点到点和发布-订阅模式）  	 
	 * 可靠性  
	 * 安全性  
	 
- #### 队列（Queue）和主题（Topic）区别  
  + ##### 点对点（point-to-point，简称PTP）Queue消息传递模型  
    * 通过该消息传递模型，一个应用程序（即消息生产者）可以向另外一个应用程序（即消息消费者）发送消息。
	在此传递模型中，消息目的地类型是队列（即Destination接口实现类实例由Session接口实现类实例通过调用其
	createQueue方法并传入队列名称而创建）。消息首先被传送至消息服务器端特定的队列中，然后从此对列中将消
	息传送至对此队列进行监听的某个消费者。同一个队列可以关联多个消息生产者和消息消费者，但一条消息仅能传
	递给一个消息消费者。如果多个消息消费者正在监听队列上的消息，JMS消息服务器将根据“先来者优先”的原则确定
	由哪个消息消费者接收下一条消息。如果没有消息消费者在监听队列，消息将保留在队列中，直至消息消费者连接到
	队列为止。这种消息传递模型是传统意义上的懒模型或轮询模型。在此模型中，消息不是自动推动给消息消费者的，
	而是要由消息消费者从队列中请求获得。   
  + ##### 发布/订阅（publish/subscribe，简称pub/sub）Topic消息传递模型
    * 通过该消息传递模型，应用程序能够将一条消息发送给多个消息消费者。在此传送模型中，消息目的地类型是主题
	（即Destination接口实现类实例由Session接口实现类实例通过调用其createTopic方法并传入主题名称而创建）。消
	息首先由消息生产者发布至消息服务器中特定的主题中，然后由消息服务器将消息传送至所有已订阅此主题的消费者。
	主题目标也支持长期订阅。长期订阅表示消费者已注册了主题目标，但在消息到达目标时该消费者可以处于非活动状态。
	当消费者再次处于活动状态时，将会接收该消息。如果消费者均没有注册某个主题目标，该主题只保留注册了长期订阅的
	非活动消费者的消息。与PTP消息传递模型不同，pub/sub消息传递模型允许多个主题订阅者接收同一条消息。JMS一直保留
	消息，直至所有主题订阅者都接收到消息为止。pub/sub消息传递模型基本上是一个推模型。在该模型中，消息会自动广播，
	消息消费者无须通过主动请求或轮询主题的方法来获得新的消息。  
  + ##### Queue与Topic区别   
  
  | 类型     |    Topic | Queue  |  
  | :--------: | :--------| :------- |  
  | **概要**  | Publish Subscribe messaging 发布订阅消息 |  Point-to-Point 点对点   |
  | **有无状态**     |   topic数据默认不落地，是无状态的  |  Queue数据默认会在mq服务器上以文件形式保存，比如Active MQ一般保存在$AMQ_HOME\data\kr-store\data下面。也可以配置成DB存储  |  
  | **完整性保障**      |    并不保证publisher发布的每条数据Subscriber都能接受到  | Queue保证每条数据都能被receiver接收  |  
  | **消息是否会丢失**      |   一般来说publisher发布消息到某一个topic时，只有正在监听该topic地址的sub能够接收到消息；如果没有sub在监听，该topic就丢失了 | Sender发送消息到目标Queue，receiver可以异步接收这个Queue上的消息。Queue上的消息如果暂时没有receiver来取，也不会丢失  |  
  | **消息发布接收策略**      |    一对多的消息发布接收策略，监听同一个topic地址的多个sub都能收到publisher发送的消息。Sub接收完通知mq服务器  | 一对一的消息发布接收策略，一个sender发送的消息，只能有一个receiver接收。receiver接收完后，通知mq服务器已接收，mq服务器对queue里的消息采取删除或其他操作  |   
  
  
  
- #### [整合ActiveMQ示例](https://github.com/timebusker/spring-boot/tree/master/spring-boot-18-MQ/spring-boot-18-MQ-activemq/)

- #### [整合RabbitMQ示例](https://github.com/timebusker/spring-boot/tree/master/spring-boot-18-MQ/spring-boot-18-MQ-rabbitmq/)

### 相关文章

[Kafka、RabbitMQ、RocketMQ消息中间件的对比—— 消息发送性能](https://yq.aliyun.com/articles/25385)  
[MQ选型对比RabbitMQ RocketMQ ActiveMQ Kafka](http://blog.csdn.net/oMaverick1/article/details/51331004)  

