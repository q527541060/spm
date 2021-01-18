package com.sinictek.spm;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.queryBean.ThreePointAsCloseResponse1JsonBean;
import com.sinictek.spm.model.utils.SoctekUtil;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.model.utils.TestJavaDLLService;
import com.sinictek.spm.service.SPcbService;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import com.sun.jna.WString;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import net.iharder.Base64;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.ref.Reference;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import org.springframework.messaging.support.MessageBuilder;


@SpringBootTest
@MapperScan("com.sinictek.spm.dao")
class SpmApplicationTests {

    @Autowired
    SPcbService sPcbService;

    /*@Resource
    private RocketMQTemplate rocketMQTemplate;*/


    @org.junit.jupiter.api.Test
    void TestDLL(){

        /*String str = "{\"CompData_SPI\":{\"Img\":null,\"arrPadShape\":null,\"arrPadSpec\":null,\"ArrayID\":null,\"CompID\":null,\"PCBStartTime\":null,\"PCBID\":null},\"CompData_AOIF\":{\"Img\":null,\"arrShape\":[{\"PadID\":0,\"WindowIndex\":4,\"JudgeRes\":\"NG\",\"Shape\":5,\"PosX\":0.0,\"PosY\":0.0,\"SizeX\":0.0,\"SizeY\":0.0,\"PolyPoints\":[{\"IsEmpty\":false,\"X\":61292.0,\"Y\":62570.0},{\"IsEmpty\":false,\"X\":61292.0,\"Y\":15362.0},{\"IsEmpty\":false,\"X\":15466.0,\"Y\":15362.0},{\"IsEmpty\":false,\"X\":15466.0,\"Y\":62570.0},{\"IsEmpty\":false,\"X\":61292.0,\"Y\":62570.0}]},{\"PadID\":0,\"WindowIndex\":5,\"JudgeRes\":\"NG\",\"Shape\":5,\"PosX\":0.0,\"PosY\":0.0,\"SizeX\":0.0,\"SizeY\":0.0,\"PolyPoints\":[{\"IsEmpty\":false,\"X\":60944.0,\"Y\":62828.0},{\"IsEmpty\":false,\"X\":60944.0,\"Y\":15376.0},{\"IsEmpty\":false,\"X\":16003.0,\"Y\":15376.0},{\"IsEmpty\":false,\"X\":16003.0,\"Y\":62828.0},{\"IsEmpty\":false,\"X\":60944.0,\"Y\":62828.0}]},{\"PadID\":0,\"WindowIndex\":6,\"JudgeRes\":\"Good\",\"Shape\":5,\"PosX\":0.0,\"PosY\":0.0,\"SizeX\":0.0,\"SizeY\":0.0,\"PolyPoints\":[{\"IsEmpty\":false,\"X\":60944.0,\"Y\":62200.0},{\"IsEmpty\":false,\"X\":60944.0,\"Y\":15250.0},{\"IsEmpty\":false,\"X\":16003.0,\"Y\":15250.0},{\"IsEmpty\":false,\"X\":16003.0,\"Y\":62200.0},{\"IsEmpty\":false,\"X\":60944.0,\"Y\":62200.0}]},{\"PadID\":0,\"WindowIndex\":7,\"JudgeRes\":\"NG\",\"Shape\":5,\"PosX\":0.0,\"PosY\":0.0,\"SizeX\":0.0,\"SizeY\":0.0,\"PolyPoints\":[{\"IsEmpty\":false,\"X\":61697.0,\"Y\":62577.0},{\"IsEmpty\":false,\"X\":61697.0,\"Y\":15627.0},{\"IsEmpty\":false,\"X\":15249.0,\"Y\":15627.0},{\"IsEmpty\":false,\"X\":15249.0,\"Y\":62577.0},{\"IsEmpty\":false,\"X\":61697.0,\"Y\":62577.0}]}],\"CompSpec\":{\"CompIndex\":4,\"DefectName\":\"Rotation\",\"JudgeRes\":\"NG\",\"OPCinfirmed\":\"Pass\",\"LstWindowSpec\":[{\"CompIndex\":4,\"WindowIndex\":4,\"DefectName\":\"Rotation\",\"JudgeRes\":\"NG\"},{\"CompIndex\":4,\"WindowIndex\":5,\"DefectName\":\"Rotation\",\"JudgeRes\":\"NG\"},{\"CompIndex\":4,\"WindowIndex\":6,\"DefectName\":\"Rotation\",\"JudgeRes\":\"Good\"},{\"CompIndex\":4,\"WindowIndex\":7,\"DefectName\":\"Rotation\",\"JudgeRes\":\"NG\"}]},\"ArrayIndex\":1,\"CompIndex\":4,\"CompID\":\"C3\",\"PCBStartTime\":\"2020-11-20 10:09:02\"},\"CompData_AOIB\":{\"Img\":null,\"arrShape\":null,\"CompSpec\":{\"CompIndex\":0,\"DefectName\":null,\"JudgeRes\":null,\"OPCinfirmed\":null,\"LstWindowSpec\":null},\"ArrayIndex\":0,\"CompIndex\":0,\"CompID\":null,\"PCBStartTime\":null}}";
        Object lstStr =  JSONUtils.parse(str);
        //String ArrayID= lstStr.CompData_AOIF.arrShape[0].WindowIndex;
        String Image3DPath = "\\\\127.0.0.1\\aoi_db\\20201104094629\\Component\\CD13_ 1_3_53.3ddata_BF";
       // File file = new File(Image3DPath);

        //Reference<Integer> ref = new Reference<Integer>();
        IntByReference iLength=new IntByReference();
        //Integer iLength=0;
        IntByReference arr = TestJavaDLLService.TestJavaDLL.instaneDll.get3DHeightByFilePath(Image3DPath,  iLength);
        //int i = TestJavaDLLService.TestJavaDLL.instaneDll.test(1,9);
        int[] iarr = arr.getPointer().getIntArray(0,iLength.getValue());*/
        //System.out.println("万恶的C#"+.length);
    }

    @org.junit.jupiter.api.Test
    void GetAllStatus(){
       /* ConstController.constController.iniDefaultParamSetting();
        Calendar instance = Calendar.getInstance();
        String tmpHour = ConstParam.DEFAULTSETTING_FrequencyStart+"";
        String stratTime =instance.get(instance.YEAR)+"-"+(instance.get(instance.MONTH)+1)+"-"+instance.get((instance.DAY_OF_MONTH)-1)+
                " "+tmpHour.substring(0,tmpHour.length()-2) + ":00:00";//
        String endTime = StringTimeUtils.getTimeDateToString(new Date());*/
        /*List<SStatus> statusList = sStatusMapper.getAllStatusWithLineNoLimt();
        System.out.println(JSON.toJSONString(statusList));*/
    }


    @Test
    void soctekTest(){
       /* try {
            String strSocketText1 ="<java>{\"StInputData\":" +
                    "{\"EndTime\":\"2020-12-29 01:00:00\"," +
                    "\"LineNo\":\"line001\"," +
                    "\"StartTime\":\"2020-01-01 01:00:00\"}," +
                    "\"Type\":1}";
            String strSocketText2 = "<java>{\"StInputData\":" +
                    "{\"LineNo\":\"line001\"," +
                    "\"Barcode\":\"T0110801079\"}," +
                    "\"Type\":2}";
            String strSocketText3 = "<java>" +
                    "{\"StInputData\":" +
                    "{\"ArrayID\":1," +
                    "\"Barcode\":\"T0110801079\"," +
                    "\"CompID\":\"R31\"," +
                    "\"LineNo\":\"line001\"," +
                    "\"PCBID_AOIB\":380," +
                    "\"PCBID_AOIF\":380," +
                    "\"PCBID_SPI\":2624},\"Type\":3}";
            String strResponseResult = SoctekUtil.sendByCShapeSocket(strSocketText1,"127.0.0.1",12345);

            List<ThreePointAsCloseResponse1JsonBean> threePointAsCloseResponse1JsonBean = JSONArray.parseArray(strResponseResult,ThreePointAsCloseResponse1JsonBean.class);
            String strBarcode = threePointAsCloseResponse1JsonBean.get(0).getBarcode();
            String strResult = SoctekUtil.sendByCShapeSocket(strSocketText2,"127.0.0.1",12345);
            String strResult3 = SoctekUtil.sendByCShapeSocket(strSocketText3,"127.0.0.1",12345);

            Object obj = JSON.parse(strResult);
            obj = "";
            obj = "asdf";
            //strResult = strResult.toString();   three-point as close
            //[{"Barcode":"T0110801079","LstPCB":[{"PCBID":2624,"StationID":1},{"PCBID":380,"StationID":2},{"PCBID":2473,"StationID":1}]},{"Barcode":"T0110801002","LstPCB":[{"PCBID":2483,"StationID":1}]},{"Barcode":"T0110801080","LstPCB":[{"PCBID":459,"StationID":2}]}]
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    @org.junit.jupiter.api.Test
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