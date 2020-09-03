package com.sinictek.spm.listenner;

/*import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;*/

/**
 * @Author sinictek-pd
 * @Date 2020/8/28 16:48
 * @Version 1.0
 */
/*@Service
@RocketMQMessageListener(topic="demo-topic",consumerGroup = "spm_producer")
public class SpmConsumers implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.err.println("接收消息:" + message);
    }
}*/
/*@Service
@RocketMQMessageListener(topic = "demo-topic", consumerGroup = "consumer-group-1")
public class RocketMQConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {

        System.out.println("-------  received:"+ message);
    }

}*/
/*
@Component
@RocketMQMessageListener(topic = "demo-topic", selectorExpression = "tag1", consumeMode = ConsumeMode.ORDERLY, consumerGroup = "${rocketmq.consumer.group}")
public class RocketMQConsumerListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("接收到消息：" + s);
    }

}*/
