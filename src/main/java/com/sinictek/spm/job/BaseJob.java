package com.sinictek.spm.job;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.api.SDefaultsettingController;
import com.sinictek.spm.api.SStatusController;
import com.sinictek.spm.model.APcb;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.SDefaultsetting;
import com.sinictek.spm.model.SJob;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *  多任务执行类
 */
@Component
@Log4j2
public class BaseJob {

    @Autowired
    SPcbService sPcbService;

    @Autowired
    SPadService sPadService;

    @Autowired
    SStatusService sStatusService;

    @Autowired
    APcbService aPcbService;

    @Autowired
    AComponentService aComponentService;

    @Autowired
    AStatusService aStatusService;

    /*@Scheduled(cron = "0/2 * * * * *")
     *")
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
    //@Scheduled(cron = "0 0 0/1 * * ?")
    //@Scheduled(cron = "0/15 * * * * *")
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void doDeleteData(){
        /*if(ConstParam.DEFAULTSETTING_autoDeleteDays==0){
            ConstParam.DEFAULTSETTING_autoDeleteDays=35;
        }*/
        //如果系统自启动 数据库获取参数值
        if(ConstParam.DEFAULTSETTING_autoDeleteDays==0){
            ConstController.constController.iniDefaultParamSetting();
            /*SStatusController sStatusController = new SStatusController();
            sStatusController.iniDefaultParamSetting();*/
        }
        //String createDateNow =   StringTimeUtils.addDataStrNow(Calendar.getInstance(),-ConstParam.DEFAULTSETTING_autoDeleteDays) ;
        if(ConstParam.DEFAULTSETTING_autoDelete == 1){
            //int iMonthNow = new Date().getMonth()+1;
            //String tmpMonth = (new Date().getMonth()+1)+"";
            //String sMonth = tmpMonth.length()>1?tmpMonth:"0"+tmpMonth;
            //int iSettingMaxMonth = ConstParam.DEFAULTSETTING_autoDeleteDays;
            if(ConstParam.DEFAULTSETTING_autoDeleteDays>0){

                //获取时间区间
                String startDeleteTime =  StringTimeUtils.addDataStrNow(Calendar.getInstance(),-ConstParam.DEFAULTSETTING_autoDeleteDays);
                log.info("delete start------------------dateTime"+startDeleteTime);
                //do spi delete pcb集合
                List<SPcb> sPcbList = sPcbService.selectList(Condition.create().lt("inspectStarttime",startDeleteTime).last("limit 10000"));
                if(sPcbList!=null && sPcbList.size()>0){
                    String create_time_spi = "";
                    for (int i = 0; i < sPcbList.size(); i++) {
                        create_time_spi = sPcbList.get(i).getCreate_time();
                        if(StringUtils.isEmpty(create_time_spi)){
                            continue;
                        }
                        sPcbService.delete(Condition.create().eq("create_time",create_time_spi));
                        sPadService.delete(Condition.create().eq("create_time",create_time_spi));
                        sStatusService.delete(Condition.create().eq("create_time",create_time_spi));
                    }
                }

                //do aoi delete pcb集合
                List<APcb> aPcbList = aPcbService.selectList(Condition.create().lt("inspectStarttime",startDeleteTime).last("limit 10000"));
                if(aPcbList!=null && aPcbList.size()>0){
                    String create_time_aoi = "";
                    for (int i = 0; i < aPcbList.size(); i++) {
                        create_time_aoi = aPcbList.get(i).getCreate_time();
                        if(StringUtils.isEmpty(create_time_aoi)){
                            continue;
                        }
                        aPcbService.delete(Condition.create().eq("create_time",create_time_aoi));
                        aComponentService.delete(Condition.create().eq("create_time",create_time_aoi));
                        aStatusService.delete(Condition.create().eq("create_time",create_time_aoi));
                    }
                }

                log.info("delete end------------------spi pcb count:"+sPcbList.size()+ "----------------aoi pcb count: "+aPcbList.size());

                sPcbList = null;
                aPcbList = null;
            }


        }
    }

}
