package com.sinictek.spm.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
/**
 * @Author sinictek-pd
 * @Date 2020/6/2 11:43
 * @Version 1.0
 */
public class Generator {

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        String packageName = "com.sinictek.spm";
        boolean srtviceNameStartWithI=false;  //iservice

        generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm",
                "a_line",
                "a_status",
                "a_component",
                "a_defaultType","a_fov","a_job","a_pcb","a_window");
       /* generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm","s_defaultSetting");*/
        /* generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm","s_component");
        generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm","s_pad");
       generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm","s_pcb");
        generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm","s_status");
        generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm","s_component");
        generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm","s_pad");
        generatorByTables(srtviceNameStartWithI,packageName,"sinictek-pd","db_spm","s_user");*/
    }

    private static void generatorByTables(boolean serviceNameStartWithI,String packageName,
           String author,String database,String... tableNames)
    {
        GlobalConfig config = new GlobalConfig();
        String dburl = "jdbc:mysql://localhost:3306/db_spm?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dburl)
                .setUsername("root")
                .setPassword("1234")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(false)
                .setNaming(NamingStrategy.underline_to_camel)
             //   .setSuperMapperClass("com.sinictek.spm")
                .setInclude(tableNames);
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir("d:\\codeGen")
                .setFileOverride(true)
                .setEnableCache(false)
                .setEnableCache(false);
        if(!serviceNameStartWithI){
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                        .setParent(packageName)
                        .setController("api")
                        .setEntity("model")
                        .setMapper("dao")
                        .setService("service")
                        .setServiceImpl("service.impl")
                        .setXml("mappers")
                ).execute();
    }

}
