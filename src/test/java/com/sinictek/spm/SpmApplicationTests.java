package com.sinictek.spm;


import com.alibaba.fastjson.JSON;
import com.sinictek.spm.dao.SPadMapper;
import com.sinictek.spm.dao.SStatusMapper;
import com.sinictek.spm.dao.SUserMapper;
import com.sinictek.spm.model.SPad;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.SStatus;
import com.sinictek.spm.model.SUser;
import com.sinictek.spm.service.SPcbService;
import com.sinictek.spm.service.SStatusService;
import com.sinictek.spm.service.SUserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;

import java.util.List;


@SpringBootTest
@MapperScan("com.sinictek.spm.dao")
class SpmApplicationTests {

    @Autowired
    SPcbService sPcbService;



    @Test
    void GetAllStatus(){
        /*List<SStatus> statusList = sStatusMapper.getAllStatusWithLineNoLimt();
        System.out.println(JSON.toJSONString(statusList));*/
    }


    @Test
    void contextLoads() {
    }










}