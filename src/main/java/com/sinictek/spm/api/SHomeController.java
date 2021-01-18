package com.sinictek.spm.api;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.model.*;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @Author sinictek-pd
 * @Date 2020/6/20 12:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/Home")
public class SHomeController {

    @Autowired
    SPcbService sPcbService;

    @Autowired
    APcbService aPcbService;

    @Autowired
    SLineService sLineService;
    @Autowired
    ALineService aLineService;

    @Autowired
    SStatusService sStatusService;

    @Autowired
    AStatusService aStatusService;

    //private Logger logger = LoggerFactory.getLogger(SHomeController.class);

    @GetMapping("/pcbHome")
    public ModelAndView showHome(){
        ConstController.constController.iniDefaultParamSetting();
        String strStartTime = "1900-01-01 00:00:00";
        //spi data
        //过程数据
        SPcb sPcb = sPcbService.getPcbListWithALLLineByDateNoGroup(strStartTime,StringTimeUtils.getTimeDateToString(new Date()));
        //APcb aPrePcb = aPcbService.getPcbListWithALLLineByDateNoGroup(strStartTime,StringTimeUtils.getTimeDateToString(new Date()),"1");
        //APcb aPostPcb = aPcbService.getPcbListWithALLLineByDateNoGroup(strStartTime,StringTimeUtils.getTimeDateToString(new Date()),"2");
        //活动线体
        int iStatusCount = sStatusService.getAllStatusWithLineNoLimt("").size();
        if(iStatusCount>0){
            int iOneCount = sStatusService.getAllStatusWithLineNoLimt("0").size();
            int iTwoCount = sStatusService.getAllStatusWithLineNoLimt("1").size();
            if(iOneCount>iTwoCount){
                iStatusCount = iOneCount;
            }else{
                iStatusCount = iTwoCount;
            }
        }

        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "pcbHome";
        if(bCmBoxs){
        }else{
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);

        mv.addObject("boardMachineRefreshTime",ConstParam.DEFAULTSETTING_boardMachineRefreshTime);
        mv.addObject("preAoi_lineCount",aLineService.selectCount(Condition.create().eq("aoiMode","1")));
        mv.addObject("postAoi_lineCount",aLineService.selectCount(Condition.create().eq("aoiMode","2")));
        mv.addObject("spi_dataCount",sPcb.getTotalpadCount());
        mv.addObject("spi_barcodeCount",sPcb.getTotal());
        mv.addObject("spi_lineCount",iStatusCount);
                //sLineService.selectCount(Condition.create().gt("updateDate",StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-24))));
        mv.addObject("spi_fovCount",0);
        mv.addObject("spi_pcbCount",0);
        mv.addObject("spi_componentCount",0);
        mv.addObject("hChartColor",ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        return  mv;
    }


    @GetMapping("/homeTimeInterver")
    @ResponseBody
    public ApiResponse homeInterver(){

        //logger.info("just start");
        //获取到时间设置区间
        int iSettingHour = ConstParam.DEFAULTSETTING_FrequencyStart;
        Calendar instance = Calendar.getInstance();
        String stratTime=null;
        String endTime=null;
        if(iSettingHour>=100){
            String tmpHour = ConstParam.DEFAULTSETTING_FrequencyStart+"";
             stratTime =instance.get(instance.YEAR)+"-"+(instance.get(instance.MONTH)+1)+"-"+(instance.get(instance.DAY_OF_MONTH)-1)+
                    " "+tmpHour.substring(0,tmpHour.length()-2) + ":00:00";//
             endTime = StringTimeUtils.getTimeDateToString(new Date());
        }else{
             stratTime =instance.get(instance.YEAR)+"-"+(instance.get(instance.MONTH)+1)+"-"+instance.get(instance.DAY_OF_MONTH)+
                    " "+ConstParam.DEFAULTSETTING_FrequencyStart + ":00:00";//
             endTime = StringTimeUtils.getTimeDateToString(new Date());
        }
        //设备状态报警  和   良率报警
        int iSpiLineErrorCount = 0;
        List<SStatus> spiStatusList=sStatusService.selectList(Condition.create()
                .groupBy("lineNo").orderBy(true,"lineNo",false)); //.orderBy(true,"lineNo",false)
        if(spiStatusList!=null && spiStatusList.size()>0){
            for (int i = 0; i < spiStatusList.size(); i++) {
                if(StringUtils.equals("1",spiStatusList.get(i).getError()) ){
                    iSpiLineErrorCount++;
                }
            }
        }
        int iAoiPreLineErrorCount = 0;
        List<AStatus> aPreStatusList=aStatusService.selectList(Condition.create() .eq("aoiMode",1)
                .groupBy("lineNo").orderBy(true,"lineNo",false));
        if(aPreStatusList!=null && aPreStatusList.size()>0){
            for (int i = 0; i < aPreStatusList.size(); i++) {
                if(aPreStatusList.get(i).getError()==1){
                    iAoiPreLineErrorCount++;
                }
            }
        }
        int iAoiPostLineErrorCount = 0;
        List<AStatus> aPostStatusList=aStatusService.selectList(Condition.create() .eq("aoiMode",2)
                .groupBy("lineNo").orderBy(true,"lineNo",false));
        if(aPostStatusList!=null && aPostStatusList.size()>0){
            for (int i = 0; i < aPostStatusList.size(); i++) {
                if(aPostStatusList.get(i).getError()==1){
                    iAoiPostLineErrorCount++;
                }
            }
        }
        int iSpiYeildCount =0;
        int iPreAoiYeildCount =0;
        int iPostAoiYeildCount =0;
        int iDEFAULTSETTING_passPcbYeild = ConstParam.DEFAULTSETTING_passPcbYeild; // 直通率标准
        List<SPcb> lstSpiPcb =  sPcbService.getPcbListWithALLLine(stratTime,endTime);
        if(lstSpiPcb!=null && lstSpiPcb.size()>0){
            for (int i = 0; i < lstSpiPcb.size(); i++) {
                if( Double.parseDouble(lstSpiPcb.get(i).getGoodPcbYeild() ) < iDEFAULTSETTING_passPcbYeild){
                    iSpiYeildCount++;  //计算出直通率小于标准值报警值加1
                }
            }
        }
        List<APcb> lstAoiPcb =  aPcbService.getPcbListWithALLLineNoAoiMode(stratTime,endTime);
        if(lstAoiPcb!=null && lstAoiPcb.size()>0){
            for (int i = 0; i < lstAoiPcb.size(); i++) {
                if(lstAoiPcb.get(i).getAoiMode()!=null){
                    if(lstAoiPcb.get(i).getAoiMode() == 2){
                        if( Double.parseDouble(lstAoiPcb.get(i).getGoodPcbYeildAoi() ) < iDEFAULTSETTING_passPcbYeild){
                            iPreAoiYeildCount++;  //计算出PreAoi直通率小于标准值报警值加1
                        }
                    }else{
                        if( Double.parseDouble(lstAoiPcb.get(i).getGoodPcbYeildAoi() ) < iDEFAULTSETTING_passPcbYeild){
                            iPostAoiYeildCount++;  //计算出PostAou直通率小于标准值报警值加1
                        }
                    }
                }

            }
        }
        Map<String,Integer> hashMap = new HashMap<String,Integer>();
        hashMap.put("iSpiLineErrorCount",iSpiLineErrorCount);
        hashMap.put("iAoiPreLineErrorCount",iAoiPreLineErrorCount);
        hashMap.put("iAoiPostLineErrorCount",iAoiPostLineErrorCount);
        //modelMap.put("iSpiLineErrorCount",iSpiLineErrorCount);
        hashMap.put("iSpiYeildCount",iSpiYeildCount);
        hashMap.put("iPreAoiYeildCount",iPreAoiYeildCount);
        hashMap.put("iPostAoiYeildCount",iPostAoiYeildCount);
        //logger.info("just end");
        return new ApiResponse(true,null,hashMap);
    }

}
