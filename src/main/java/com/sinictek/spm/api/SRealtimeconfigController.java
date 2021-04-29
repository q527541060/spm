package com.sinictek.spm.api;


import com.sinictek.spm.model.SRealtimeconfig;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.service.SRealtimeconfigService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2021-03-25
 */
@Controller
@RequestMapping()
public class SRealtimeconfigController {

    @Resource
    SRealtimeconfigService sRealtimeconfigService;




}

