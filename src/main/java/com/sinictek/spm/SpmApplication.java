package com.sinictek.spm;

import com.sinictek.spm.model.utils.DataBaseInit;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling  //配置多线程任务启动
@MapperScan("com.sinictek.spm.dao")
public class SpmApplication {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseInit.class);
    public static void main(String[] args) {
        try
        {
            DataBaseInit.initUseSQL("/sql/db_spm.sql");
        }
        catch (IOException e)
        {
            logger.error("DataBaseInit error:"+e.getMessage());
        }

        SpringApplication.run(SpmApplication.class, args);
    }
}
