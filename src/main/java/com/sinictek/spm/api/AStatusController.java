package com.sinictek.spm.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author sinictek-pd
 * @Date 2020/7/23 17:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/Status")
public class AStatusController {

    @RequestMapping("aoi/pcbMonitorview")
    public ModelAndView ShowPcbMonitorView_AOI(){
        ModelAndView mv = new ModelAndView("aoi/pcbMonitorview_aoi");
        return  mv;
    }

}
