package com.sinictek.spm.api;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.annotation.LoginToken;
import com.sinictek.spm.model.*;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.*;
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
    @Autowired
    ALineService aLineService;

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
        mv.addObject("weburl","/sDefaultsetting/setting?");
        return  mv;
    }

    @ResponseBody
    @GetMapping("listline")
    public ApiResponse getEquipmentJson(){
        List<SLine> lineList = sLineService.selectList(Condition.create().eq("create_time","21000101"));
        try{
            return  new ApiResponse(true,
                    null,
                    null,
                    lineList);
        }catch (Exception e){
        }finally {
            lineList = null;
            System.gc();
        }
        return  new ApiResponse(true,
                null,
                null,
                lineList);
    }
    @ResponseBody
    @GetMapping("listlineAoi")
    public ApiResponse getEquipmentJsonAoi(){
        List<ALine> lineList =aLineService.selectList(Condition.create().eq("create_time","21000101"));
        try{
            return  new ApiResponse(true,
                    null,
                    null,lineList
                    );
        }catch (Exception e){
        }finally {
            lineList = null;
            System.gc();
        }
        return  new ApiResponse(true,
                null,
                null,lineList
                );
    }
    @ResponseBody
    @GetMapping("lineSetting")
    public ApiResponse getLineContentlistJson(@RequestParam("lineId") Long lineId,@RequestParam("mode")Integer mode){
        if(mode==1){
            return new ApiResponse(true,null,sLineService.selectById(lineId));
        }else{
            return new ApiResponse(true,null,aLineService.selectById(lineId));
        }

    }

    @ResponseBody
    @PostMapping("updateLineSetting")
    public ApiResponse updateSettingLine(SLine sline){
        if(sline!=null){
            if(  sline.getModeType() == 1){
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
            }else{
                ALine aLine = new ALine();
                try {
                    aLine.setCreateDate(StringTimeUtils.getTimeStringToDate(sline.getCreateDateStr()) );
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                aLine.setId(Long.parseLong(sline.getIdStr()) );
                aLine.setUpdateDate(new Date());
                aLine.setLineContent(sline.getLineContent());
                aLine.setLineNo(sline.getLineNo());
                aLine.setRemark(sline.getRemark());
                boolean bIsSuccess = aLineService.update(aLine, Condition.create().eq("id",sline.getIdStr()));
                if (bIsSuccess){
                    return new ApiResponse(true,"save success",null);//"";
                }else{
                    return new ApiResponse(false,"save fail",null);
                }

            }

        }

        return new ApiResponse(false,"save fail",null);
    }

    @ResponseBody
    @GetMapping("deleteLineSetting")
    public ApiResponse deleteSettingLine(@RequestParam("id") Long id,@RequestParam("mode")Integer mode,@RequestParam("dlineNo")String lineNo){
        boolean bIsSuccess = false;
        if(mode==1){
            bIsSuccess = sLineService.deleteById(id);

            //清空SPI线体数据!
            List<SPcb> sPcbList = sPcbService.selectList(Condition.create().lt("lineNo",lineNo));
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
                    //sPadService.up
                }
            }


        }else{
            bIsSuccess = aLineService.deleteById(id);

            //清空AOI线体数据
            List<APcb> aPcbList = aPcbService.selectList(Condition.create().lt("lineNo",lineNo));
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
        }

        if (bIsSuccess){
            return new ApiResponse(true,"delete success",null);
        }else{
            return new ApiResponse(false,"delete fail",null);
        }
    }

    @ResponseBody
    @GetMapping("getDefaultJson")
    public ApiResponse getDefaultJson(){
        List<SDefaultsetting> sDefaultsettingList = sDefaultsettingService.selectList(Condition.create().eq("create_time","21000101"));
        try{
            return  new ApiResponse(true,null,null,sDefaultsettingList);
        }catch (Exception e){
        }finally {
            sDefaultsettingList=null;
            System.gc();
        }
        return  new ApiResponse(true,null,null,sDefaultsettingList);
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

