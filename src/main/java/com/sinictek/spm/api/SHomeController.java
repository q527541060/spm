package com.sinictek.spm.api;

import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SLineService;
import com.sinictek.spm.service.SPcbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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

    @GetMapping("/pcbHome")
    public ModelAndView showHome(){
        String strStartTime = "1900-01-01 00:00:00";
        //spi data
        //过程数据
        SPcb sPcb = sPcbService.getPcbListWithALLLineByDateNoGroup(strStartTime,StringTimeUtils.getTimeDateToString(new Date()));

        ModelAndView mv = new ModelAndView("pcbHome");
        mv.addObject("spi_dataCount",sPcb.getTotalpadCount());
        mv.addObject("spi_barcodeCount",sPcb.getTotal());
        mv.addObject("spi_lineCount",sLineService.selectCount(null));
        mv.addObject("spi_fovCount",0);
        mv.addObject("spi_pcbCount",0);
        mv.addObject("spi_componentCount",0);
        return  mv;
    }

}
