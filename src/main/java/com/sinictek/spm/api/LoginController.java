package com.sinictek.spm.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.annotation.LoginToken;
import com.sinictek.spm.annotation.PassToken;
import com.sinictek.spm.model.SUser;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.service.SUserService;
import com.sinictek.spm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author sinictek-pd
 * @Date 2021/3/1 15:28
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    SUserService sUserService;

    @Autowired
    TokenService tokenService;

    @LoginToken
    @ResponseBody
    @PostMapping("/verify")
    public ApiResponse verify(){

        return new ApiResponse(true,"OK","");

    }

    @PostMapping("login")
    @ResponseBody
    public ApiResponse login(@RequestBody SUser sUser){

        if(sUser==null){
            return new ApiResponse(true,null,"登录失败!");
        }else{

            int iUserCount = sUserService.selectCount(Condition.create().eq("name",sUser.getName()).eq("psw",sUser.getPsw()));
            JSONObject jsonObject = new JSONObject();
            if(iUserCount>0) {
                String token = tokenService.getToken(sUser);  //create token
                jsonObject.put("user", sUser);
                jsonObject.put("token", token);
                jsonObject.put("login", "登录成功!");
                return new ApiResponse(true, "OK", jsonObject);
            }else{
                jsonObject.put("user",sUser);
                jsonObject.put("login", "登录失败!用户 账号密码不匹配!");
                return new ApiResponse(false,"NG",jsonObject);
            }
        }

    }

    @LoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

    @PassToken
    @PostMapping("/passMessage")
    public String passMessage(){
        return "通过啦!";
    }

    @ResponseBody
    @PostMapping("spc/login")
    public ApiResponse spcLogin(SUser sUser){
        JSONObject jsonObject = new JSONObject();
        if(sUser!=null) {
            int iUserCount = sUserService.selectCount(Condition.create().eq("name",sUser.getName()).eq("psw",sUser.getPsw()));
            if(iUserCount>0) {
                String token = tokenService.getToken(sUser);  //create token
                jsonObject.put("token",token);
                jsonObject.put("username",sUser.getName());
                jsonObject.put("data","");
                return new ApiResponse(true, "OK", jsonObject);
            }else{
                jsonObject.put("token","");
                jsonObject.put("data","USERNAME_OR_PASSWORD_ORROR");
                jsonObject.put("username",null);
                return new ApiResponse(false, "NG", jsonObject);
            }
        }else{
            jsonObject.put("token","");
            jsonObject.put("data","USERNAME_OR_PASSWORD_ORROR");
            jsonObject.put("username",null);
            return new ApiResponse(false, "NG", jsonObject);
        }
    }
}
