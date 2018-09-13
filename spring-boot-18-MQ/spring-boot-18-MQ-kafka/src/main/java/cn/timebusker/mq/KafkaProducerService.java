package cn.timebusker.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @DESC:KafkaProducer:消息投递
 * @author:timebusker
 * @date:2018/9/13
 */
@Service
public class KafkaProducerService {

    private final static Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void produce(String topic,String message){
        try {
            kafkaTemplate.send(topic,message);
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}
