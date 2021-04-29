package com.sinictek.spm.api;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.annotation.LoginToken;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.JsonchartModel.*;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SLineService;
import com.sinictek.spm.service.SPcbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * <p>
 * 线体总表 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/sLine")
public class SLineController {

    @Autowired
    SPcbService sPcbService;
    @Autowired
    SLineService sLineService;


    @GetMapping("/line")
    public ModelAndView showLine()  {
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "line";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        //mv.addObject("weburl","/sLine/pcbLine?");
        return  mv;
    }

    @GetMapping("pcbLine")
    public ModelAndView showPcbLine()  {
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = false;//ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        try{
            int i = StringTimeUtils.getTimeStringToDate("2021-04-25 00:00:00").compareTo(new Date());
            if(i > 0  ){
                bCmBoxs = true;
            }else{
                bCmBoxs = false;
            };
        }catch (Exception e){
        }
        String viewName = "spi/pcbLineData";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        //ModelAndView mv = new ModelAndView("spi/pcbLineData");
        mv.addObject("hChartColor", ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        mv.addObject("weburl","/sLine/pcbLine?");
        return  mv;
    }


    /***
     * 获取SPI全部线体信息
     * @return apiResponse
     */
    @GetMapping("/lineList")
    @ResponseBody
    public ApiResponse getLineList(){

        return new ApiResponse(true,null, sLineService.selectList(Condition.create().eq("create_time","21000101")));

    }

    @ResponseBody
    @GetMapping(value = "jsonPcbLine",  produces = "application/json;charset=UTF-8")
    public ApiResponse getJsonPcbLine(@RequestParam("inspectStarttime") String inspectStarttime,
                                      @RequestParam("inspectEndtime") String inspectEndtime ,
                                      @RequestParam("mode") String mode ,
                                      @RequestParam("pcbType") String pcbType) throws ParseException {

        if(inspectStarttime ==null || inspectEndtime ==null){
            inspectStarttime= StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-24);
            inspectEndtime = StringTimeUtils.getTimeDateToString(new Date());
        }
        SPcb pcb=null;
        List<SPcb> lstPcb = new ArrayList<SPcb>();
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
        goodSeries.setName(ConstController.constController.getStringByLocalContextHolder("line.pass"));
        Series ngSeries = new Series();
        ngSeries.setName(ConstController.constController.getStringByLocalContextHolder("line.ng"));
        Series passSeries = new Series();
        passSeries.setName(ConstController.constController.getStringByLocalContextHolder("line.rePass"));
        List<Data> lstGoodSeriesData = new ArrayList<Data>();
        List<Data> lstNgSeriesData = new ArrayList<Data>();
        List<Data> lstPassSeriesData = new ArrayList<Data>();

        List<Series> lstPcbSeries = new ArrayList<Series>();
        Series pcbCountSeries = new Series();
        pcbCountSeries.setName(ConstController.constController.getStringByLocalContextHolder("line.pcbCount"));
        Series passPcbCountSeries = new Series();
        passPcbCountSeries.setName(ConstController.constController.getStringByLocalContextHolder("line.rePassPcbCount"));
        Series ngPadCountSeries = new Series();
        ngPadCountSeries.setName(ConstController.constController.getStringByLocalContextHolder("line.defectCount"));
        List<Data> lstpcbCountSeriesData = new ArrayList<Data>();
        List<Data> lstpassPcbCountSeriesData = new ArrayList<Data>();
        List<Data> lstngPadCountSeriesData = new ArrayList<Data>();

        Data data = new Data();
        double dAVGGood=0.0,dAVGNg=0.0,dAVGPass=0.0;
        double dAVGpcbCount=0.0,dAVGpassPcbCount=0.0,dAVGPadCount=0.0;
        switch (mode){
            case "1": {
                lstPcb = sPcbService.getPcbListWithALLLine(inspectStarttime,inspectEndtime);
                if(lstPcb!=null && lstPcb.size()>0) {
                    double dTmp=0.0;
                    for (int i = 0; i < lstPcb.size(); i++) {

                        double goodarrayCount = (lstPcb.get(i).getGoodarrayCount()==null )? 0:Double.parseDouble(lstPcb.get(i).getGoodarrayCount()),
                        ngarrayCount = (lstPcb.get(i).getNgarrayCount()==null )? 0:Double.parseDouble(lstPcb.get(i).getNgarrayCount()),
                        passarrayCount = (lstPcb.get(i).getPassarrayCount()==null )? 0:Double.parseDouble(lstPcb.get(i).getPassarrayCount());
                        if(StringUtils.equals("1",pcbType)){
                            dTmp = lstPcb.get(i).getGoodPcbYeild()==null?0: new BigDecimal(Double.parseDouble(lstPcb.get(i).getGoodPcbYeild() )).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp = goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(goodarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0);
                        }else{
                            dTmp = lstPcb.get(i).getGoodpadYeild()==null?0:(double)(Math.round(Double.parseDouble(lstPcb.get(i).getGoodpadYeild()) *100)/100.0);
                        }
                        //dTmp =lstPcb.get(i).getGoodPcbYeild()==null?0.0: new BigDecimal(Double.parseDouble(lstPcb.get(i).getGoodPcbYeild() )).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        dAVGGood +=  dTmp;

                        data = new Data();
                         data.setY(dTmp);
                        lstGoodSeriesData.add(data);

                        if(StringUtils.equals("1",pcbType)){
                            dTmp =lstPcb.get(i).getNgPcbYeild()==null?0.0:new BigDecimal( Double.parseDouble(lstPcb.get(i).getNgPcbYeild())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() ;
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp=goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(ngarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0);
                        }else{
                            dTmp =lstPcb.get(i).getNgpadYeild()==null?0:(double)(Math.round(Double.parseDouble(lstPcb.get(i).getNgpadYeild()) *100)/100.0);
                        }
                        dAVGNg+=dTmp ;
                        data = new Data();data.setY(dTmp);
                        lstNgSeriesData.add(data);

                        if(StringUtils.equals("1",pcbType)){
                            dTmp =lstPcb.get(i).getPassPcbYeild()==null?0:new BigDecimal( Double.parseDouble(lstPcb.get(i).getPassPcbYeild())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp=goodarrayCount+ngarrayCount+passarrayCount==0?0:(double)(Math.round(passarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0);
                        }else{
                            dTmp =lstPcb.get(i).getPasspadYeild()==null?0:(double)(Math.round(Double.parseDouble(lstPcb.get(i).getPasspadYeild()) *100)/100.0);
                        }

                        dAVGPass+= dTmp ;
                        data = new Data();data.setY(dTmp);
                        lstPassSeriesData.add(data);
                        lstCategoriesYeild.add(lstPcb.get(i).getLineNo());

                        //pcb count

                        if(StringUtils.equals("1",pcbType)){
                            dTmp = lstPcb.get(i).getTotal() ==null ? 0:Double.parseDouble(lstPcb.get(i).getTotal());
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp=goodarrayCount+ngarrayCount+passarrayCount;
                        }else{
                            dTmp = lstPcb.get(i).getTotal() ==null ? 0:Double.parseDouble(lstPcb.get(i).getTotal());
                        }

                        data = new Data();data.setY(dTmp);
                        lstpcbCountSeriesData.add(data);
                        dAVGpcbCount+=dTmp;
                        //pass pcb count
                        if(StringUtils.equals("1",pcbType)){
                            dTmp=lstPcb.get(i).getPassPcbCount()==null?0:Double.parseDouble(lstPcb.get(i).getPassPcbCount());
                        }else if(StringUtils.equals("2",pcbType)){
                            dTmp=passarrayCount;
                        }else{
                            dTmp=lstPcb.get(i).getPassPcbCount()==null?0:Double.parseDouble(lstPcb.get(i).getPassPcbCount());
                        }
                        data = new Data();data.setY(dTmp);
                        lstpassPcbCountSeriesData.add(data);
                        dAVGpassPcbCount+=dTmp;
                        //pad 缺陷趋势
                        dTmp = lstPcb.get(i).getNgpadCount()==null?0.0:lstPcb.get(i).getNgpadCount()+0.0;
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
                goodSeries.setColor("#22B14C");//22B14C 92ff4e
                goodSeries.setData(lstGoodSeriesData);
                goodSeries.setStack("0");

                ngSeries.setType("column");
                ngSeries.setColor("#DC334D");  //B84A5B  ff4c39
                ngSeries.setData(lstNgSeriesData);
                ngSeries.setStack("0");

                passSeries.setType("column");
                passSeries.setColor("#007AAE");//007AAE  211cff
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
                passPcbCountSeries.setColor("#007AAE");
                ngPadCountSeries.setType("column");
                ngPadCountSeries.setData(lstngPadCountSeriesData);
                ngPadCountSeries.setStack("0");
                ngPadCountSeries.setColor("#DC334D");

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
                    pcb = sPcbService.getPcbListWithALLLineByDateNoGroup(inspectStarttime, inspectEndtime);

                    inspectStarttime = inspectEndtime; //开始时间=结束时间
                    if (pcb != null) {

                        double goodarrayCount = pcb.getGoodarrayCount()==null? 0:Double.parseDouble(pcb.getGoodarrayCount()),
                                ngarrayCount = pcb.getNgarrayCount()==null ? 0:Double.parseDouble(pcb.getNgarrayCount()),
                                passarrayCount = pcb.getPassarrayCount()==null ? 0:Double.parseDouble(pcb.getPassarrayCount()),
                                arrayTotalCount = goodarrayCount+ngarrayCount+passarrayCount
                        ;

                        double goodpcbCount = pcb.getGoodPcbCount()==null? 0:Double.parseDouble(pcb.getGoodPcbCount()),
                                ngpcbCount = pcb.getNgPcbCount()==null ? 0:Double.parseDouble(pcb.getNgPcbCount()),
                                passpcbCount = pcb.getPassPcbCount()==null ? 0:Double.parseDouble(pcb.getPassPcbCount()),
                                pcbTotalCount = goodpcbCount+ngpcbCount+passpcbCount
                        ;
                        double goodpadCount = pcb.getGoodpadCount()==null? 0:pcb.getGoodpadCount(),
                                ngpadCount = pcb.getNgpadCount()==null? 0:pcb.getNgpadCount(),
                                passpadCount = pcb.getPasspadCount()==null? 0:pcb.getPasspadCount(),
                                padTotalCount = goodpadCount+ngpadCount+passpadCount
                        ;

                        //直通率
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcbTotalCount==0?0:(double)(Math.round(goodpcbCount*100/pcbTotalCount*100)/100.0));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(arrayTotalCount==0?0:(double)(Math.round(goodarrayCount*100/arrayTotalCount*100)/100.0));
                        }else{
                            data.setY(padTotalCount==0?0:(double)(Math.round(goodpadCount*100/padTotalCount*100)/100.0));
                        }
                        lstGoodSeriesData.add(data);
                        //不良率
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcbTotalCount==0?0:(double)(Math.round(ngpcbCount*100/pcbTotalCount*100)/100.0));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(arrayTotalCount==0?0:(double)(Math.round(ngarrayCount*100/arrayTotalCount*100)/100.0));
                        }else{
                            data.setY(padTotalCount==0?0:(double)(Math.round(ngpadCount*100/(padTotalCount)*100)/100.0));
                        }
                         lstNgSeriesData.add(data);
                        //误判率
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcbTotalCount==0?0:(double)(Math.round(goodpcbCount*100/pcbTotalCount*100)/100.0));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(arrayTotalCount==0?0:(double)(Math.round(passarrayCount*100/arrayTotalCount*100)/100.0));
                        }else{
                            data.setY(padTotalCount==0?0:(double)(Math.round(passpadCount*100/padTotalCount*100)/100.0));
                        }
                        lstPassSeriesData.add(data);

                        //pcb count
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcb.getTotal() ==null ? 0:Double.parseDouble( pcb.getTotal()));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(goodarrayCount+ngarrayCount+passarrayCount);
                        }else{
                            data.setY(pcb.getTotal() ==null ? 0:Double.parseDouble( pcb.getTotal()));
                        }
                        lstpcbCountSeriesData.add(data);
                        //pass pcb count
                        data = new Data();
                        if(StringUtils.equals("1",pcbType)){
                            data.setY(pcb.getPassPcbCount()==null?0:Double.parseDouble(pcb.getPassPcbCount()));
                        }else if(StringUtils.equals("2",pcbType)){
                            data.setY(passarrayCount);
                        }else{
                            data.setY(pcb.getTotal() ==null ? 0:Double.parseDouble( pcb.getTotal()));
                        }

                        lstpassPcbCountSeriesData.add(data);
                        //pad 缺陷趋势
                        data = new Data();data.setY(pcb.getNgpadCount()==null?0.0:pcb.getNgpadCount()+0.0);
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
                goodSeries.setColor("#22B14C");//22B14C  92ff4e
                ngSeries.setType("areaspline");
                ngSeries.setData(lstNgSeriesData);
                ngSeries.setColor("#F47378"); //B84A5B  ff4c39
                passSeries.setType("areaspline");
                passSeries.setData(lstPassSeriesData);
                passSeries.setColor("#007AAE"); //007AAE  211cff

                lstSeries.add(goodSeries);
                lstSeries.add(passSeries);
                lstSeries.add(ngSeries);

                pcbCountSeries.setType("areaspline");
                pcbCountSeries.setData(lstpcbCountSeriesData);
                passPcbCountSeries.setType("areaspline");
                passPcbCountSeries.setData(lstpassPcbCountSeriesData);
                passPcbCountSeries.setColor("#007AAE");
                ngPadCountSeries.setType("areaspline");
                ngPadCountSeries.setData(lstngPadCountSeriesData);
                ngPadCountSeries.setColor("#DC334D");

                lstPcbSeries.add(pcbCountSeries);
                lstPcbSeries.add(ngPadCountSeries);
                lstPcbSeries.add(passPcbCountSeries);

                break;
            }
        }
        jsonChartsBeanYeild.setSeries(lstSeries);
        jsonChartsBeanYeild.setXAxis(axisYeild);
        jsonChartsBeanContainerline.setSeries(lstPcbSeries);

        try{
            return  new ApiResponse(true,null,jsonChartsBeanYeild,jsonChartsBeanContainerline);
        }catch (Exception e){
        }finally {
            jsonChartsBeanYeild = null;
            jsonChartsBeanContainerline=null;
            lstGoodSeriesData =null;lstNgSeriesData=null;   lstPassSeriesData=null;   lstPassSeriesData=null;   lstpcbCountSeriesData =null;  lstpassPcbCountSeriesData=null;   lstngPadCountSeriesData=null;
            lstpcbCountSeriesData=null;lstCategoriesYeild =null; lstGoodSeriesData=null;   lstNgSeriesData =null; lstPassSeriesData=null;
            lstpassPcbCountSeriesData=null;
            lstngPadCountSeriesData=null;
            lstPassSeriesData=null;
            pcbCountSeries=null;
            pcbCountSeries=null;
            ngPadCountSeries=null;
            passPcbCountSeries=null;
            lstPcb=null;
            lstPcbSeries=null;
            axisYeild=null;
            lstSeries=null;
            System.gc();
        }
        return  new ApiResponse(true,null,jsonChartsBeanYeild,jsonChartsBeanContainerline);

    }


    @ResponseBody
    @GetMapping("pcbTableLine")
    public ApiResponse getJsonPcbTableLine(@RequestParam("inspectStarttime") String inspectStarttime,
                                           @RequestParam("inspectEndtime") String inspectEndtime){
        if(inspectStarttime ==null || inspectEndtime ==null){
            inspectStarttime= StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-24);
            inspectEndtime = StringTimeUtils.getTimeDateToString(new Date());
        }
        List<SPcb> lstPcb = sPcbService.getPcbListWithALLLine(inspectStarttime,inspectEndtime);
        return new ApiResponse(true,null,null,lstPcb
                );
    }

    @GetMapping("pcbLineDetails")
    public ModelAndView showPcbLineDetails(@RequestParam("lineNo") String lineNo,
                                           @RequestParam("inspectStarttime") String inspectStarttime,
                                           @RequestParam("inspectEndtime") String inspectEndtime,
                                           @RequestParam("pcbType") String pcbType){
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = false;//ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        try{
            int i = StringTimeUtils.getTimeStringToDate("2021-04-25 00:00:00").compareTo(new Date());
            if(i > 0  ){
                bCmBoxs = true;
            }else{
                bCmBoxs = false;
            };
        }catch (Exception e){
        }
        String viewName = "spi/pcbDataDetails";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("pcbType",pcbType);
        mv.addObject("lineNo",lineNo);
        mv.addObject("inspectStarttime",inspectStarttime);
        mv.addObject("inspectEndtime",inspectEndtime);
        mv.addObject("hChartColor",ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        mv.addObject("weburl","/sLine/pcbLineDetails?" +
                "pcbType="+pcbType+
                "&lineNo="+lineNo+
                "&inspectStarttime="+inspectStarttime+
                "&inspectEndtime="+inspectEndtime+ "&"
        );
        return mv;
    }


    @GetMapping("pcbLineDetailsNew")
    public ModelAndView showPcbLineDetailsNew(@RequestParam("lineNo") String lineNo,
                                           @RequestParam("inspectStarttime") String inspectStarttime,
                                           @RequestParam("inspectEndtime") String inspectEndtime,
                                           @RequestParam("pcbType") String pcbType){
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "spiPcbChartDetail";
        if(bCmBoxs){
        }else {
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("pcbType",pcbType);
        mv.addObject("lineNo",lineNo);
        mv.addObject("inspectStarttime",inspectStarttime);
        mv.addObject("inspectEndtime",inspectEndtime);
        mv.addObject("hChartColor",ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        mv.addObject("weburl","/sLine/pcbLineDetails?" +
                "pcbType="+pcbType+
                "&lineNo="+lineNo+
                "&inspectStarttime="+inspectStarttime+
                "&inspectEndtime="+inspectEndtime+ "&"
        );
        return mv;
    }


    @ResponseBody
    @GetMapping(value="lineDetailsLeftChart",produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse getJsonlineDetailsLeftChart(@RequestParam("lineNo") String lineNo,
                                                   @RequestParam("inspectStarttime") String inspectStarttime,
                                                   @RequestParam("inspectEndtime") String inspectEndtime,
                                                   @RequestParam("pcbResult") String pcbResult,
                                                   @RequestParam("pcbType") String pcbType)
    {

        if(pcbResult==null || pcbResult==""){
            pcbResult = "'0','1','2','4'"; //"good ng pass skip"
        }
        List<SPcb>  lstPcb = sPcbService.getPcbListWithDateAndLineNoPcbResult(lineNo,inspectStarttime,inspectEndtime,pcbResult);
        int totleNgPcb = 0;
        int totlePassPcb =0;
        int totleGoodPcb =0;
        List<Series> seriesLst = new ArrayList<Series>();
        String strPieServies = "";
        strPieServies +="[";
        if(lstPcb!=null && lstPcb.size()>0){
            for (int i = 0; i < lstPcb.size(); i++) {
                if(pcbType.contains("1") || pcbType.contains("3") ) {
                    if (lstPcb.get(i).getInspectResult().contains("0")) {
                        totleGoodPcb++;
                    } else if (lstPcb.get(i).getInspectResult().contains("1")) {
                        totleNgPcb++;
                    } else if (lstPcb.get(i).getInspectResult().contains("2")) {
                        totlePassPcb++;
                    }
                }else{
                    if(lstPcb.get(i).getArrayinspectResult()!=null
                        &&lstPcb.get(i).getArrayinspectResult()!=""){
                        totleNgPcb+=org.apache.commons.lang3.StringUtils.countMatches(lstPcb.get(i).getArrayinspectResult(),"NG");
                        totleGoodPcb+=org.apache.commons.lang3.StringUtils.countMatches(lstPcb.get(i).getArrayinspectResult(),"Good");
                        totlePassPcb+=org.apache.commons.lang3.StringUtils.countMatches(lstPcb.get(i).getArrayinspectResult(),"Pass");
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


}

