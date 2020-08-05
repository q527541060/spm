package com.sinictek.spm.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  多任务执行类
 */
@Component
public class BaseJob {

    /*@Scheduled(cron = "0/2 * * * * *")
    public void doJob(){
        System.out.println("springboot thread job..." + new Date());
    }
    @Scheduled(cron = "0/2 * * * * *")
    public void doLine(){
        System.out.println("springboot thread line..."+new Date());
    }*/
    @Scheduled(cron = "0/5 * * * * *")
    public void doGc(){
        System.gc();
    }
}
