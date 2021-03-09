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
public class D {

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
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl(dburl);
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("1234");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true);
        //strategyConfig.setEntityLombokModel(false);
        strategyConfig .setNaming(NamingStrategy.underline_to_camel);
        //   .setSuperMapperClass("com.sinictek.spm")
        strategyConfig .setInclude(tableNames);
        config.setActiveRecord(false);
        config        .setAuthor(author);
        config       .setOutputDir("d:\\codeGen");
        config       .setFileOverride(true);
        config       .setEnableCache(false);
        config       .setEnableCache(false);
        if(!serviceNameStartWithI){
            config.setServiceName("%sService");
        }
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.      setGlobalConfig(config);
        autoGenerator       .setDataSource(dataSourceConfig);
        autoGenerator       .setStrategy(strategyConfig);
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName);
        packageConfig.setController("api");
        packageConfig.setEntity("model");
        packageConfig .setMapper("dao");
        packageConfig .setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig .setXml("mappers");
        autoGenerator       .setPackageInfo(
                packageConfig
        );
        autoGenerator.execute();
    }

}
