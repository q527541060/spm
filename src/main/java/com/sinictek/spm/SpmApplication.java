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
       /* try
        {
            DataBaseInit.initUseSQL("/sql/db_spm01.sql",
                    "/sql/db_spm02.sql",
                    "/sql/db_spm03.sql",
                    "/sql/db_spm04.sql",
                    "/sql/db_spm05.sql",
                    "/sql/db_spm06.sql",
                    "/sql/db_spm07.sql",
                    "/sql/db_spm08.sql",
                    "/sql/db_spm09.sql",
                    "/sql/db_spm10.sql",

                    "/sql/db_spm11.sql",
                    "/sql/db_spm12.sql",
                  *//*  "/sql/db_spm13.sql",
                    "/sql/db_spm202204.sql",
                    "/sql/db_spm202205.sql",
                    "/sql/db_spm202206.sql",
                    "/sql/db_spm202207.sql",
                    "/sql/db_spm202208.sql",
                    "/sql/db_spm202209.sql",
                    "/sql/db_spm202210.sql",
                    "/sql/db_spm202211.sql",
                    "/sql/db_spm202212.sql",*//*


                    "/sql/db_spm210001.sql"
            );
        }
        catch (IOException e)
        {
        }*/

        SpringApplication.run(SpmApplication.class, args);
    }
}
