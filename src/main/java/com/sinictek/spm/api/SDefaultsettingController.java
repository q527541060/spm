package com.sinictek.spm.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.SDefaultsetting;
import com.sinictek.spm.model.SLine;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SDefaultsettingService;
import com.sinictek.spm.service.SLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Wrapper;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-17
 */
@Controller
@RequestMapping("/sDefaultsetting")
public class SDefaultsettingController {


    @Autowired
    SLineService sLineService;
    @Autowired
    SDefaultsettingService sDefaultsettingService;

    /*public void iniDefaultParamSetting(){

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
                        ConstParam.DEFAULTSETTING_boardViewChartMove= strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                }
            }
        }


    }*/

    @GetMapping("setting")
    public ModelAndView showSetting(){
        ConstController.constController.iniDefaultParamSetting();


        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "setting";
        if(bCmBoxs){
        }else{
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);

        mv.addObject("hChartColor",ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        mv.addObject("boardView_chartMove",ConstParam.DEFAULTSETTING_boardViewChartMove);

        return  mv;
    }

    @ResponseBody
    @GetMapping("listline")
    public ApiResponse getEquipmentJson(){
        return  new ApiResponse(true,
                null,
                null,
                sLineService.selectList(null));
    }

    @ResponseBody
    @GetMapping("lineSetting")
    public ApiResponse<SLine> getLineContentlistJson(@RequestParam("lineId") Long lineId){
        return new ApiResponse<SLine>(true,null,sLineService.selectById(lineId));
    }

    @ResponseBody
    @PostMapping("updateLineSetting")
    public ApiResponse updateSettingLine(SLine sline){
        try {
            sline.setCreateDate(StringTimeUtils.getTimeStringToDate(sline.getCreateDateStr()) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sline.setUpdateDate(new Date());
        boolean bIsSuccess = sLineService.update(sline, Condition.create().eq("id",sline.getIdStr()));
        if (bIsSuccess){
            return new ApiResponse(true,"save success",null);//"";
        }else{
            return new ApiResponse(false,"save fail",null);
        }
    }

    @ResponseBody
    @GetMapping("deleteLineSetting")
    public ApiResponse deleteSettingLine(@RequestParam("id") Long id){
        boolean bIsSuccess = sLineService.deleteById(id);
        if (bIsSuccess){
            return new ApiResponse(true,"delete success",null);
        }else{
            return new ApiResponse(false,"delete fail",null);
        }
    }

    @ResponseBody
    @GetMapping("getDefaultJson")
    public ApiResponse getDefaultJson(){
        return  new ApiResponse(true,null,null,sDefaultsettingService.selectList(null));
    }

    @ResponseBody
    @GetMapping("default")
    public ApiResponse getDefaultlistJson(@RequestParam("lineId") Long lineId){
        return new ApiResponse(true,null,sDefaultsettingService.selectById(lineId));
    }

    @ResponseBody
    @PostMapping("updatedefault")
    public ApiResponse updateSettingLine(SDefaultsetting sDefaultsetting){

        //保存全局设置  暂写入自动删除  保存数据天数
        switch (sDefaultsetting.getIdStr()){
            case "8":{    //自动删除
                ConstParam.DEFAULTSETTING_autoDelete = sDefaultsetting.getSettingValue()==null?0:Integer.parseInt(sDefaultsetting.getSettingValue());
                break;
            }case "9":{    //保存数据天数
                ConstParam.DEFAULTSETTING_autoDeleteDays = sDefaultsetting.getSettingValue()==null?35:Integer.parseInt(sDefaultsetting.getSettingValue());
                break;
            }case "12":{    //自动删除最大天数
                ConstParam.DEFAULTSETTING_autoDeleteMaxDays= sDefaultsetting.getSettingValue()==null?365:Integer.parseInt(sDefaultsetting.getSettingValue());
                break;
            }case "13":{     //表格图背景主题
                ConstParam.DEFAULTSETTING_hChartColor= sDefaultsetting.getSettingValue()==null?0:Integer.parseInt(sDefaultsetting.getSettingValue());
                break;
            }case "14":{     //页面背景色
                ConstParam.DEFAULTSETTING_backgroundColor= sDefaultsetting.getSettingValue()==null?0:Integer.parseInt(sDefaultsetting.getSettingValue());
                break;
            }case "15":{     //看板直通率标准设定值
                ConstParam.DEFAULTSETTING_passPcbYeild= sDefaultsetting.getSettingValue()==null?0:Integer.parseInt(sDefaultsetting.getSettingValue());
                break;
            }case "16":{     //看板动画渲染开关
                ConstParam.DEFAULTSETTING_boardViewChartMove= sDefaultsetting.getSettingValue()==null?0:Integer.parseInt(sDefaultsetting.getSettingValue());
                break;
            }
            default:{
                break;
            }

        }


        sDefaultsetting.setUpdateTime(new Date());
        boolean bIsSuccess = sDefaultsettingService.update(sDefaultsetting, Condition.create().eq("id",sDefaultsetting.getIdStr()));
        if (bIsSuccess){
            return new ApiResponse(true,"save success",null);//"";
        }else{
            return new ApiResponse(false,"save fail",null);
        }
    }

}

