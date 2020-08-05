package com.sinictek.spm.api;


import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.SDefaultsetting;
import com.sinictek.spm.model.SStatus;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.service.SDefaultsettingService;
import com.sinictek.spm.service.SLineService;
import com.sinictek.spm.service.SStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * spi-设备状态 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/Status")
public class SStatusController {

    @Autowired
    SDefaultsettingService sDefaultsettingService;
    @Autowired
    SStatusService sStatusService;

    @Autowired
    SLineService sLineService;

    @GetMapping("pcbMonitorview")
    public ModelAndView ShowPcbMonitorView(){
        ModelAndView mv = new ModelAndView("spi/pcbMonitorview");
        // ini defaultsetting
        iniDefaultParamSetting();
        return  mv;
    }
    private void iniDefaultParamSetting(){

        if(ConstParam.DEFAULTSETTING_boardMachineTimeLimit==0|
                ConstParam.DEFAULTSETTING_boardMachineRefreshTime==0|
                ConstParam.DEFAULTSETTING_autoDeleteDays==0|
                ConstParam.DEFAULTSETTING_FPY==0|
                ConstParam.DEFAULTSETTING_CPK==0|
                ConstParam.DEFAULTSETTING_Product==0|
                ConstParam.DEFAULTSETTING_DefaultTop5==0|
                ConstParam.DEFAULTSETTING_autoDelete==0|
                ConstParam.DEFAULTSETTING_defaultType==""
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

                }
            }
        }


    }


    @ResponseBody
    @GetMapping("pcbMonitorJson")
    public ApiResponse<List<SStatus>> GetPcbMonitorListJson(){
        //获取所有线体;
        //List<SLine> lineList = sLineService.selectList(null);
        //List<SStatus> statusList = sStatusService.getAllStatusWithLineNoLimt();
        //sStatusService.selectByMap()
        //ApiResponse<ModelMap> api = new ApiResponse<ModelMap>();
        //String strJson =
               /* "{\"total\":\"364\", rows:[{\"equipmentNo\":\"adf\"," +
                "\"lineNo\":\"SPI-01\",\"status\":\"stop\",\"factory\":\"Ssssss\" ,\"errContent\":\"eeeeee\"" +
                "}]";*/
        //JSON.toJSONString(statusList);
        return new ApiResponse<List<SStatus>>(null,sStatusService.getAllStatusWithLineNoLimt());
    }

}

