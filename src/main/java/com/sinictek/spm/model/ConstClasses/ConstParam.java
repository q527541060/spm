package com.sinictek.spm.model.ConstClasses;

/**
 * @Author sinictek-pd
 * @Date 2020/6/17 13:16
 * @Version 1.0
 */
public  class ConstParam {
    public static final String DEFAULTSETTING_SETTINGNAME_TOP1 = "top1";
    public static final String DEFAULTSETTING_SETTINGNAME_TOP2 = "top2";
    public static final String DEFAULTSETTING_SETTINGNAME_TOP3 = "top3";
    public static final String DEFAULTSETTING_SETTINGNAME_TOP4 = "top4";
    public static final  String DEFAULTSETTING_SETTINGNAME_TOP5 = "top5";


    public static final String DEFAULTSETTING_SETTINGNAME_TIMEINTERVER = "timeInterver";
    public static final String DEFAULTSETTING_SETTINGNAME_STANDARDCAPACITY = "StandardCapacity";

    public static int DEFAULTSETTING_boardMachineTimeLimit=0;
    public static int DEFAULTSETTING_boardMachineRefreshTime=0;
    public static int DEFAULTSETTING_autoDeleteDays=0;

    /**
     * FPY 0初始化 1开启  2关闭
     */
    public static int DEFAULTSETTING_FPY=0;
    /**
     * CPK 0初始化 1开启  2关闭
     */
    public static int DEFAULTSETTING_CPK=0;
    /**
     * Product 0初始化 1开启  2关闭
     */
    public static int DEFAULTSETTING_Product=0;
    /**
     * DefaultTop5 0初始化 1开启  2关闭
     */
    public static int DEFAULTSETTING_DefaultTop5=0;
    /**
     * autoDelete 0初始化 1开启  2关闭
     */
    public static int DEFAULTSETTING_autoDelete=0;
    public static String DEFAULTSETTING_defaultType="";
    /**
     * standCPK  CPK设置标准值1
     */
    public static double DEFAULTSETTING_standCPK=0;

    /**
     * FrequencyStart  班次设置值
     */
    public static int DEFAULTSETTING_FrequencyStart=0;

    /**
     * autoDeleteMaxDays 自动删除设置最大天数
     */
    public static int DEFAULTSETTING_autoDeleteMaxDays=0;

    /**
     * hChartColor  表格图背景主题
     */
    public static int DEFAULTSETTING_hChartColor=0;

    /**
     * backgroundColor 页面背景色
     */
    public static int DEFAULTSETTING_backgroundColor=0;

    /**
     * passPcbYeild  看板直通率标准设定值
     */
    public static int DEFAULTSETTING_passPcbYeild=0;
    /**
     * boardViewChartMove  看板动画渲染开关
     */
    public static int DEFAULTSETTING_boardViewChartMove=0;
    /**
     * showPad2DImageMode  看2D图片方式  0:中间层(autoapp)直接插入sql方式   1:中间层(autoapp)调用web接口传入base64方式   2:中间层(autoapp)调用web传入图片路径方式
     */
    public static int DEFAULTSETTING_showPad2DImageMode=0;

    //public static String CONST_RESOUCE_IMAGEPATH = "file://192.168.1.123/AOI_DB/";
}