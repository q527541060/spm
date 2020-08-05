package com.sinictek.spm.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author sinictek-pd
 * @Date 2020/6/20 12:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/Home")
public class SHomeController {

    @GetMapping("/pcbHome")
    public ModelAndView showHome(){
        ModelAndView mv = new ModelAndView("pcbHome");
        mv.addObject("data","");
        return  mv;
    }

}
