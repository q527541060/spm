package com.sinictek.spm.api;


import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.SUser;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.service.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/sUser")
public class SUserController {

    @Autowired
    SUserService sUserService;

    @GetMapping("getUser")
    @ResponseBody
    public ApiResponse getTestUser(@RequestParam("userSize")Integer userSize){
        return new ApiResponse(true,"",sUserService.getTestAllUser(userSize));
    }

    @PostMapping("addUser")
    @ResponseBody
    public ApiResponse addTestUser(@RequestBody SUser sUser){

        return new ApiResponse(true,"",sUserService.addTestAllUser(sUser));
    }
}

