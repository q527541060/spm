package com.sinictek.spm;


import com.alibaba.fastjson.JSON;
import com.sinictek.spm.dao.SPadMapper;
import com.sinictek.spm.dao.SStatusMapper;
import com.sinictek.spm.dao.SUserMapper;
import com.sinictek.spm.model.SPad;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.SStatus;
import com.sinictek.spm.model.SUser;
import com.sinictek.spm.model.message.AddMessageReq;
import com.sinictek.spm.model.message.Message;
import com.sinictek.spm.service.SPcbService;
import com.sinictek.spm.service.SStatusService;
import com.sinictek.spm.service.SUserService;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
@MapperScan("com.sinictek.spm.dao")
class SpmApplicationTests {

    @Autowired
    SPcbService sPcbService;

    /*@Resource
    private RocketMQTemplate rocketMQTemplate;*/



    @Test
    void GetAllStatus(){
        /*List<SStatus> statusList = sStatusMapper.getAllStatusWithLineNoLimt();
        System.out.println(JSON.toJSONString(statusList));*/
    }


    @Test
    void contextLoads() {
    }

   /* @Test
    void syncSendMessageTest() throws InterruptedException {
        //发送同步消息
        Message<String> message = new Message<>();
        message.setId("123");
        message.setContent("测试一下");
        rocketMQTemplate.asyncSend("topic2", message, new SendCallback() {
            // 实现消息发送成功的后续处理
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }
            // 实现消息发送失败的后续处理
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }
        });
        // 让主线程睡眠10秒
        Thread.currentThread().sleep(10000);
    }

    @Test
    void sendMessageTest() throws InterruptedException {
        //指定topic，tag
        AddMessageReq addMessageReq = new AddMessageReq();
        addMessageReq.setTopic("demo-topic");
        addMessageReq.setTag("demo-topic");
        Message<String> message = new Message<>();
        message.setId("123");
        message.setContent("进来测试了");
        addMessageReq.setMessage(message);
        System.out.println(rocketMQTemplate.getProducer().getNamesrvAddr());
        rocketMQTemplate.convertAndSend(addMessageReq.getTopic() + ":" + addMessageReq.getTag(), addMessageReq.getMessage());
        // 让主线程睡眠10秒
        //Thread.currentThread().sleep(10000);
    }

    @Test
    void sendMessage(){
        *//*AddMessageReq addMessageReq = new AddMessageReq();
        addMessageReq.setTopic("demo-topic");
        addMessageReq.setTag("tag1");
        Message<String> message = new Message<>();
        message.setId("123");
        message.setContent("测试一下");
        addMessageReq.setMessage(message);*//*
        //rocketMQTemplate.convertAndSend("demo-topic", "aaa");
        rocketMQTemplate.send("demo-topic", MessageBuilder.withPayload("测试一下").build());
        //;
        System.out.println("发送成功!"+rocketMQTemplate.getProducer().getNamesrvAddr());
    }
*/
    /*@BeforeEach
    void testBefore(){
        System.out.printf("测试开始!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @AfterEach
    void testAfter(){
        System.out.printf("测试结束!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }*/








}