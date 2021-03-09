package com.sinictek.spm.api;

import com.alibaba.fastjson.JSONObject;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author sinictek-pd
 * @Date 2021/3/2 10:24
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //@ResponseBody
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {

        String viewName = "";
        viewName = "error/error";
        ModelAndView mv = new ModelAndView(viewName);

        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        JSONObject jsonObject = new JSONObject();
        mv.addObject("code", 1000);
        mv.addObject("message", msg);
        return mv;
    }

}
