package com.sinictek.spm.advice;

import com.alibaba.druid.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author sinictek-pd
 * @Date 2021/3/11 15:03
 * @Version 1.0
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    //解析区域信息
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l = httpServletRequest.getParameter("lang");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);

            Locale.setDefault(locale);
        }
        return locale;
    }



    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }


}
