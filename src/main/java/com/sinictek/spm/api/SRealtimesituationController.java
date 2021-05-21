package com.sinictek.spm.api;


import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.model.SRealtimesituation;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SRealtimesituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2021-04-16
 */
@RestController
@RequestMapping("/sRealtimesituation")
public class SRealtimesituationController {


    @Autowired
    SRealtimesituationService sRealtimesituationService;

    @GetMapping("/getSRealTimeSituation")
    @ResponseBody
    public ApiResponse getSRealTimeSituation(@RequestParam("inspectStarttime")String inspectStarttime,
                                             @RequestParam("inspectEndtime") String inspectEndtime,
                                             @RequestParam("lineNo")String lineNo){

        return  new ApiResponse(true,"","",
                sRealtimesituationService.selectList(Condition.create().eq("lineNo",lineNo)
                                                    .ge("DataTime",inspectStarttime)
                                                    .le("DataTime",inspectEndtime)
                )
        );
    }

    @PostMapping("/insertSituation")
    @ResponseBody
    public ApiResponse insertSituation( SRealtimesituation sRealtimesituation){

        sRealtimesituation.setCreate_time(StringTimeUtils.getDateToYearMonthDayString(sRealtimesituation.getDataTime()) );

        return  new ApiResponse(true,null,sRealtimesituationService.insert(sRealtimesituation),sRealtimesituation);

    }
}

