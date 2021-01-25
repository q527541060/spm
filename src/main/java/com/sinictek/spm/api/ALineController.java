package com.sinictek.spm.api;


import com.alibaba.druid.util.StringUtils;
import com.sinictek.spm.model.APcb;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.JsonchartModel.*;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.model.utils.TestJavaDLLService;
import com.sinictek.spm.service.APcbService;
import com.sinictek.spm.service.SPcbService;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import net.iharder.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * <p>
 * 线体总表 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-08-05
 */
@Controller
//@RefreshScope
@RequestMapping("/aLine")
public class ALineController {

    @Autowired
    APcbService aPcbService;
   /* @Autowired
    private ContextRefresher contextRefresher;*/
    /*@Autowired
    private ValueConfig valueConfig;*/

    @GetMapping("pcbLine")
    public ModelAndView showPcbLine(@RequestParam("aoiType") String aoiType)  {
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "aoi/pcbLineData_aoi";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        //ModelAndView mv = new ModelAndView("spi/pcbLineData");
        mv.addObject("hChartColor", ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        mv.addObject("aoiType",aoiType);
        return  mv;
    }

    @ResponseBody
    @GetMapping(value = "jsonPcbLine",  produces = "application/json;charset=UTF-8")
    public ApiResponse getJsonPcbLine(@RequestParam("inspectStarttime") String inspectStarttime,
                                      @RequestParam("inspectEndtime") String inspectEndtime ,
                                      @RequestParam("mode") String mode ,
                                      @RequestParam("pcbType") String pcbType,
                                      @RequestParam("aoiType")String aoiType) throws ParseException {

        if(inspectStarttime ==null || inspectEndtime ==null){
            inspectStarttime= StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-24);
            inspectEndtime = StringTimeUtils.getTimeDateToString(new Date());
        }
        APcb pcb=null;
        List<APcb> lstPcb = new ArrayList<APcb>();
        Date dateStart = StringTimeUtils.getTimeStringToDate(inspectStarttime);
        Date dateEnd = StringTimeUtils.getTimeStringToDate(inspectEndtime);
        JsonChartsBean jsonChartsBeanYeild = new JsonChartsBean();
        JsonChartsBean jsonChartsBeanContainerline = new JsonChartsBean();
        // 24方格 mode=0时间  mode=1多线
        XAxis axisYeild = new XAxis();
        YAxis yAxisYeild = new YAxis();

        Title yTitleYeild = new Title();
        //yTitleYeild.setText();
        List<String> lstCategoriesYeild = new ArrayList<String>();

        List<Series> lstSeries = new ArrayList<Series>();

        Series goodSeries = new Series();
        goodSeries.setName("直通率");
        Series ngSeries = new Series();
        ngSeries.setName("不良率");
        Series passSeries = new Series();
        passSeries.setName("误报率");
        List<Data> lstGoodSeriesData = new ArrayList<Data>();
        List<Data> lstNgSeriesData = new ArrayList<Data>();
        List<Data> lstPassSeriesData = new ArrayList<Data>();

        List<Series> lstPcbSeries = new ArrayList<Series>();
        Series pcbCountSeries = new Series();
        pcbCountSeries.setName("总板");
        Series passPcbCountSeries = new Series();
        passPcbCountSeries.setName("复判板");
        Series ngPadCountSeries = new Series();
        ngPadCountSeries.setName("缺陷点");
        List<Data> lstpcbCountSeriesData = new ArrayList<Data>();
        List<Data> lstpassPcbCountSeriesData = new ArrayList<Data>();
        List<Data> lstngPadCountSeriesData = new ArrayList<Data>();

        Data data = new Data();
        double dAVGGood=0.0,dAVGNg=0.0,dAVGPass=0.0;
        double dAVGpcbCount=0.0,dAVGpassPcbCount=0.0,dAVGPadCount=0.0;

        switch (mode){
            case "1": {
                lstPcb = aPcbService.getPcbListWithALLLine(inspectStarttime,inspectEndtime,aoiType);
                if(lstPcb!=null && lstPcb.size()>0) {
                    double dTmp=0.0;
                    for (int i = 0; i < lstPcb.size(); i++) {
                        double goodarrayCount = (lstPcb.get(i).getGoodarrayCountAoi()==null )? 0:Double.parseDouble(lstPcb.get(i).getGoodarrayCountAoi()),
                                ngarrayCount = (lstPcb.get(i).getNgarrayCountAoi()==null )? 0:Double.parseDouble(lstPcb.get(i).getNgarrayCountAoi()),
                                passarrayCount = (lstPcb.get(i).getPassarrayCountAoi()==null )? 0:Double.parseDouble(lstPcb.get(i).getPassarrayCountAoi());
                        if(StringUtils.equals("1",pcbType)){
                            dTmp = lstPcb.get(i).getGoodPcbYeildAoi()==null?0: new BigDecimal(Double.parseDouble(lstPcb.get(i).getGoodPcbYeildAoi() )).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp = goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(goodarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0);
                        }else{
                            dTmp = lstPcb.get(i).getGoodcomponentYeildAoi()==null?0:(double)(Math.round(Double.parseDouble(lstPcb.get(i).getGoodcomponentYeildAoi()) *100)/100.0);
                        }
                        //dTmp =lstPcb.get(i).getGoodPcbYeild()==null?0.0: new BigDecimal(Double.parseDouble(lstPcb.get(i).getGoodPcbYeild() )).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        dAVGGood +=  dTmp;
                        data = new Data();data.setY(dTmp);
                        lstGoodSeriesData.add(data);

                        if(StringUtils.equals("1",pcbType)){
                            dTmp =lstPcb.get(i).getNgPcbYeildAoi()==null?0.0:new BigDecimal( Double.parseDouble(lstPcb.get(i).getNgPcbYeildAoi())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() ;
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp=goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(ngarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0);
                        }else{
                            dTmp =lstPcb.get(i).getNgcomponentYeildAoi()==null?0:(double)(Math.round(Double.parseDouble(lstPcb.get(i).getNgcomponentYeildAoi()) *100)/100.0);
                        }
                        dAVGNg+=dTmp ;
                        data = new Data();data.setY(dTmp);
                        lstNgSeriesData.add(data);

                        if(StringUtils.equals("1",pcbType)){
                            dTmp =lstPcb.get(i).getPassPcbYeildAoi()==null?0:new BigDecimal( Double.parseDouble(lstPcb.get(i).getPassPcbYeildAoi())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp=goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(passarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0);
                        }else{
                            dTmp =lstPcb.get(i).getPasscomponentYeildAoi()==null?0:(double)(Math.round(Double.parseDouble(lstPcb.get(i).getPasscomponentYeildAoi()) *100)/100.0);
                        }

                        dAVGPass+= dTmp ;
                        data = new Data();data.setY(dTmp);
                        lstPassSeriesData.add(data);
                        lstCategoriesYeild.add(lstPcb.get(i).getLineNo());

                        //pcb count

                        if(StringUtils.equals("1",pcbType)){
                            dTmp = lstPcb.get(i).getTotalAoi() ==null ? 0:Double.parseDouble(lstPcb.get(i).getTotalAoi());
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp=goodarrayCount+ngarrayCount+passarrayCount;
                        }else{
                            dTmp = lstPcb.get(i).getTotalAoi() ==null ? 0:Double.parseDouble(lstPcb.get(i).getTotalAoi());
                        }

                        data = new Data();data.setY(dTmp);
                        lstpcbCountSeriesData.add(data);
                        dAVGpcbCount+=dTmp;
                        //pass pcb count
                        if(StringUtils.equals("1",pcbType)){
                            dTmp=lstPcb.get(i).getPassPcbCountAoi()==null?0:Double.parseDouble(lstPcb.get(i).getPassPcbCountAoi());
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp=passarrayCount;
                        }else{
                            dTmp=lstPcb.get(i).getPassPcbCountAoi()==null?0:Double.parseDouble(lstPcb.get(i).getPassPcbCountAoi());
                        }
                        data = new Data();data.setY(dTmp);
                        lstpassPcbCountSeriesData.add(data);
                        dAVGpassPcbCount+=dTmp;
                        //Component 缺陷趋势
                        dTmp = lstPcb.get(i).getNgComponentCount()==null?0.0:lstPcb.get(i).getNgComponentCount()+0.0;
                        data = new Data();data.setY(dTmp);
                        lstngPadCountSeriesData.add(data);
                        dAVGPadCount+=dTmp;
                    }
                }
                lstCategoriesYeild.add("ALL-LINE");
                // List<Double> lists=null;
                //DoubleSummaryStatistics statistics = lstPcb.stream().mapToDouble(Number::doubleValue).summaryStatistics();
                data = new Data();data.setY(lstPcb.size()>0?new BigDecimal(dAVGGood/lstPcb.size()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue():0.0);
                lstGoodSeriesData.add(data );
                data = new Data();data.setY(lstPcb.size()>0?new BigDecimal(dAVGNg/lstPcb.size()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue():0.0);
                lstNgSeriesData.add(data );
                data = new Data();data.setY( lstPcb.size()>0?new BigDecimal(dAVGPass/lstPcb.size()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue():0.0);
                lstPassSeriesData.add(data);

                data = new Data();data.setY(lstPcb.size()>0?dAVGpcbCount:0.0);
                lstpcbCountSeriesData.add(data );
                data = new Data();data.setY(lstPcb.size()>0?dAVGpassPcbCount:0.0);
                lstpassPcbCountSeriesData.add(data );
                data = new Data();data.setY(lstPcb.size()>0?dAVGPadCount:0.0);
                lstngPadCountSeriesData.add( data);

                goodSeries.setType("column");
                goodSeries.setColor("#92ff4e");
                goodSeries.setData(lstGoodSeriesData);
                goodSeries.setStack("0");

                ngSeries.setType("column");
                ngSeries.setColor("#ff4c39");
                ngSeries.setData(lstNgSeriesData);
                ngSeries.setStack("0");

                passSeries.setType("column");
                passSeries.setColor("#211cff");
                passSeries.setData(lstPassSeriesData);
                passSeries.setStack("0");

                lstSeries.add(goodSeries);
                lstSeries.add(ngSeries);
                lstSeries.add(passSeries);

                pcbCountSeries.setType("column");
                pcbCountSeries.setData(lstpcbCountSeriesData);
                pcbCountSeries.setStack("0");
                passPcbCountSeries.setType("column");
                passPcbCountSeries.setData(lstpassPcbCountSeriesData);
                passPcbCountSeries.setStack("0");
                ngPadCountSeries.setType("column");
                ngPadCountSeries.setData(lstngPadCountSeriesData);
                ngPadCountSeries.setStack("0");

                lstPcbSeries.add(pcbCountSeries);
                lstPcbSeries.add(ngPadCountSeries);
                lstPcbSeries.add(passPcbCountSeries);

                axisYeild.setMin(0);
                axisYeild.setMax(lstPcb.size()==0?0:lstPcb.size());
                axisYeild.setCategories(lstCategoriesYeild);
                break;
            }
            case "0":
            default:
            {
                long iHour = (dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60);
                double dOptical = 1;
                if (iHour > 24) {
                    dOptical = iHour / 24;
                }
                Calendar calendar = Calendar.getInstance();


                inspectStarttime = StringTimeUtils.getTimeDateToString(dateStart);
                for (int i = 1; i < 25; i++) {
                    calendar.setTime(dateStart);

                    inspectEndtime = StringTimeUtils.addHourTimeStrNow(calendar, i * (int) dOptical);
                    pcb = aPcbService.getPcbListWithALLLineByDateNoGroup(inspectStarttime, inspectEndtime,aoiType);
                    inspectStarttime = inspectEndtime; //开始时间=结束时间
                    if (pcb != null) {

                        double goodarrayCount = pcb.getGoodarrayCountAoi()==null? 0:Double.parseDouble(pcb.getGoodarrayCountAoi()),
                                ngarrayCount = pcb.getNgarrayCountAoi()==null ? 0:Double.parseDouble(pcb.getNgarrayCountAoi()),
                                passarrayCount = pcb.getPassarrayCountAoi()==null ? 0:Double.parseDouble(pcb.getPassarrayCountAoi());
                        //直通率
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcb.getGoodPcbYeildAoi() == null ? 0.0 : Double.parseDouble(pcb.getGoodPcbYeildAoi()));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(goodarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0));
                        }else{
                            data.setY(pcb.getGoodcomponentYeildAoi()==null?0:(double)(Math.round(Double.parseDouble(pcb.getGoodcomponentYeildAoi()) *100)/100.0));
                        }
                        lstGoodSeriesData.add(data);
                        //不良率
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcb.getNgPcbYeildAoi() == null ? 0.0 : Double.parseDouble(pcb.getNgPcbYeildAoi()));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(ngarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0));
                        }else{
                            data.setY(pcb.getNgcomponentYeildAoi()==null?0:(double)(Math.round(Double.parseDouble(pcb.getNgcomponentYeildAoi()) *100)/100.0));
                        }
                        lstNgSeriesData.add(data);
                        //误判率
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcb.getPassPcbYeildAoi() == null ? 0.0 : Double.parseDouble(pcb.getPassPcbYeildAoi()));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(passarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0));
                        }else{
                            data.setY(pcb.getPasscomponentYeildAoi()==null?0:(double)(Math.round(Double.parseDouble(pcb.getPasscomponentYeildAoi()) *100)/100.0));
                        }
                        lstPassSeriesData.add(data);

                        //pcb count
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcb.getTotalAoi() ==null ? 0:Double.parseDouble( pcb.getTotalAoi()));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(goodarrayCount+ngarrayCount+passarrayCount);
                        }else{
                            data.setY(pcb.getTotalAoi() ==null ? 0:Double.parseDouble( pcb.getTotalAoi()));
                        }
                        lstpcbCountSeriesData.add(data);
                        //pass pcb count
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcb.getPassPcbCountAoi()==null?0:Double.parseDouble(pcb.getPassPcbCountAoi()));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(passarrayCount);
                        }else{
                            data.setY(pcb.getTotalAoi() ==null ? 0:Double.parseDouble( pcb.getTotalAoi()));
                        }

                        lstpassPcbCountSeriesData.add(data);
                        //comComponent 缺陷趋势
                        data = new Data();data.setY(pcb.getNgComponentCount()==null?0.0:pcb.getNgComponentCount()+0.0);
                        lstngPadCountSeriesData.add(data);
                    } else {
                        //直通率
                        data = new Data();data.setY(0.0);
                        lstGoodSeriesData.add(data);
                        //不良率
                        lstNgSeriesData.add(data);
                        //误判率
                        lstPassSeriesData.add(data);
                        //pcb count
                        lstpcbCountSeriesData.add(data);
                        //pass pcb count
                        lstpassPcbCountSeriesData.add(data);
                        //pad 缺陷趋势
                        lstngPadCountSeriesData.add(data);
                    }
                    lstCategoriesYeild.add(i * (int) dOptical + "h");
                }

                //lstPcbSeries.add(lstpcbCountSeriesData);
                /*axisYeild.setLineWidth(0);
                axisYeild.setTickWidth(0);
                axisYeild.setMinorGridLineWidth(0);
                axisYeild.setLineColor("transparent");
                axisYeild.setMinorTickLength(0);*/
                axisYeild.setMin(0);
                axisYeild.setMax(23);

                axisYeild.setCategories(lstCategoriesYeild);
                goodSeries.setType("areaspline");
                goodSeries.setData(lstGoodSeriesData);
                goodSeries.setColor("#92ff4e");
                ngSeries.setType("areaspline");
                ngSeries.setData(lstNgSeriesData);
                ngSeries.setColor("#ff4c39");
                passSeries.setType("areaspline");
                passSeries.setData(lstPassSeriesData);
                passSeries.setColor("#211cff");

                lstSeries.add(goodSeries);
                lstSeries.add(passSeries);
                lstSeries.add(ngSeries);

                pcbCountSeries.setType("areaspline");
                pcbCountSeries.setData(lstpcbCountSeriesData);
                passPcbCountSeries.setType("areaspline");
                passPcbCountSeries.setData(lstpassPcbCountSeriesData);
                ngPadCountSeries.setType("areaspline");
                ngPadCountSeries.setData(lstngPadCountSeriesData);

                lstPcbSeries.add(pcbCountSeries);
                lstPcbSeries.add(ngPadCountSeries);
                lstPcbSeries.add(passPcbCountSeries);
                break;
            }
        }
        jsonChartsBeanYeild.setSeries(lstSeries);
        jsonChartsBeanYeild.setXAxis(axisYeild);
        jsonChartsBeanContainerline.setSeries(lstPcbSeries);
        lstPcb=null;
        System.gc();
        return  new ApiResponse(true,null,jsonChartsBeanYeild,jsonChartsBeanContainerline);
    }


    @ResponseBody
    @GetMapping("pcbTableLine")
    public ApiResponse getJsonPcbTableLine(@RequestParam("inspectStarttime") String inspectStarttime,
                                           @RequestParam("inspectEndtime") String inspectEndtime,
                                           @RequestParam("aoiType") String aoiType){
        if(inspectStarttime ==null || inspectEndtime ==null){
            inspectStarttime= StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-24);
            inspectEndtime = StringTimeUtils.getTimeDateToString(new Date());
        }
        List<APcb> lstPcb = aPcbService.getPcbListWithALLLine(inspectStarttime,inspectEndtime,aoiType);
        return new ApiResponse(true,null,null,lstPcb
        );
    }


    @GetMapping("pcbLineDetails")
    public ModelAndView showPcbLineDetails(@RequestParam("lineNo") String lineNo,
                                           @RequestParam("inspectStarttime") String inspectStarttime,
                                           @RequestParam("inspectEndtime") String inspectEndtime,
                                           @RequestParam("pcbType") String pcbType,
                                           @RequestParam("aoiType")String aoiType){
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "aoi/pcbDataDetails_aoi";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("aoiType",aoiType);
        mv.addObject("pcbType",pcbType);
        mv.addObject("lineNo",lineNo);
        mv.addObject("inspectStarttime",inspectStarttime);
        mv.addObject("inspectEndtime",inspectEndtime);
        mv.addObject("hChartColor",ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        return mv;
    }


    @ResponseBody
    @GetMapping(value="lineDetailsLeftChart",produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getJsonlineDetailsLeftChart(@RequestParam("lineNo") String lineNo,
                                                   @RequestParam("inspectStarttime") String inspectStarttime,
                                                   @RequestParam("inspectEndtime") String inspectEndtime,
                                                   @RequestParam("pcbResult") String pcbResult,
                                                   @RequestParam("pcbType") String pcbType,
                                                   @RequestParam("aoiType") String aoiType)
    {

        if(pcbResult==null || pcbResult==""){
            pcbResult = "'0','1','2','4'"; //"good ng pass skip"
        }
        List<APcb>  lstPcb = aPcbService.getPcbListWithDateAndLineNoPcbResult(lineNo,inspectStarttime,inspectEndtime,pcbResult,aoiType);
        int totleNgPcb = 0;
        int totlePassPcb =0;
        int totleGoodPcb =0;
        List<Series> seriesLst = new ArrayList<Series>();
        String strPieServies = "";
        strPieServies +="[";
        if(lstPcb!=null && lstPcb.size()>0){
            for (int i = 0; i < lstPcb.size(); i++) {
                if(pcbType.contains("1") || pcbType.contains("3") ) {
                    if (lstPcb.get(i).getInspectResult()==0) {
                        totleGoodPcb++;
                    } else if (lstPcb.get(i).getInspectResult()==1) {
                        totleNgPcb++;
                    } else if (lstPcb.get(i).getInspectResult()==2) {
                        totlePassPcb++;
                    }
                }else{
                    if(lstPcb.get(i).getArrayInfo()!=null
                            &&lstPcb.get(i).getArrayInfo()!=""){
                        totleNgPcb+=org.apache.commons.lang3.StringUtils.countMatches(lstPcb.get(i).getArrayInfo(),":1");
                        totleGoodPcb+=org.apache.commons.lang3.StringUtils.countMatches(lstPcb.get(i).getArrayInfo(),":0");
                        totlePassPcb+=org.apache.commons.lang3.StringUtils.countMatches(lstPcb.get(i).getArrayInfo(),":2");
                    }
                }
            }
        }
        strPieServies+= "[\"REPASS\","+totlePassPcb+"]," +
                "[\"NG\","+totleNgPcb+"],"+
                "[\"PASS\","+totleGoodPcb +"]" +
                "]" ;


        //System.gc();
        return  new ApiResponse(true,null,strPieServies,lstPcb);
    }


    @GetMapping("pre/pcbfovView")
    public ModelAndView showPcbFovView(){
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "/aoi/pcbfovImageView_aoi";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        //valueConfig.setStaticLocations("classpath:/META-INF/resources/, classpath:/META-INF/resources/webjars/,classpath:/META-INF/static/,classpath:/static/,file://192.168.1.123\\aoi_db");
        //testYml.setStaticLocations("classpath:/META-INF/resources/, classpath:/META-INF/resources/webjars/,classpath:/META-INF/static/,classpath:/static/,file://192.168.1.148\\aoi_db");
        //System.out.println(valueConfig.getStaticLocations());
        //ImageIcon imageIcon = new ImageIcon("file://192.168.1.123/aoi_db/WholeImage/BoardImage.jpg");
        //Image image = imageIcon.getImage();
        //imageIcon.getImage().getHeight();
        //ConstParam.CONST_RESOUCE_IMAGEPATH="file://192.168.1.123/AOI_DB/";
        //new Thread(() -> contextRefresher.refresh()).start();

        return mv;
    }

    @GetMapping("getAoiImageByte")
    @ResponseBody
    public ApiResponse getImage(@RequestParam String path){
        //String Image3DPath = "\\\\127.0.0.1\\aoi_db\\20201028205455\\Component\\C328_ 1_774_92.3ddata_BF";
        if(StringUtils.isEmpty(path)){
            return  new ApiResponse(false,"PATH_IS_EMPTY",null);
        }else{
            try {
                //File file = new File(Image3DPath);
                //int[] arr = UtilLibrary.test(Image3DPath);
                //byte[] arrBy = new BinaryReaderIO(file).read(1000000);
                //int i1= BinaryReaderIO.readInt32();
                /*ObjectInputStream in = new ObjectInputStream(new FileInputStream(Image3DPath));
                System.out.println("obj1 " + in.readObject());    //读取字面值常量*/
                //System.out.println("obj1 " + in.readObject().toString());    //读取字面值常量*/
               byte[] arrByte =  ConstPublicClassUtil.getFileByte(path);

               return  new ApiResponse(true,"ok",Base64.encodeBytes(arrByte)) ;
            } catch (Exception  e) {
                e.printStackTrace();
                return ApiResponse.failed(e.getMessage());
            }
        }
    }

    @GetMapping("getAoiArray3DImage")
    @ResponseBody
    public ApiResponse getImageBy3D(@RequestParam String path,@RequestParam String heigthPath){

        if(StringUtils.isEmpty(path) ){
            return  new ApiResponse(false,"PATH_IS_EMPTY",null);
        }else{
            try {

                byte[] arrByte =  ConstPublicClassUtil.getFileByte(path);
                int[] arrDb={1};
                int iMax=0;
                int iMin=0;
                //get 3D height
                if(StringUtils.isEmpty(heigthPath) ==false){
                    // 注释 本地获取3D图高度
                   /* IntByReference intByReferenceHeight = new IntByReference();
                    IntByReference intByReferenceImage = TestJavaDLLService.TestJavaDLL.instaneDll.get3DHeightByFilePath(heigthPath,intByReferenceHeight);
                    arrDb= intByReferenceImage.getPointer().getIntArray(0,intByReferenceHeight.getValue());
                     iMax=Arrays.stream(arrDb).max().getAsInt();
                     iMin=Arrays.stream(arrDb).min().getAsInt();*/
                }
                //double[] arrRealDb = new double[intByReferenceHeight.getValue()];

                /*for(int i=0;i<arrRealDb.length;i++){
                    arrRealDb[i] = arrDb[i];
                    if(arrRealDb[i]>dMax){
                        dMax = arrRealDb[i];
                    }
                    if(arrRealDb[i]<dMin){
                        dMin = arrRealDb[i];
                    }
                }*/
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("base64Image",Base64.encodeBytes(arrByte));
                map.put("arrayHeightBy3D",arrDb);
                map.put("dMaxHeight",iMax+iMax*5);
                map.put("dMinHeight",iMin-iMax*5);

                return  new ApiResponse(true,"ok",map) ;
            } catch (Exception  e) {
                e.printStackTrace();
                return ApiResponse.failed(e.getMessage());
            }
        }


    }


    //public ApiResponse get
}

