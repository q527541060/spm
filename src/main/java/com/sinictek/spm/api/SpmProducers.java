package com.sinictek.spm.api;

/*import com.alibaba.fastjson.JSON;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.message.AddMessageReq;
import com.sinictek.spm.model.message.Message;
import com.sinictek.spm.service.SPcbService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;*/

import java.util.List;

/**
 * @Author sinictek-pd
 * @Date 2020/8/28 16:52
 * @Version 1.0
 */
/*@RestController
public class SpmProducers {
    @Autowired
    private RocketMQTemplate template;

    @Autowired
    SPcbService sPcbService;

    @RequestMapping("/producersMessage")
    @ResponseBody
    public List<SPcb> producersMessage() {
        //指定topic，tag
        AddMessageReq addMessageReq = new AddMessageReq();
        addMessageReq.setTopic("demo-topic");
        addMessageReq.setTag("tag1");
        Message<String> message = new Message<>();
        message.setId("123");
        message.setContent("测试一下");
        addMessageReq.setMessage(message);
        template.convertAndSend(addMessageReq.getTopic()+":"+addMessageReq.getTag() ,addMessageReq.getMessage());
        return sPcbService.selectList(null);
    }
}*/
