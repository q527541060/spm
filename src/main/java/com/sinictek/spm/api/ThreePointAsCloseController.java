package com.sinictek.spm.api;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.annotation.LoginToken;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.SLine;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.queryBean.ThreePointAsCloseResponse1JsonBean;
import com.sinictek.spm.model.queryBean.ThreePointAsCloseResponse3JsonBean;
import com.sinictek.spm.model.utils.SoctekUtil;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SLineService;
import com.sinictek.spm.service.SPcbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @Author sinictek-pd
 * @Date 2020/11/13 10:37
 * @Version 1.0
 */
@Controller
@RequestMapping("/threePointClose")
public class ThreePointAsCloseController {
    @Autowired
    SPcbService sPcbService;
    @Autowired
    SLineService sLineService;

    @GetMapping("showThreePointClose")
    public ModelAndView threepointascloseView() {
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = false;//ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        try{
            int i = StringTimeUtils.getTimeStringToDate("2021-04-25 00:00:00").compareTo(new Date());
            if(i > 0  ){
                bCmBoxs = true;
            }else{
                bCmBoxs = false;
            };
        }catch (Exception e){
        }
        String viewName = "pubPages/threePointAsClose";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("weburl","/threePointClose/showThreePointClose?");
        //ModelAndView mv = new ModelAndView("spi/pcbLineData");
        mv.addObject("hChartColor", ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
       // mv.addObject("aoiType",aoiType);
        //ModelAndView mv = new ModelAndView("pubPages/threePointAsClose");
        return mv;
    }


    @GetMapping("showThreePointCloseNew")
    public ModelAndView threepointascloseViewNew() {
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "threePointClose";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("weburl","/threePointClose/showThreePointClose?");
        //ModelAndView mv = new ModelAndView("spi/pcbLineData");
        // mv.addObject("aoiType",aoiType);
        //ModelAndView mv = new ModelAndView("pubPages/threePointAsClose");
        return mv;
    }



    @GetMapping("getAllLine")
    @ResponseBody
    public ApiResponse getAllLine() {

        List<SLine> lstSline = sLineService.selectList(null);

        return new ApiResponse(true, null, null, lstSline);
    }

    @GetMapping("getPcbListWiththreePointAsClose")
    @ResponseBody
    public ApiResponse pcbListByLineNoAndCheckTimek(@RequestParam("inspectStarttime") String inspectStarttime, @RequestParam("inspectEndtime") String inspectEndtime, @RequestParam("lineNo") String lineNo) throws Exception{
        String strSocketText = "<java>";
        strSocketText+= "{\"StInputData\":" +
                            "{\"EndTime\":\""+ inspectEndtime +"\"," +
                             "\"LineNo\":\""+lineNo+"\"," +
                             "\"StartTime\":\""+inspectStarttime+"\"}," +
                             "\"Type\":1}";
        String strResponseResult = SoctekUtil.sendByCShapeSocket(strSocketText,"127.0.0.1",12345);
        List<ThreePointAsCloseResponse1JsonBean> threePointAsCloseResponse1JsonBean = JSONArray.parseArray(strResponseResult,ThreePointAsCloseResponse1JsonBean.class);
        int strSpiPCBID=0,strPreAoiPCBID=0,strPostAoiPCBID=0;
        for (int i = 0; i < threePointAsCloseResponse1JsonBean.size(); i++) {
            for (int j = 0; j < threePointAsCloseResponse1JsonBean.get(i).getLstPCB().size(); j++) {
                switch (threePointAsCloseResponse1JsonBean.get(i).getLstPCB().get(j).getStationID()){
                    case 1: {
                        if (strSpiPCBID < threePointAsCloseResponse1JsonBean.get(i).getLstPCB().get(j).getPCBID()) {
                            strSpiPCBID = threePointAsCloseResponse1JsonBean.get(i).getLstPCB().get(j).getPCBID();
                        }
                        break;
                    }
                    case 2: {
                        if (strPreAoiPCBID < threePointAsCloseResponse1JsonBean.get(i).getLstPCB().get(j).getPCBID()) {
                            strPreAoiPCBID = threePointAsCloseResponse1JsonBean.get(i).getLstPCB().get(j).getPCBID();
                        }
                        break;
                    }
                    case 3:{
                        if (strPostAoiPCBID < threePointAsCloseResponse1JsonBean.get(i).getLstPCB().get(j).getPCBID()) {
                            strPostAoiPCBID = threePointAsCloseResponse1JsonBean.get(i).getLstPCB().get(j).getPCBID();
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            threePointAsCloseResponse1JsonBean.get(i).setSpiPCBID(strSpiPCBID);
            threePointAsCloseResponse1JsonBean.get(i).setPreAoiPCBID(strPreAoiPCBID);
            threePointAsCloseResponse1JsonBean.get(i).setPostAoiPCBID(strPostAoiPCBID);
            strSpiPCBID=0;strPreAoiPCBID=0;strPostAoiPCBID=0;
        }
        //List<ThreePointAsCloseResponse1JsonBean>
        // List<SPcb> lstSpcb = sPcbService.selectList(Condition.create().gt("inspectStarttime", inspectStarttime).and().lt("inspectEndtime", inspectEndtime).and().eq("lineNo", lineNo));
        try{
            return new ApiResponse(true, "", "", threePointAsCloseResponse1JsonBean);

        }catch (Exception e){
        }finally {
            strResponseResult=null;
            threePointAsCloseResponse1JsonBean=null;
        }
        return new ApiResponse(true, "", "", true);

    }

    @GetMapping("getComponentListWiththreePointAsClose")
    @ResponseBody
    public ApiResponse componentListWiththreePointAsClose(
            @RequestParam("inspectStarttime") String inspectStarttime,
            @RequestParam("inspectEndtime") String inspectEndtime,
            @RequestParam("lineNo") String lineNo,
            @RequestParam("pcbID") String pcbID,
            @RequestParam("barcode") String barcode) throws Exception{

        String strSocketText = "<java>";
        strSocketText+= "{\"StInputData\":" +
                "{\"LineNo\":\""+lineNo+"\"," +
                "\"Barcode\":\""+barcode+"\"}," +
                "\"Type\":2}";

        String strResponseResult = SoctekUtil.sendByCShapeSocket(strSocketText,"127.0.0.1",12345);
        List<Object> lstStr = (List<Object>)JSONUtils.parse(strResponseResult);
        try{
            return new ApiResponse(true, "", "", lstStr);

        }catch (Exception e){
        }finally {
            strSocketText=null;
            strResponseResult=null;
            lstStr=null;
        }
        return new ApiResponse(true, "", "", lstStr);

    }

    @GetMapping("getComponentPcsInfoWiththreePointAsClose")
    @ResponseBody
    public ApiResponse componentPcsInfoWiththreePointAsClose(
            @RequestParam("ArrayID") String ArrayID,
            @RequestParam("Barcode") String Barcode,
            @RequestParam("CompID") String CompID,
            @RequestParam("LineNo") String LineNo,
            @RequestParam("PCBID_AOIB") String PCBID_AOIB,
            @RequestParam("PCBID_AOIF") String PCBID_AOIF,
            @RequestParam("PCBID_SPI") String PCBID_SPI) throws Exception{
        String strSocketText = "<java>" +
                "{\"StInputData\":{" +
                    "\"ArrayID\":"+ArrayID+"," +
                    "\"Barcode\":\""+Barcode+"\"," +
                    "\"CompID\":\""+CompID+"\"," +
                    "\"LineNo\":\""+LineNo+"\"," +
                    "\"PCBID_AOIB\":"+PCBID_AOIB+"," +
                    "\"PCBID_AOIF\":"+PCBID_AOIF+"," +
                    "\"PCBID_SPI\":"+PCBID_SPI+"}," +
                "\"Type\":3}";
        String strResponseResult = SoctekUtil.sendByCShapeSocket(strSocketText,"127.0.0.1",12345);
        //ThreePointAsCloseResponse3JsonBean threePointAsCloseResponse3JsonBean= (ThreePointAsCloseResponse3JsonBean)JSONUtils.parse(strResponseResult);
        Object obj= JSONUtils.parse(strResponseResult);
        try{
            return new ApiResponse(true,"",obj);
        }catch (Exception e){
            obj=null;
            strResponseResult=null;
            strSocketText=null;
        }
        return new ApiResponse(true,"",obj);
    }

    @GetMapping("showTest")
    public ModelAndView showTestJson(){
        ModelAndView mv = new ModelAndView("text");
        return mv;
    }
    @GetMapping("getTestJson")
    @ResponseBody
    public ApiResponse getTestJson(){
        String str = "{\"CompData_SPI\":{\"Img\":null,\"arrPadShape\":null,\"arrPadSpec\":null,\"ArrayID\":null,\"CompID\":null,\"PCBStartTime\":null,\"PCBID\":null},\"CompData_AOIF\":{\"Img\":null,\"arrShape\":[{\"PadID\":0,\"WindowIndex\":4,\"JudgeRes\":\"NG\",\"Shape\":5,\"PosX\":0.0,\"PosY\":0.0,\"SizeX\":0.0,\"SizeY\":0.0,\"PolyPoints\":[{\"IsEmpty\":false,\"X\":61292.0,\"Y\":62570.0},{\"IsEmpty\":false,\"X\":61292.0,\"Y\":15362.0},{\"IsEmpty\":false,\"X\":15466.0,\"Y\":15362.0},{\"IsEmpty\":false,\"X\":15466.0,\"Y\":62570.0},{\"IsEmpty\":false,\"X\":61292.0,\"Y\":62570.0}]},{\"PadID\":0,\"WindowIndex\":5,\"JudgeRes\":\"NG\",\"Shape\":5,\"PosX\":0.0,\"PosY\":0.0,\"SizeX\":0.0,\"SizeY\":0.0,\"PolyPoints\":[{\"IsEmpty\":false,\"X\":60944.0,\"Y\":62828.0},{\"IsEmpty\":false,\"X\":60944.0,\"Y\":15376.0},{\"IsEmpty\":false,\"X\":16003.0,\"Y\":15376.0},{\"IsEmpty\":false,\"X\":16003.0,\"Y\":62828.0},{\"IsEmpty\":false,\"X\":60944.0,\"Y\":62828.0}]},{\"PadID\":0,\"WindowIndex\":6,\"JudgeRes\":\"Good\",\"Shape\":5,\"PosX\":0.0,\"PosY\":0.0,\"SizeX\":0.0,\"SizeY\":0.0,\"PolyPoints\":[{\"IsEmpty\":false,\"X\":60944.0,\"Y\":62200.0},{\"IsEmpty\":false,\"X\":60944.0,\"Y\":15250.0},{\"IsEmpty\":false,\"X\":16003.0,\"Y\":15250.0},{\"IsEmpty\":false,\"X\":16003.0,\"Y\":62200.0},{\"IsEmpty\":false,\"X\":60944.0,\"Y\":62200.0}]},{\"PadID\":0,\"WindowIndex\":7,\"JudgeRes\":\"NG\",\"Shape\":5,\"PosX\":0.0,\"PosY\":0.0,\"SizeX\":0.0,\"SizeY\":0.0,\"PolyPoints\":[{\"IsEmpty\":false,\"X\":61697.0,\"Y\":62577.0},{\"IsEmpty\":false,\"X\":61697.0,\"Y\":15627.0},{\"IsEmpty\":false,\"X\":15249.0,\"Y\":15627.0},{\"IsEmpty\":false,\"X\":15249.0,\"Y\":62577.0},{\"IsEmpty\":false,\"X\":61697.0,\"Y\":62577.0}]}],\"CompSpec\":{\"CompIndex\":4,\"DefectName\":\"Rotation\",\"JudgeRes\":\"NG\",\"OPCinfirmed\":\"Pass\",\"LstWindowSpec\":[{\"CompIndex\":4,\"WindowIndex\":4,\"DefectName\":\"Rotation\",\"JudgeRes\":\"NG\"},{\"CompIndex\":4,\"WindowIndex\":5,\"DefectName\":\"Rotation\",\"JudgeRes\":\"NG\"},{\"CompIndex\":4,\"WindowIndex\":6,\"DefectName\":\"Rotation\",\"JudgeRes\":\"Good\"},{\"CompIndex\":4,\"WindowIndex\":7,\"DefectName\":\"Rotation\",\"JudgeRes\":\"NG\"}]},\"ArrayIndex\":1,\"CompIndex\":4,\"CompID\":\"C3\",\"PCBStartTime\":\"2020-11-20 10:09:02\"},\"CompData_AOIB\":{\"Img\":null,\"arrShape\":null,\"CompSpec\":{\"CompIndex\":0,\"DefectName\":null,\"JudgeRes\":null,\"OPCinfirmed\":null,\"LstWindowSpec\":null},\"ArrayIndex\":0,\"CompIndex\":0,\"CompID\":null,\"PCBStartTime\":null}}";
        Object lstStr =  JSONUtils.parse(str);
        return new ApiResponse(true,"",lstStr);
    }
}
