package com.sinictek.spm.model.ConstClasses;

import com.sinictek.spm.model.SDefaultsetting;
import com.sinictek.spm.service.SDefaultsettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author sinictek-pd
 * @Date 2020/9/8 11:06
 * @Version 1.0
 */
@Component
public class ConstController {

    @Autowired
    SDefaultsettingService sDefaultsettingService;

    @Autowired
    MessageSource messageSource;

    public static ConstController constController;

    @PostConstruct
    public void init(){
        constController = this;
        constController.sDefaultsettingService = this.sDefaultsettingService;
        //iniDefaultParamSetting();
    }
    public void iniDefaultParamSetting(){

        if(
               ConstParam.DEFAULTSETTING_boardMachineTimeLimit==0||
                ConstParam.DEFAULTSETTING_boardMachineRefreshTime==0||
                ConstParam.DEFAULTSETTING_autoDeleteDays==0||
                ConstParam.DEFAULTSETTING_FPY==0||
                ConstParam.DEFAULTSETTING_CPK==0||
                ConstParam.DEFAULTSETTING_Product==0||
                ConstParam.DEFAULTSETTING_DefaultTop5==0||
                ConstParam.DEFAULTSETTING_autoDelete==0||
                ConstParam.DEFAULTSETTING_defaultType==""||
                ConstParam.DEFAULTSETTING_FrequencyStart==0||
                ConstParam.DEFAULTSETTING_standCPK==0||
                ConstParam.DEFAULTSETTING_autoDeleteMaxDays==0||
                ConstParam.DEFAULTSETTING_hChartColor==0||
                ConstParam.DEFAULTSETTING_backgroundColor==0||
                ConstParam.DEFAULTSETTING_passPcbYeild==0||
                ConstParam.DEFAULTSETTING_boardViewChartMove==0||
                ConstParam.DEFAULTSETTING_showPad2DImageMode==0
        ){
            List<SDefaultsetting> lstDefaultSetting = constController.sDefaultsettingService.selectList(null);
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
                        ConstParam.DEFAULTSETTING_standCPK=strSettingValue==null?1:Double.parseDouble(strSettingValue);
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
                        ConstParam.DEFAULTSETTING_boardViewChartMove= strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }

                    if("showPad2DImageMode".equals(strSettingName)){     //看图方式
                        ConstParam.DEFAULTSETTING_showPad2DImageMode= strSettingValue==null?0:Integer.parseInt(strSettingValue);
                    }
                }
            }
        }


    }

    public String getStringByLocalContextHolder(String souce){

        return  messageSource.getMessage(souce,null, LocaleContextHolder.getLocale());

    }

}
