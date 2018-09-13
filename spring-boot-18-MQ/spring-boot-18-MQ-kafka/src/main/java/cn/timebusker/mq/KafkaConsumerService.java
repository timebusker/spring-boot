package cn.timebusker.mq;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @DESC:KafkaConsumerService:消息消费
 * @author:timebusker
 * @date:2018/9/13
 */
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = {"test"})
    public void consume(String message){
        System.out.println(message);
    }
}
