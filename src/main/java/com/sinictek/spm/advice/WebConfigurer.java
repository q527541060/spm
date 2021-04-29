package com.sinictek.spm.advice;

import com.sinictek.spm.listenner.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author sinictek-pd
 * @Date 2021/3/2 10:22
 * @Version 1.0
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录

        //注册TestInterceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(jwtInterceptor());
//        registration.addPathPatterns("/**");                      //添加拦截路径
//        registration.excludePathPatterns(                         //添加不拦截路径
//            "/**/*.html",            //html静态资源
//            "/**/*.js",              //js静态资源
//            "/**/*.css",             //css静态资源
//            "/**/*.woff",
//            "/**/*.ttf",
//            "/swagger-ui.html"
//        );
    }

    /***
     *   set 首页 login
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("login");
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();

    }
}