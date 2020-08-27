package com.sinictek.spm.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.model.ConstClasses.ConstParam;
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

    @GetMapping("setting")
    public ModelAndView showSetting(){
        ModelAndView mv = new ModelAndView("setting");
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

