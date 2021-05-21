package com.sinictek.spm.api;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.annotation.LoginToken;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.SRealtimeautorefresh;
import com.sinictek.spm.model.SStatus;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.SoctekUtil;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SRealtimeautorefreshService;
import com.sinictek.spm.service.SStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2021-03-25
 */
@Controller
@RequestMapping("/realTime")
public class SRealtimeautorefreshController {

    @Resource
    SRealtimeautorefreshService sRealtimeautorefreshService;

    @Autowired
    SStatusService sStatusService;

    @GetMapping("/showRealTime")
    public ModelAndView showSpiRealTime(){

        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "realTime";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire"; //cache
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("hChartColor", ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        mv.addObject("weburl","/sRealTime?"
        );
        return mv;

    }


    @PostMapping("/insertSpiRealTimeAutoRefresh")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse insertSpiRealTimeAutoRefresh(@RequestBody SRealtimeautorefresh sRealtimeautorefresh){
        String message=null;
        boolean bIsSuccess = false;
        if(sRealtimeautorefresh!=null){

            try{
                bIsSuccess = sRealtimeautorefreshService.insert(sRealtimeautorefresh);
            }catch (Exception e){
                message = e.getMessage();
            }finally {
                sRealtimeautorefresh = null;
            }
        }else{
            message = "SRealtimeautorefresh_IS_NULL";
        }
        return  new ApiResponse(bIsSuccess,message,"");
    }


    /**
     *  从C#socket服务获取realTime信息;
     * @param startTime
     * @param endTime
     * @param lineNo
     * @return
     */
    @GetMapping("/getRealTimeInfoSocketServer")
    @ResponseBody
    public ApiResponse getRealTimeInfoByCShapeSocketServer(@RequestParam("startTime")String startTime,
                                                           @RequestParam("endTime")String endTime,
                                                           @RequestParam("lineNo")String lineNo
                                                           ){

        String callSocketResult = null;
        boolean bIsApiSuccess = false;
        String message = null;
        callSocketResult = "";//"[{\"LstPCBData\":[{\"ID\":1,\"JobName\":\"AOI Demo Board\",\"JobVersion\":\"202104021420530000600004\",\"LineNo\":\"SMT01\",\"LaneNo\":0,\"PadGroup\":\"963,4328,4333,4403,4415\",\"PCBIDs\":\"589836,\",\"PCBBarcodes\":\"66ab122,\",\"PCBStartTimes\":\"2021-04-08 11:48:38,\",\"XVal\":[73.497279016319254],\"RealTimeType\":0,\"XAverage\":73.497279016319254,\"XSigma\":36.713484625703529,\"RSigma\":0.0,\"Max\":106.178180011616,\"Min\":41.510444226015295,\"R\":64.667735785600712,\"PointArea\":\"+C\",\"UpOrDown\":0,\"Judge\":0,\"iShowSolutionsTime\":0,\"iCountTime\":1,\"CPU\":1.1485581957454651,\"CPL\":0.213339224500894,\"CP\":0.68094871012317948,\"CPK\":0.213339224500894,\"Ca\":0.0,\"XUCL\":183.63773289342984,\"XCL\":73.497279016319254,\"XLCL\":-36.643174860791333,\"RUCL\":211.26949281155751,\"RCL\":64.667735785600712,\"RLCL\":0.0,\"Source\":0,\"MESDataPosted\":false,\"MESAlarmPosted\":false},{\"ID\":2,\"JobName\":\"AOI Demo Board\",\"JobVersion\":\"202104021420530000600004\",\"LineNo\":\"SMT01\",\"LaneNo\":0,\"PadGroup\":\"963,4328,4333,4403,4415\",\"PCBIDs\":\"589835,\",\"PCBBarcodes\":\"123abc,\",\"PCBStartTimes\":\"2021-04-08 11:46:41,\",\"XVal\":[76.942931560413669],\"RealTimeType\":0,\"XAverage\":76.942931560413669,\"XSigma\":29.211845950566925,\"RSigma\":0.0,\"Max\":103.054563474609,\"Min\":40.1496635473938,\"R\":62.9048999272152,\"PointArea\":\"+C\",\"UpOrDown\":1,\"Judge\":0,\"iShowSolutionsTime\":0,\"iCountTime\":1,\"CPU\":1.4041913983323389,\"CPL\":0.30744298741005305,\"CP\":0.855817192871196,\"CPK\":0.30744298741005305,\"Ca\":0.0,\"XUCL\":164.57846941211443,\"XCL\":76.942931560413669,\"XLCL\":-10.692606291287106,\"RUCL\":205.51030806221203,\"RCL\":62.9048999272152,\"RLCL\":0.0,\"Source\":0,\"MESDataPosted\":false,\"MESAlarmPosted\":false},{\"ID\":3,\"JobName\":\"AOI Demo Board\",\"JobVersion\":\"202104021420530000600004\",\"LineNo\":\"SMT01\",\"LaneNo\":0,\"PadGroup\":\"963,4328,4333,4403,4415\",\"PCBIDs\":\"589834,\",\"PCBBarcodes\":\",\",\"PCBStartTimes\":\"2021-04-08 11:46:02,\",\"XVal\":[68.2294585601471],\"RealTimeType\":0,\"XAverage\":68.2294585601471,\"XSigma\":42.601026295325241,\"RSigma\":0.0,\"Max\":104.57674749582499,\"Min\":19.658587092284797,\"R\":84.918160403540185,\"PointArea\":\"-C\",\"UpOrDown\":-1,\"Judge\":0,\"iShowSolutionsTime\":0,\"iCountTime\":1,\"CPU\":1.0310435600492607,\"CPL\":0.14263708448222273,\"CP\":0.58684032226574168,\"CPK\":0.14263708448222273,\"Ca\":0.0,\"XUCL\":196.03253744612283,\"XCL\":68.2294585601471,\"XLCL\":-59.573620325828628,\"RUCL\":277.42763003836575,\"RCL\":84.918160403540185,\"RLCL\":0.0,\"Source\":0,\"MESDataPosted\":false,\"MESAlarmPosted\":false}],\"RuleMessage\":\"---message--\",\"Config\":{\"LineNo\":\"SMT01\",\"Index\":0,\"RealTimeVersion\":\"\",\"JobName\":\"AOI Demo Board\",\"JobVersion\":\"202104021420530000600004\",\"LaneNo\":0,\"PadGroupType\":\"Select Pad\",\"PadGroup\":\"963,4328,4333,4403,4415\",\"Pads\":\"963,4328,4333,4403,4415\",\"PCBSubGroupCount\":1,\"blnGetPCBByTime\":false,\"GetPCBTimeInterval\":2.0,\"GetPCBCount\":1,\"SubGroupCPKFormule\":1,\"RealTimeType\":0,\"PadGroupDetailList\":[{\"ComponentID\":\"\",\"PinName\":\"0\",\"ArraryID\":\"0\",\"PackageType\":\"\",\"PadID\":\"963\"},{\"ComponentID\":\"C287\",\"PinName\":\"0\",\"ArraryID\":\"0\",\"PackageType\":\"\",\"PadID\":\"4328\"},{\"ComponentID\":\"C287\",\"PinName\":\"0\",\"ArraryID\":\"0\",\"PackageType\":\"\",\"PadID\":\"4333\"},{\"ComponentID\":\"R281\",\"PinName\":\"0\",\"ArraryID\":\"0\",\"PackageType\":\"\",\"PadID\":\"4403\"},{\"ComponentID\":\"R281\",\"PinName\":\"0\",\"ArraryID\":\"0\",\"PackageType\":\"\",\"PadID\":\"4415\"}],\"Title\":\"Area\",\"ShowUSLAndLSL\":false,\"ShowCLSigma\":false,\"LSL\":50.0,\"MSL\":1.0,\"USL\":200.0,\"SLUnit\":\"%\",\"Rule\":\"R8,R7,R6,R5,R4,R3,R2,R1,\",\"Checked\":0,\"ClearRun\":true,\"RunRule\":0,\"CL\":0.0,\"UCL\":0.0,\"LCL\":0.0,\"CPKThresh\":1.33,\"CPKTimes\":1,\"AutoCL\":true,\"CLAlgorithm\":1,\"arrPadValueGroupPCB\":[41.510444226015295,41.9076459390267,104.392845888619,106.178180011616,40.1496635473938,66.9967410021099,97.570758217542,103.054563474609,19.658587092284797,45.275983861834604,103.406515790644,104.57674749582499],\"PCBDataXBarSigma\":33.306074752897921,\"PCBDataXBarLCL\":-27.028334546400416,\"PCBDataXBarCL\":72.889889712293339,\"PCBDataXBarUCL\":172.80811397098711,\"PCBDataRSigma\":12.232272371810772,\"PCBDataRLCL\":0.0,\"PCBDataRCL\":70.830265372118689,\"PCBDataRUCL\":182.3171030678335,\"PCBDataXBarCPK\":0.22908623408909032,\"PCBDataXBarAvgCPK\":0.22113976546438993,\"RefreshTime\":20,\"UserUCL\":-999.0,\"UserLCL\":-999.0,\"dtLastUpdateTime\":\"2021-04-08T11:48:38\",\"blnNoNGPCB\":false,\"blnPostData\":false,\"Rule12DefectTypeCode\":-1,\"Rule9Type\":false,\"Rule9Hour\":1,\"Rule11Type\":false,\"Rule11Hour\":1}}]";
        //获取设备机器IV4 IP地址
        //if(false) {
            if (StringUtils.isEmpty(lineNo) == false) {
                //String ip  = null;
                SStatus sStatus = sStatusService.selectOne(Condition.create().eq("lineNo", lineNo).ge("updateTime", startTime).le("updateTime", endTime).orderBy("updateTime",false).last("limit 1"));
                if (sStatus != null) {
                    String ip = sStatus.getIpAddress();
                    String content = "{\"StartTime\":\"" + startTime + "\",\"EndTime\":\" " + endTime + " \"}";
                    try {
                        callSocketResult = SoctekUtil.sendByCShapeSocket(content, ip, 20800);
                        bIsApiSuccess = true;
                    } catch (Exception e) {
                        bIsApiSuccess = false;
                        message = e.getMessage();
                    }


                } else {
                    bIsApiSuccess = false;
                    message = "not find status ! lineNo:" + lineNo;

                }
            } else {
                bIsApiSuccess = false;
            }

       // }

        return  new ApiResponse(bIsApiSuccess,message,callSocketResult==""?null:JSONUtils.parse(callSocketResult));
    }

}

