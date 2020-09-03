package com.sinictek.spm.api;

import com.baomidou.mybatisplus.annotations.Version;
import com.sinictek.spm.model.SLine;
import com.sinictek.spm.model.SUser;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.service.SLineService;
import com.sinictek.spm.service.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author sinictek-pd
 * @Date 2020/9/3 12:40
 * @Version 1.0
 */
@RestController
@RequestMapping(value="/rest",produces= MediaType.APPLICATION_JSON_VALUE)
public class SRestTestController {

    @Resource
    SLineService sLineService;
    @Resource
    SUserService sUserService;

    @GetMapping("/selectLine/{line}")
    @ResponseStatus(HttpStatus.OK)
    public Object getTestLineData(@PathVariable("line") String line){

        return  new ApiResponse(true,null,sLineService.selectById(line)) ;
    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addTestUserData(@RequestBody SUser sUser){

        if(sUser==null) new ApiResponse(false,"user_is_null",null);
        try{
            if(sUserService.insert(sUser)){
                //Thread.s
                return  new ApiResponse(true,null,null);
            }else{
                return  new ApiResponse(false,"insert fail ",null);
            }
        }catch (Exception e){
            return  new ApiResponse(false,"exception:"+e.getMessage(),null);
        }

    }

}
