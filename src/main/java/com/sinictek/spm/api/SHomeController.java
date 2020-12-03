package com.sinictek.spm.api;

import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.SDefaultsetting;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SDefaultsettingService;
import com.sinictek.spm.service.SLineService;
import com.sinictek.spm.service.SPcbService;
import com.sinictek.spm.service.SStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    SLineService sLineService;

    @Autowired
    SStatusService sStatusService;

    @GetMapping("/pcbHome")
    public ModelAndView showHome(){
        String strStartTime = "1900-01-01 00:00:00";
        //spi data
        //过程数据
        SPcb sPcb = sPcbService.getPcbListWithALLLineByDateNoGroup(strStartTime,StringTimeUtils.getTimeDateToString(new Date()));
        ConstController.constController.iniDefaultParamSetting();

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

   /* private void iniDefaultParamSetting(){

        if(ConstParam.DEFAULTSETTING_boardMachineTimeLimit==0|
                ConstParam.DEFAULTSETTING_boardMachineRefreshTime==0|
                ConstParam.DEFAULTSETTING_autoDeleteDays==0|
                ConstParam.DEFAULTSETTING_FPY==0|
                ConstParam.DEFAULTSETTING_CPK==0|
                ConstParam.DEFAULTSETTING_Product==0|
                ConstParam.DEFAULTSETTING_DefaultTop5==0|
                ConstParam.DEFAULTSETTING_autoDelete==0|
                ConstParam.DEFAULTSETTING_defaultType==""|
                ConstParam.DEFAULTSETTING_FrequencyStart==0|
                ConstParam.DEFAULTSETTING_standCPK==0|
                ConstParam.DEFAULTSETTING_autoDeleteMaxDays==0|
                ConstParam.DEFAULTSETTING_hChartColor==0|
                ConstParam.DEFAULTSETTING_backgroundColor==0|
                ConstParam.DEFAULTSETTING_passPcbYeild==0|
                ConstParam.DEFAULTSETTING_boardViewChartMove==0

        ){
            List<SDefaultsetting> lstDefaultSetting = sDefaultsettingService.selectList(null);
            if(lstDefaultSetting!=null&&lstDefaultSetting.size()>0){
                for (int i = 0; i < lstDefaultSetting.size(); i++) {
                    String strSettingName=lstDefaultSetting.get(i).getSettingName(),strSettingValue=lstDefaultSetting.get(i).getSettingValue();
                    if("boardMachineTimeLimit".equals(strSettingName)){
                        ConstParam.DEFAULTSETTING_boardMachineTimeLimit=strSettingValue==null?5:Integer.parseInt(strSettingValue);
                    }
                    if("FPY".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_FPY=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("CPK".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_CPK=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("Product".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_Product=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("DefaultTop5".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_DefaultTop5=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("defaultType".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_defaultType=strSettingValue==null?"0;1;2;3;4":strSettingValue;
                    }
                    if("boardMachineRefreshTime".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_boardMachineRefreshTime=strSettingValue==null?10:Integer.parseInt(strSettingValue);
                    }
                    if("autoDelete".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_autoDelete=strSettingValue==null?0:Integer.parseInt(strSettingValue);
                    }
                    if("autoDeleteDays".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_autoDeleteDays=strSettingValue==null?30:Integer.parseInt(strSettingValue);
                    }

                    if("Frequency-start".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_FrequencyStart=strSettingValue==null?8:Integer.parseInt(strSettingValue);
                    }
                    if("standCPK".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_standCPK=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("autoDelete-MaxDays".equals(strSettingName)){
                        ConstParam.DEFAULTSETTING_autoDeleteMaxDays=strSettingValue==null?365:Integer.parseInt(strSettingValue);
                    }
                    if("hChartColor".equals(strSettingName)){
                        ConstParam.DEFAULTSETTING_hChartColor=strSettingValue==null?0:Integer.parseInt(strSettingValue);
                    }
                    if("backgroundColor".equals(strSettingName)){
                        ConstParam.DEFAULTSETTING_backgroundColor=strSettingValue==null?0:Integer.parseInt(strSettingValue);
                    }
                    if("passPcbYeild".equals(strSettingName)){     //看板直通率标准设定值
                        ConstParam.DEFAULTSETTING_passPcbYeild= strSettingValue==null?0:Integer.parseInt(strSettingValue);
                    }
                    if("boardView-chartMove".equals(strSettingName)){     //看板动画渲染开关
                        ConstParam.DEFAULTSETTING_boardViewChartMove= strSettingValue==null?0:Integer.parseInt(strSettingValue);
                    }
                }
            }
        }


    }
*/

}
