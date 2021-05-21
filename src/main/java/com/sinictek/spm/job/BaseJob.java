package com.sinictek.spm.job;

import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.api.SDefaultsettingController;
import com.sinictek.spm.api.SStatusController;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.SDefaultsetting;
import com.sinictek.spm.model.SJob;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *  多任务执行类
 */
@Component
public class BaseJob {

    @Autowired
    SPcbService sPcbService;

    @Autowired
    SPadService sPadService;

    @Autowired
    SStatusService sStatusService;

    /*@Scheduled(cron = "0/2 * * * * *")
    public void doJob(){
        System.out.println("springboot thread job..." + new Date());
    }
    @Scheduled(cron = "0/2 * * * * *")
    public void doLine(){
        System.out.println("springboot thread line..."+new Date());
    }*/
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void doGc(){
        //System.out.println("----------------GC-------------");
        System.gc();
    }

    /*private void iniDefaultParamSetting(){

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
                ConstParam.DEFAULTSETTING_autoDeleteMaxDays==0
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
                }
            }
        }


    }
*/
    /***
     * 自动删除  删除多少天数据
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void doDeleteData(){
        //System.out.println("大哥这是自动删除:"+ConstParam.DEFAULTSETTING_autoDelete+"");
            /*if(ConstParam.DEFAULTSETTING_autoDeleteDays==0){
                ConstParam.DEFAULTSETTING_autoDeleteDays=35;
            }*/
            //如果系统自启动 数据库获取参数值
            if(ConstParam.DEFAULTSETTING_autoDeleteDays==0){
                ConstController.constController.iniDefaultParamSetting();
                /*SStatusController sStatusController = new SStatusController();
                sStatusController.iniDefaultParamSetting();*/
            }
        if(ConstParam.DEFAULTSETTING_autoDelete == 1){
            //String endTime = StringTimeUtils.getTimeDateToString(new Date());
            String startTime = StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-ConstParam.DEFAULTSETTING_autoDeleteDays*24);
            //String[]  strDateTime = startTime.split(" ");
            String strPadSqlName="";
            //System.out.println(new Date()+"---开始delete焊盘");
            for (int i = 1; i <ConstParam.DEFAULTSETTING_autoDeleteMaxDays; i++) {
                //拿到pad 表名
                strPadSqlName = "s_pad_"+ StringTimeUtils.addDataStrNow(Calendar.getInstance(),-ConstParam.DEFAULTSETTING_autoDeleteDays-i);
                //drop pad
                sPadService.deletePadTableWithName(strPadSqlName);
            }
            //System.out.println(new Date()+"---结束delete焊盘");
            sPcbService.delete(Condition.create().lt("inspectStarttime",startTime).last("limit 3000") );//..and("1=1  LIMIT 1")
            sStatusService.delete(Condition.create().lt("updateTime",startTime).last("limit 3000") );
            //ystem.out.println(bTure);

        }

    }

}
