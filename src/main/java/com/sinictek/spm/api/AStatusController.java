package com.sinictek.spm.api;

import com.sinictek.spm.model.AStatus;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.SStatus;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.service.AStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author sinictek-pd
 * @Date 2020/7/23 17:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/aStatus")
public class AStatusController {

    @Autowired
    AStatusService aStatusService;

    @RequestMapping("pcbMonitorview_realLineView")
    public ModelAndView ShowPcbMonitorView_AOI(){
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "aoi/pcbMonitorview_realLineView_aoi";
        if(bCmBoxs){
        }else{
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        return  mv;
    }

    @ResponseBody
    @RequestMapping("aoi/pcbMonitorJson")
    public ApiResponse GetPcbMonitorListJson_AOI(){

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
        return new ApiResponse(null,aStatusService.getAllStatusWithLineNoLimt_AOI());
    }

}
