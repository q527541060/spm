package com.sinictek.spm.api;


import com.alibaba.druid.util.StringUtils;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.JsonchartModel.*;
import com.sinictek.spm.model.SDefaultsetting;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SDefaultsettingService;
import com.sinictek.spm.service.SPcbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * spi-pcb表 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/Pcb")
public class SPcbController {

    @Autowired
    SPcbService sPcbService;

    @Autowired
    SDefaultsettingService sDefaultsettingService;

    @ResponseBody
    @GetMapping("pcbList")
    public ModelMap getPchList(){
        ModelAndView mav = new ModelAndView("/sPcb/pcbList");
        List<SPcb> lstPCB = sPcbService.selectList(null);
        mav.addObject("pcbList",lstPCB) ;
        ModelMap map = mav.getModelMap();

        lstPCB = null;
        System.gc();
        return map;
    }

    @ResponseBody
    @GetMapping("FPY_LineNo")
    public ApiResponse<JsonChartsBean> getFPTYWithLineNo(@RequestParam("lineNo") String lineNo){

        String[] arrStrTop5 = getDefaultTypeArray();
        ModelMap model = new ModelMap();

        int iTop1Count = 0 ;
        int iTop2Count=  0;
        int iTop3Count =  0;
        int iTop4Count =  0;
        int iTop5Count =  0;

        String fristLineNo = "";
        JsonChartsBean jsonChartsBean = new JsonChartsBean();
        Title title = new Title();
        Chart chart = new Chart();
        Column column = new Column();
        //Credits credits = new Credits();
        //PlotOptions plotOptions = new PlotOptions();
        Series goodSeries = new Series();
        Series passSeries = new Series();
        Series ngSeries = new Series();
        XAxis xAxis = new XAxis();
        Title yAxistitle = new Title();
        YAxis yAxis = new YAxis();
        String endTime =StringTimeUtils.getTimeDateToString(new Date());
        List<SPcb> sPcbList = new ArrayList<SPcb>();
        SPcb sPcbtmp = sPcbService.getPcbListWithLineNo(lineNo,StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-ConstParam.DEFAULTSETTING_boardMachineTimeLimit),endTime);
        if(sPcbtmp.getLineNo()!=null && sPcbtmp.getLineNo()!="") {
            sPcbList.add(sPcbtmp);
        }
        List<String> categoriesList = new ArrayList<String>();
        List<Series> seriesList = new ArrayList<Series>();
        List<Data> goodSeriesList = new ArrayList<Data>();
        List<Data> passSeriesList = new ArrayList<Data>();
        List<Data> ngSeriesList = new ArrayList<Data>();



        int iTop1 =  Integer.parseInt(arrStrTop5[0]) ;
        int iTop2=  Integer.parseInt(arrStrTop5[1]);
        int iTop3 =  Integer.parseInt(arrStrTop5[2]);
        int iTop4 =  Integer.parseInt(arrStrTop5[3]);
        int iTop5 =  Integer.parseInt(arrStrTop5[4]);
        Data data =null;
        if(sPcbList!=null && sPcbList.size()>0){
            for (int i=0;i<sPcbList.size();i++
            ) {
                data = new Data();
                fristLineNo = sPcbList.get(0).getLineNo();
                categoriesList.add(sPcbList.get(i).getLineNo()+"");
                data.setY((double)(Math.round(Double.parseDouble(sPcbList.get(i).getGoodPcbYeild()) *100)/100.0));
                goodSeriesList.add(data);
                data = new Data();
                data.setY((double)(Math.round(Double.parseDouble(sPcbList.get(i).getPassPcbYeild()) *100)/100.0));
                passSeriesList.add(data);
                data = new Data();
                data.setY((double)(Math.round(Double.parseDouble(sPcbList.get(i).getNgPcbYeild()) *100)/100.0));
                ngSeriesList.add(data);
                //default error count
                iTop1Count+= getPadErrorCodeCount(sPcbList.get(i),iTop1);
                iTop2Count+= getPadErrorCodeCount(sPcbList.get(i),iTop2);
                iTop3Count+= getPadErrorCodeCount(sPcbList.get(i),iTop3);
                iTop4Count+= getPadErrorCodeCount(sPcbList.get(i),iTop4);
                iTop5Count= getPadErrorCodeCount(sPcbList.get(i),iTop5);
            }
        }
        chart.setType("column");
        xAxis.setCategories(categoriesList);
        xAxis.setCrosshair(true);
        xAxis.setMin(0);
        xAxis.setMax(categoriesList.size()<=0?0:(categoriesList.size()-1));

        yAxistitle.setText("yied(%)");
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.setTitle(yAxistitle);
        yAxis.setMinorGridLineWidth(0);

        column.setBorderWidth(0);
        column.setPointPadding(0.1);
        //DataLabels dataLabels = new DataLabels();
        //dataLabels.setEnabled(true);
        //column.setDataLabels(dataLabels);
        // plotOptions.setColumn(column);

        //credits.setEnabled(false);

        goodSeries.setType("column");
        goodSeries.setName("good%");
        goodSeries.setData(goodSeriesList);

        ngSeries.setType("column");
        ngSeries.setName("ng%");
        ngSeries.setData(ngSeriesList);

        passSeries.setType("column");
        passSeries.setName("pass%");
        passSeries.setData(passSeriesList);

        seriesList.add(goodSeries);
        seriesList.add(ngSeries);
        seriesList.add(passSeries);

        jsonChartsBean.setChart(chart);
        //jsonChartsBean.setTitle("");
        //json.subtitle = subtitle;
        //jsonChartsBean.setTooltip("");
        jsonChartsBean.setXAxis(xAxis) ;
        jsonChartsBean.setYAxis(yAxis) ;
        jsonChartsBean.setSeries(seriesList);
        //jsonChartsBean.setPlotOptions(plotOptions);
        //jsonChartsBean.setCredits(credits);

        model.put("iTop1Count",iTop1Count);
        model.put("iTop2Count",iTop2Count);
        model.put("iTop3Count",iTop3Count);
        model.put("iTop4Count",iTop4Count);
        model.put("iTop5Count",iTop5Count);
        model.put("fristLineNo",fristLineNo);

        sPcbList=null;
        /*categoriesList.clear();
        seriesList.clear();
        goodSeriesList.clear();
        passSeriesList.clear();
        ngSeriesList.clear();*/
        System.gc();
        return new ApiResponse(true,"",jsonChartsBean,model);
    }


    @ResponseBody
    @GetMapping("FPY")
    public ApiResponse<JsonChartsBean> getFPTYAndTop5ChartDatas(){

        String[] arrStrTop5 = getDefaultTypeArray();
        ModelMap model = new ModelMap();

        int iTop1Count = 0 ;
        int iTop2Count=  0;
        int iTop3Count =  0;
        int iTop4Count =  0;
        int iTop5Count =  0;

        String fristLineNo = "";
        JsonChartsBean jsonChartsBean = new JsonChartsBean();
        Title title = new Title();
        Chart chart = new Chart();
        Column column = new Column();
        //Credits credits = new Credits();
        //PlotOptions plotOptions = new PlotOptions();
        Series goodSeries = new Series();
        Series passSeries = new Series();
        Series ngSeries = new Series();
        XAxis xAxis = new XAxis();
        Title yAxistitle = new Title();
        YAxis yAxis = new YAxis();
        String endTime =StringTimeUtils.getTimeDateToString(new Date());
        List<SPcb> sPcbList =  sPcbService.getPcbListWithALLLine(StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-ConstParam.DEFAULTSETTING_boardMachineTimeLimit),endTime);
        List<String> categoriesList = new ArrayList<String>();
        List<Series> seriesList = new ArrayList<Series>();
        List<Data> goodSeriesList = new ArrayList<Data>();
        List<Data> passSeriesList = new ArrayList<Data>();
        List<Data> ngSeriesList = new ArrayList<Data>();

        int iTop1 =  Integer.parseInt(arrStrTop5[0]) ;
        int iTop2=  Integer.parseInt(arrStrTop5[1]);
        int iTop3 =  Integer.parseInt(arrStrTop5[2]);
        int iTop4 =  Integer.parseInt(arrStrTop5[3]);
        int iTop5 =  Integer.parseInt(arrStrTop5[4]);
        Data data =null;
        if(sPcbList!=null && sPcbList.size()>0){
            for (int i=0;i<sPcbList.size();i++
                 ) {
                fristLineNo = sPcbList.get(0).getLineNo();
                categoriesList.add(sPcbList.get(i).getLineNo()+"");
                data = new Data();
                data.setY((double)(Math.round(Double.parseDouble(sPcbList.get(i).getGoodPcbYeild()) *100)/100.0));
                goodSeriesList.add(data);
                data = new Data();
                data.setY((double)(Math.round(Double.parseDouble(sPcbList.get(i).getPassPcbYeild()) *100)/100.0));
                passSeriesList.add(data);
                data = new Data();
                data.setY((double)(Math.round(Double.parseDouble(sPcbList.get(i).getNgPcbYeild()) *100)/100.0));
                ngSeriesList.add(data);
                //default error count
                iTop1Count+= getPadErrorCodeCount(sPcbList.get(i),iTop1);
                iTop2Count+= getPadErrorCodeCount(sPcbList.get(i),iTop2);
                iTop3Count+= getPadErrorCodeCount(sPcbList.get(i),iTop3);
                iTop4Count+= getPadErrorCodeCount(sPcbList.get(i),iTop4);
                iTop5Count= getPadErrorCodeCount(sPcbList.get(i),iTop5);
            }
        }
       chart.setType("column");
        xAxis.setCategories(categoriesList);
        xAxis.setCrosshair(true);
        xAxis.setMin(0);
        xAxis.setMax(categoriesList.size()<=0?0:(categoriesList.size()-1));

        yAxistitle.setText("yied(%)");
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.setTitle(yAxistitle);
        yAxis.setMinorGridLineWidth(0);

        column.setBorderWidth(0);
        column.setPointPadding(0.1);
        //DataLabels dataLabels = new DataLabels();
        //dataLabels.setEnabled(true);
        //column.setDataLabels(dataLabels);
       // plotOptions.setColumn(column);

        //credits.setEnabled(false);

        goodSeries.setType("column");
        goodSeries.setName("good%");
        goodSeries.setData(goodSeriesList);

        ngSeries.setType("column");
        ngSeries.setName("ng%");
        ngSeries.setData(ngSeriesList);

        passSeries.setType("column");
        passSeries.setName("pass%");
        passSeries.setData(passSeriesList);

        seriesList.add(goodSeries);
        seriesList.add(ngSeries);
        seriesList.add(passSeries);

        jsonChartsBean.setChart(chart);
        //jsonChartsBean.setTitle("");
        //json.subtitle = subtitle;
        //jsonChartsBean.setTooltip("");
        jsonChartsBean.setXAxis(xAxis) ;
        jsonChartsBean.setYAxis(yAxis) ;
        jsonChartsBean.setSeries(seriesList);
        //jsonChartsBean.setPlotOptions(plotOptions);
        //jsonChartsBean.setCredits(credits);

        model.put("iTop1Count",iTop1Count);
        model.put("iTop2Count",iTop2Count);
        model.put("iTop3Count",iTop3Count);
        model.put("iTop4Count",iTop4Count);
        model.put("iTop5Count",iTop5Count);
        model.put("fristLineNo",fristLineNo);

        sPcbList=null;
        /*categoriesList.clear();
        seriesList.clear();
        goodSeriesList.clear();
        passSeriesList.clear();
        ngSeriesList.clear();*/
        System.gc();
        return new ApiResponse(true,"",jsonChartsBean,model);
    }

    private int getPadErrorCodeCount(SPcb sPcbList,int iJuResult){
        int i=0;

        switch (iJuResult){
            case 0:{
                i = sPcbList.getMissingCount()==null?0:sPcbList.getMissingCount();
                break;
            }
            case 1:
            {
                i = sPcbList.getInsufficientCount()==null?0:sPcbList.getInsufficientCount();
                break;
            }
            case 2:
            {
                i = sPcbList.getExcessCount()==null?0:sPcbList.getExcessCount();
                break;
            }
            case 3:
            {
                i = sPcbList.getOverheightCount()==null?0:sPcbList.getOverheightCount();
                break;
            }
            case 4:
            {
                i = sPcbList.getLowheightCount()==null?0:sPcbList.getLowheightCount();
                break;
            }
            case 5:
            {
                i = sPcbList.getOverareaCount()==null?0:sPcbList.getOverareaCount();
                break;
            }
            case 6:
            {
                i = sPcbList.getLowareaCount()==null?0:sPcbList.getLowareaCount();
                break;
            }
            case 7:
            {
                i = sPcbList.getShiftxCount()==null?0:sPcbList.getShiftxCount();
                break;
            }
            case 8:
            {
                i = sPcbList.getShiftyCount()==null?0:sPcbList.getShiftyCount();
                break;
            }
            case 9:
            {
                i = sPcbList.getBridgeCount()==null?0:sPcbList.getBridgeCount();
                break;
            }
            case 10:
            {
                i = sPcbList.getShapeerrorCount()==null?0:sPcbList.getShapeerrorCount();
                break;
            }
            case 11:
            {
                i = sPcbList.getSmearedCount()==null?0:sPcbList.getSmearedCount();
                break;
            }
            case 12:
            {
                i = sPcbList.getCoplanarityCount()==null?0:sPcbList.getCoplanarityCount();
                break;
            }
            case 13:
            {
                i = sPcbList.getPrebridgeCount()==null?0:sPcbList.getPrebridgeCount();
                break;
            }
            case 14:
            {
                i = sPcbList.getPadareapercentCount()==null?0:sPcbList.getPadareapercentCount();
                break;
            }
            case 15:
            {
                i = sPcbList.getBridgeCount()==null?0:sPcbList.getBridgeCount();
                break;
            }
           default:{
               i = sPcbList.getOtherCount()==null?0:sPcbList.getOtherCount();
               break;
           }
                   /* Missing = 0,
                   Insufficient = 1,
                   Excess = 2,
                   OverHeight = 3,
                   LowHeight = 4,
                   OverArea = 5,
                   LowArea = 6,
                   ShiftX = 7,
                   ShiftY = 8,
                   Bridge = 9,
                   ShapeError = 10,
                   Smeared = 11,
                   Coplanarity = 12,
                   PreBridge = 13,
                   PadAreaError = 14,
                   WarpError = 15,*/
        }

        return  i;
    }

    private String[] getDefaultTypeArray(){
        if(ConstParam.DEFAULTSETTING_defaultType==""){
            ConstParam.DEFAULTSETTING_defaultType="0;1;2;3;4";
        }
        return ConstParam.DEFAULTSETTING_defaultType.split(";");
    }
    private String getErrorCodeString(int iJuResult) {
        String str = "";
        switch (iJuResult) {
            case 0: {
                str = "Missing";
                break;
            }
            case 1: {
                str = "Insufficient";
                break;
            }
            case 2: {
                str = "Excess";
                break;
            }
            case 3: {
                str = "Overheight";
                break;
            }
            case 4: {
                str = "Lowheight";
                break;
            }
            case 5: {
                str = "Overarea";
                break;
            }
            case 6: {
                str = "Lowarea";
                break;
            }
            case 7: {
                str = "Shiftx";
                break;
            }
            case 8: {
                str = "Shifty";
                break;
            }
            case 9: {
                str = "Bridge";
                break;
            }
            case 10: {
                str = "Shapeerror";
                break;
            }
            case 11: {
                str = "Smeared";
                break;
            }
            case 12: {
                str = "Coplanarity";
                break;
            }
            case 13: {
                str = "Prebridge";
                break;
            }
            case 14: {
                str = "Padareapercent";
                break;
            }
            case 15: {
                str = "Bridge";
                break;
            }
            default: {
                str = "Other";
                break;
            }

        }
        return  str;
    }
    @ResponseBody
    @PostMapping("DefaultTop5")
    public ApiResponse<JsonChartsBean> getDefaultTop5ChartWithLineNo(@RequestParam("lineNo") String lineNo,@RequestParam("iTop1count")Integer iTop1count,
                                                                     @RequestParam("iTop2count") Integer iTop2count,@RequestParam("iTop3count") Integer iTop3count,
                                                                     @RequestParam("iTop4count") Integer iTop4count ,@RequestParam("iTop5count") Integer iTop5count ){
        //从后台获取defaultTop5数据;
        List<String> categoriesList = new ArrayList<String>();
        String[] arrStrTop5 = getDefaultTypeArray();
        int iTop1Count = 0 ;
        int iTop2Count=  0;
        int iTop3Count =  0;
        int iTop4Count =  0;
        int iTop5Count =  0;
        int timeInterVer = ConstParam.DEFAULTSETTING_boardMachineTimeLimit;
        String endTime =   StringTimeUtils.getTimeDateToString(new Date());
        String startTime = StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-timeInterVer);

        SPcb sPcb = sPcbService.getPcbListWithLineNo(lineNo,startTime,endTime);
        JsonChartsBean jsonChartsBean = new JsonChartsBean();
        Title title = new Title();
        Chart chart = new Chart();
        Column column = new Column();
        column.setStacking(lineNo);
        Credits credits = new Credits();
        PlotOptions plotOptions = new PlotOptions();
        XAxis xAxis = new XAxis();

        YAxis yAxis = new YAxis();
        List<Data> seriesAllList = new ArrayList<Data>();
        Data data = new Data();
        data.setY((double)iTop1count);
        seriesAllList.add(data);
        data = new Data();
        data.setY((double)iTop2count);
        seriesAllList.add(data);
        data = new Data();
        data.setY((double)iTop3count);
        seriesAllList.add(data);
        data = new Data();
        data.setY((double)iTop4count);
        seriesAllList.add(data);
        data = new Data();
        data.setY((double)iTop5count);
        seriesAllList.add(data);
        List<Data> seriesLineList = new ArrayList<Data>();
        int iTop1 =  Integer.parseInt(arrStrTop5[0]) ;
        int iTop2=  Integer.parseInt(arrStrTop5[1]);
        int iTop3 =  Integer.parseInt(arrStrTop5[2]);
        int iTop4 =  Integer.parseInt(arrStrTop5[3]);
        int iTop5 =  Integer.parseInt(arrStrTop5[4]);
        if(sPcb!=null ) {
            data = new Data();
            data.setY((double) getPadErrorCodeCount(sPcb, iTop1));
            seriesLineList.add(data);
            data = new Data();
            data.setY((double) getPadErrorCodeCount(sPcb, iTop2));
            seriesLineList.add(data);
            data = new Data();
            data.setY((double) getPadErrorCodeCount(sPcb, iTop3));
            seriesLineList.add(data);
            data = new Data();
            data.setY((double) getPadErrorCodeCount(sPcb, iTop4));
            seriesLineList.add(data);
            data = new Data();
            data.setY((double) getPadErrorCodeCount(sPcb, iTop5));
            seriesLineList.add(data);
        }else{
            data = new Data();
            data.setY(0.0);
            seriesLineList.add(data);
            seriesLineList.add(data);
            seriesLineList.add(data);
            seriesLineList.add(data);
            seriesLineList.add(data);
        }
        categoriesList.add(getErrorCodeString(iTop1));
        categoriesList.add(getErrorCodeString(iTop2));
        categoriesList.add(getErrorCodeString(iTop3));
        categoriesList.add(getErrorCodeString(iTop4));
        categoriesList.add(getErrorCodeString(iTop5));
        chart = new Chart();
        chart.setType("column");
        xAxis.setMin(0);
        xAxis.setMax(4);
        xAxis.setCategories(categoriesList);
        yAxis.setAllowDecimals(false);
        yAxis.setMin(0);
        yAxis.setMax(iTop1count+iTop2count+iTop3count+iTop4count+iTop5count);
        title.setText("value");
        yAxis.setTitle(title);
        yAxis.setMinorGridLineWidth(0);
        DataLabels dataLabels = new DataLabels();
        dataLabels.setEnabled(true);
        column.setDataLabels(dataLabels);
        plotOptions.setColumn(column);

        List<Series> seriesList = new ArrayList<Series>();
        Series seriesAll = new Series();
        Series seriesLine = new Series();
        seriesAll.setStack("male");
        seriesAll.setName("All-Line");
        seriesAll.setData(seriesAllList);

        seriesLine.setStack("'female'");
        seriesLine.setName(lineNo);
        seriesLine.setData(seriesLineList);

        jsonChartsBean.setChart(chart);
        jsonChartsBean.setTitle("");
        //json.subtitle = subtitle;
        jsonChartsBean.setXAxis(xAxis) ;
        jsonChartsBean.setYAxis(yAxis) ;
        seriesList.add(seriesAll);
        seriesList.add(seriesLine);
        jsonChartsBean.setSeries(seriesList);
        jsonChartsBean.setPlotOptions(plotOptions);
        credits.setEnabled(false);
        jsonChartsBean.setCredits(credits);

        System.gc();
        return new ApiResponse<JsonChartsBean>(true,"",jsonChartsBean);
    }
    @ResponseBody
    @PostMapping("DefaultTop5_LineNo")
    public ApiResponse<JsonChartsBean> getDefaultTop5ChartWithLineNo_LineNo(@RequestParam("lineNo") String lineNo,@RequestParam("iTop1count")Integer iTop1count,
                                                                     @RequestParam("iTop2count") Integer iTop2count,@RequestParam("iTop3count") Integer iTop3count,
                                                                     @RequestParam("iTop4count") Integer iTop4count ,@RequestParam("iTop5count") Integer iTop5count ){
        //从后台获取defaultTop5数据;
        List<String> categoriesList = new ArrayList<String>();
        String[] arrStrTop5 = getDefaultTypeArray();
        int iTop1Count = 0 ;
        int iTop2Count=  0;
        int iTop3Count =  0;
        int iTop4Count =  0;
        int iTop5Count =  0;
        int timeInterVer = ConstParam.DEFAULTSETTING_boardMachineTimeLimit;
        String endTime =   StringTimeUtils.getTimeDateToString(new Date());
        String startTime = StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-timeInterVer);

        SPcb sPcb = sPcbService.getPcbListWithLineNo(lineNo,startTime,endTime);
        JsonChartsBean jsonChartsBean = new JsonChartsBean();
        Title title = new Title();
        Chart chart = new Chart();
        Column column = new Column();
        column.setStacking(lineNo);
        Credits credits = new Credits();
        PlotOptions plotOptions = new PlotOptions();
        XAxis xAxis = new XAxis();

        YAxis yAxis = new YAxis();
        /*List<Double> seriesAllList = new ArrayList<Double>();
        seriesAllList.add((double)iTop1count);
        seriesAllList.add((double)iTop2count);
        seriesAllList.add((double)iTop3count);
        seriesAllList.add((double)iTop4count);
        seriesAllList.add((double)iTop5count);*/
        List<Data> seriesLineList = new ArrayList<Data>();
        int iTop1 =  Integer.parseInt(arrStrTop5[0]) ;
        int iTop2=  Integer.parseInt(arrStrTop5[1]);
        int iTop3 =  Integer.parseInt(arrStrTop5[2]);
        int iTop4 =  Integer.parseInt(arrStrTop5[3]);
        int iTop5 =  Integer.parseInt(arrStrTop5[4]);
        Data data =new Data();
        if(sPcb!=null ) {
            data = new Data();
            data.setY((double) getPadErrorCodeCount(sPcb, iTop1));
            seriesLineList.add(data);
            data = new Data();
            data.setY((double) getPadErrorCodeCount(sPcb, iTop2));
            seriesLineList.add(data);
            data = new Data();data.setY((double) getPadErrorCodeCount(sPcb, iTop3));
            seriesLineList.add(data);
            data = new Data();data.setY((double) getPadErrorCodeCount(sPcb, iTop4));
            seriesLineList.add(data);
            data = new Data();data.setY((double) getPadErrorCodeCount(sPcb, iTop5));
            seriesLineList.add(data);
        }else{
            data = new Data();data.setY(0.0);
            seriesLineList.add(data);
            seriesLineList.add(data);
            seriesLineList.add(data);
            seriesLineList.add(data);
            seriesLineList.add(data);
        }
        categoriesList.add(getErrorCodeString(iTop1));
        categoriesList.add(getErrorCodeString(iTop2));
        categoriesList.add(getErrorCodeString(iTop3));
        categoriesList.add(getErrorCodeString(iTop4));
        categoriesList.add(getErrorCodeString(iTop5));
        chart = new Chart();
        chart.setType("column");
        xAxis.setMin(0);
        xAxis.setMax(4);
        xAxis.setCategories(categoriesList);
        yAxis.setAllowDecimals(false);
        yAxis.setMin(0);
        yAxis.setMax(iTop1count+iTop2count+iTop3count+iTop4count+iTop5count);
        title.setText("value");
        yAxis.setTitle(title);
        yAxis.setMinorGridLineWidth(0);
        DataLabels dataLabels = new DataLabels();
        dataLabels.setEnabled(true);
        column.setDataLabels(dataLabels);
        plotOptions.setColumn(column);

        List<Series> seriesList = new ArrayList<Series>();
        //Series seriesAll = new Series();
        Series seriesLine = new Series();
        //seriesAll.setStack("male");
        //seriesAll.setName("All-Line");
        //seriesAll.setData(seriesAllList);

        seriesLine.setStack("'female'");
        seriesLine.setName(lineNo);
        seriesLine.setData(seriesLineList);

        jsonChartsBean.setChart(chart);
        jsonChartsBean.setTitle("");
        //json.subtitle = subtitle;
        jsonChartsBean.setXAxis(xAxis) ;
        jsonChartsBean.setYAxis(yAxis) ;
        //seriesList.add(seriesAll);
        seriesList.add(seriesLine);
        jsonChartsBean.setSeries(seriesList);
        jsonChartsBean.setPlotOptions(plotOptions);
        credits.setEnabled(false);
        jsonChartsBean.setCredits(credits);

        System.gc();
        return new ApiResponse<JsonChartsBean>(true,"",jsonChartsBean);
    }

    @ResponseBody
    @PostMapping("ProductCPK")
    public ApiResponse<JsonChartsBean> getProductCPKChartWithLineNo(@RequestParam("lineNo") String lineNo,@RequestParam("aValue") String aValue){
        //获取设置时间段;
        int timeInterVer = ConstParam.DEFAULTSETTING_boardMachineTimeLimit/5;
        int standardPa = 300;
        int productOneCount =0;
        double dAreaCPK = 0;
        double dVolCPK = 0;
        double dHeightCPK = 0;
        double dShiftXCPK = 0;
        double dShiftYCPK = 0;
        double dUcl=1;
        double dLcl=-1;

        /*if(sDefaultsettingList!=null && sDefaultsettingList.size()>0){
            for (int i = 0; i < sDefaultsettingList.size(); i++) {
                switch (sDefaultsettingList.get(i).getSettingName()){
                    case ConstParam.DEFAULTSETTING_SETTINGNAME_TIMEINTERVER:{
                        timeInterVer = sDefaultsettingList.get(i).getSettingValue()==null?
                                1:Integer.parseInt( sDefaultsettingList.get(i).getSettingValue());
                        break;}
                    case ConstParam.DEFAULTSETTING_SETTINGNAME_STANDARDCAPACITY:{
                        standardPa = sDefaultsettingList.get(i).getSettingValue()==null?
                                300:Integer.parseInt( sDefaultsettingList.get(i).getSettingValue());
                        break;}
                    default :{
                        break;}
                }
                if(sDefaultsettingList.get(i).getSettingName() == ConstParam.DEFAULTSETTING_SETTINGNAME_TIMEINTERVER){
                    timeInterVer = sDefaultsettingList.get(i).getSettingValue()==null?
                            1:Integer.parseInt( sDefaultsettingList.get(i).getSettingValue());
                }
            }
        }
       */ //yyyy-MM-dd HH:mm:ss
        //Date dateStartTime = new Date().;
        Calendar calendar =Calendar.getInstance();
        String endTime =  "";// StringTimeUtils.addHourTimeStrNow(calendar,0);
        String startTime = "";// StringTimeUtils.addHourTimeStrNow(calendar,-(1*timeInterVer));

        JsonChartsBean jsonDefaultTop5ChartsBean = new JsonChartsBean();
        JsonChartsBean jsonCPKChartsBean = new JsonChartsBean();
        List<Series> seriesList = new ArrayList<Series>();
        XAxis xAxis = new XAxis();
        //Title yAxistitle = new Title();
        YAxis yAxis = new YAxis();
        //product chart
        List<String> categoriesList = new ArrayList<String>();
        List<Data> columnSeriesData = new ArrayList<Data>();
        List<Data> splineSeriesData = new ArrayList<Data>();
        List<Data> dAreaCPKListData  = new ArrayList<Data>();
        List<Data> dUclListData = new ArrayList<Data>();
        List<Data> dLclListData  = new ArrayList<Data>();

        double dMaxCPK = 0;
        double dMinCPK = 0;
        Data data = new Data();
        for (int i = 0; i < 5; i++) {
            data = new Data();data.setY(standardPa+0.0);
            splineSeriesData.add(data);
            categoriesList.add(timeInterVer*(1+i)+"H");
            endTime = StringTimeUtils.addHourTimeStrNow(calendar,0);
            startTime = StringTimeUtils.addHourTimeStrNow(calendar,-(1*timeInterVer));
            SPcb sPcb =  sPcb = sPcbService.getPcbListProductCPKWithLineNo(lineNo,startTime,endTime);
            if(sPcb!=null) {
                productOneCount = sPcb.getTotal()==null?0:Integer.parseInt(sPcb.getTotal());
                dAreaCPK = sPcb.getaCpk()==null?0: (double)(Math.round(sPcb.getaCpk()*100)/100.0);
                if(dMinCPK>dAreaCPK){
                    dMinCPK =dAreaCPK;
                }
                if(dMaxCPK<dAreaCPK){
                    dMaxCPK=dAreaCPK;
                }
                dHeightCPK = sPcb.gethCpk()==null?0:(double)(Math.round(sPcb.gethCpk()*100)/100.0);
                if(dMinCPK>dHeightCPK){
                    dMinCPK =dHeightCPK;
                }
                if(dMaxCPK<dHeightCPK){
                    dMaxCPK=dHeightCPK;
                }
                dVolCPK = sPcb.getVcpk()==null?0:(double)(Math.round(sPcb.getVcpk()*100)/100.0);
                if(dMinCPK>dVolCPK){
                    dMinCPK =dVolCPK;
                }
                if(dMaxCPK<dVolCPK){
                    dMaxCPK=dVolCPK;
                }
                dShiftXCPK = sPcb.getShithxCpk()==null?0: (double)(Math.round(sPcb.getShithxCpk()*100)/100.0);
                if(dMinCPK>dShiftXCPK){
                    dMinCPK =dShiftXCPK;
                }
                if(dMaxCPK<dShiftXCPK){
                    dMaxCPK=dShiftXCPK;
                }
                dShiftYCPK = sPcb.getShithyCpk()==null?0: (double)(Math.round(sPcb.getShithyCpk()*100)/100.0);
                if(dMinCPK>dShiftYCPK){
                    dMinCPK =dShiftYCPK;
                }
                if(dMaxCPK<dShiftYCPK){
                    dMaxCPK=dShiftYCPK;
                }
                dUcl =sPcb.getUcl()==null?0:Double.parseDouble(sPcb.getUcl().split(";")[0])*0.1;
                dLcl =sPcb.getUcl()==null?0:Double.parseDouble(sPcb.getLcl().split(";")[0])*0.1;//sPcb.getLcl()==null ?0:sPcb.getLcl();
            }
            data = new Data();data.setY(productOneCount+0.0);
            columnSeriesData.add(data);
            if(aValue!=null && aValue.contains("0")){
                data = new Data();data.setY(dAreaCPK);
                dAreaCPKListData.add(data);
            }else if(aValue!=null && aValue.contains("1")){
                data = new Data();data.setY(dHeightCPK);
                dAreaCPKListData.add(data);
            }else if(aValue!=null && aValue.contains("2")){
                data = new Data();data.setY(dVolCPK);
                dAreaCPKListData.add(data);
            }else if(aValue!=null && aValue.contains("3")){
                data = new Data();data.setY(dShiftXCPK);
                dAreaCPKListData.add(data);
            }else if(aValue!=null && aValue.contains("4")){
                data = new Data();data.setY(dShiftYCPK);
                dAreaCPKListData.add(data);
            }else {
                data = new Data();data.setY(dAreaCPK);
                dAreaCPKListData.add(data);
            }
            data = new Data();data.setY(dUcl);
            dUclListData.add(data);
            data = new Data();data.setY(dLcl);
            dLclListData.add(data);
        }
        xAxis.setCategories(categoriesList);
        xAxis.setMin(0);
        xAxis.setMax(4);
        yAxis.setAllowDecimals(false);
        yAxis.setMin(0);
        yAxis.setMax(standardPa+100);
        Title title = new Title();
        title.setText("pcs(0)");
        yAxis.setTitle(title);
        yAxis.setMinorGridLineWidth(0);
        Series columnSeries = new Series();
        columnSeries.setType("column");
        columnSeries.setName(lineNo);
        columnSeries.setData(columnSeriesData);
        Series splineSeries = new Series();
        splineSeries.setType("spline");
        splineSeries.setName("StandardCapacity");
        splineSeries.setData(splineSeriesData);
        seriesList.add(columnSeries);
        seriesList.add(splineSeries);
        jsonDefaultTop5ChartsBean.setXAxis(xAxis) ;
        jsonDefaultTop5ChartsBean.setYAxis(yAxis) ;
        jsonDefaultTop5ChartsBean.setSeries(seriesList);
        XAxis xAxis1 = new XAxis();
        YAxis yAxis1= new YAxis();
        xAxis1.setCategories(categoriesList);
        xAxis1.setMin(0);
        xAxis1.setMax(4);
        yAxis1.setAllowDecimals(true);
        yAxis1.setMin(-10);//dMinCPK>0?(int)-dMinCPK-10:(int)dMinCPK-10);
        yAxis1.setMax(10);//(int) dMaxCPK<=0?(int)(-dMaxCPK+10):(int)dMaxCPK+10);
        //yAxis1.setMin((int)dUcl);
        //yAxis1.setMax((int)dLcl);
        Title title1 = new Title();
        title1.setText("value(0.0)");
        yAxis1.setTitle(title1);
        List<Series> seriesList1 = new ArrayList<Series>();
        for (int i = 0; i < 3; i++) {
            Series splineSeries1 = new Series();
            splineSeries1.setType("spline");
            switch (i){
                case 0:
                    splineSeries1.setName("cpk");
                    splineSeries1.setData(dAreaCPKListData);
                    break;
                case  1:
                    splineSeries1.setName("ucl");
                    splineSeries1.setData(dUclListData);
                    break;
                case 2:
                    splineSeries1.setName("lcl");
                    splineSeries1.setData(dLclListData);
                    break;
            }
            seriesList1.add(splineSeries1);
        }
        jsonCPKChartsBean.setXAxis(xAxis1);
        jsonCPKChartsBean.setYAxis(yAxis1);
        jsonCPKChartsBean.setSeries(seriesList1);

        /*categoriesList.clear();
        columnSeriesData.clear();
        splineSeriesData.clear();
        dAreaCPKListData.clear();
        dAreaCPKListData.clear();
        dUclListData.clear();
        dLclListData.clear();*/
        System.gc();
        return new ApiResponse<JsonChartsBean>(true,"",jsonDefaultTop5ChartsBean,jsonCPKChartsBean);
    }





}

