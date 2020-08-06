package com.sinictek.spm.api;


import com.sinictek.spm.model.JsonchartModel.*;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SPcbService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("pcbLine")
    public ModelAndView showPcbLine()  {
        ModelAndView mv = new ModelAndView("spi/pcbLineData");
        return  mv;
    }

    @ResponseBody
    @GetMapping(value = "jsonPcbLine",  produces = "application/json;charset=UTF-8")
    public ApiResponse getJsonPcbLine(@RequestParam("inspectStarttime") String inspectStarttime,
                                      @RequestParam("inspectEndtime") String inspectEndtime ,
                                      @RequestParam("mode") String mode) throws ParseException {

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
        goodSeries.setName("PCB直通率(%)");
        Series ngSeries = new Series();
        ngSeries.setName("不良率(%)");
        Series passSeries = new Series();
        passSeries.setName("误报率(%)");
        List<Double> lstGoodSeriesData = new ArrayList<Double>();
        List<Double> lstNgSeriesData = new ArrayList<Double>();
        List<Double> lstPassSeriesData = new ArrayList<Double>();

        List<Series> lstPcbSeries = new ArrayList<Series>();
        Series pcbCountSeries = new Series();
        pcbCountSeries.setName("总PCB个数");
        Series passPcbCountSeries = new Series();
        passPcbCountSeries.setName("复判PASS-PCB个数");
        Series ngPadCountSeries = new Series();
        ngPadCountSeries.setName("不良点个数");
        List<Double> lstpcbCountSeriesData = new ArrayList<Double>();
        List<Double> lstpassPcbCountSeriesData = new ArrayList<Double>();
        List<Double> lstngPadCountSeriesData = new ArrayList<Double>();
        double dAVGGood=0.0,dAVGNg=0.0,dAVGPass=0.0;
        double dAVGpcbCount=0.0,dAVGpassPcbCount=0.0,dAVGPadCount=0.0;
        switch (mode){
            case "1": {
                lstPcb = sPcbService.getPcbListWithALLLine(inspectStarttime,inspectEndtime);
                if(lstPcb!=null && lstPcb.size()>0) {
                    double dTmp=0.0;
                    for (int i = 0; i < lstPcb.size(); i++) {
                        dTmp =lstPcb.get(i).getGoodPcbYeild()==null?0.0: new BigDecimal(Double.parseDouble(lstPcb.get(i).getGoodPcbYeild() )).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        dAVGGood +=  dTmp;
                        lstGoodSeriesData.add(dTmp);

                        dTmp =lstPcb.get(i).getNgPcbYeild()==null?0.0:new BigDecimal( Double.parseDouble(lstPcb.get(i).getNgPcbYeild())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() ;
                        dAVGNg+=dTmp ;
                        lstNgSeriesData.add(dTmp);

                        dTmp =lstPcb.get(i).getPassPcbYeild()==null?0.0:new BigDecimal( Double.parseDouble(lstPcb.get(i).getPassPcbYeild())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                        dAVGPass+= dTmp ;
                        lstPassSeriesData.add(dTmp);
                        lstCategoriesYeild.add(lstPcb.get(i).getLineNo());

                        //pcb count
                        dTmp = lstPcb.get(i).getTotal() ==null ? 0.0:Double.parseDouble(lstPcb.get(i).getTotal());
                        lstpcbCountSeriesData.add(dTmp);
                        dAVGpcbCount+=dTmp;
                        //pass pcb count
                        dTmp=lstPcb.get(i).getPassPcbCount()==null?0:Double.parseDouble(lstPcb.get(i).getPassPcbCount());
                        lstpassPcbCountSeriesData.add(dTmp);
                        dAVGpassPcbCount+=dTmp;
                        //pad 缺陷趋势
                        dTmp = lstPcb.get(i).getNgpadCount()==null?0.0:lstPcb.get(i).getNgpadCount()+0.0;
                        lstngPadCountSeriesData.add(dTmp);
                        dAVGPadCount+=dTmp;
                    }
                }
                lstCategoriesYeild.add("ALL-LINE");
               // List<Double> lists=null;
                //DoubleSummaryStatistics statistics = lstPcb.stream().mapToDouble(Number::doubleValue).summaryStatistics();

                lstGoodSeriesData.add( lstPcb.size()>0?new BigDecimal(dAVGGood/lstPcb.size()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue():0.0);
                lstNgSeriesData.add( lstPcb.size()>0?new BigDecimal(dAVGNg/lstPcb.size()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue():0.0);
                lstPassSeriesData.add( lstPcb.size()>0?new BigDecimal(dAVGPass/lstPcb.size()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue():0.0);

                lstpcbCountSeriesData.add( lstPcb.size()>0?dAVGpcbCount:0.0);
                lstpassPcbCountSeriesData.add( lstPcb.size()>0?dAVGpassPcbCount:0.0);
                lstngPadCountSeriesData.add( lstPcb.size()>0?dAVGPadCount:0.0);

                goodSeries.setType("column");
                goodSeries.setColor("#211cff");
                goodSeries.setData(lstGoodSeriesData);
                goodSeries.setStack("0");

                ngSeries.setType("column");
                ngSeries.setColor("#ff4c39");
                ngSeries.setData(lstNgSeriesData);
                ngSeries.setStack("0");

                passSeries.setType("column");
                passSeries.setColor("#92ff4e");
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
                    pcb = sPcbService.getPcbListWithALLLineByDateNoGroup(inspectStarttime, inspectEndtime);
                    inspectStarttime = inspectEndtime; //开始时间=结束时间
                    if (pcb != null) {
                        //直通率
                        lstGoodSeriesData.add(pcb.getGoodPcbYeild() == null ? 0.0 : Double.parseDouble(pcb.getGoodPcbYeild()));
                        //不良率
                        lstNgSeriesData.add(pcb.getNgPcbYeild() == null ? 0.0 : Double.parseDouble(pcb.getNgPcbYeild()));
                        //误判率
                        lstPassSeriesData.add(pcb.getPassPcbYeild() == null ? 0.0 : Double.parseDouble(pcb.getPassPcbYeild()));

                        //pcb count
                        lstpcbCountSeriesData.add(pcb.getTotal() ==null ? 0:Double.parseDouble( pcb.getTotal()));
                        //pass pcb count
                        lstpassPcbCountSeriesData.add(pcb.getPassPcbCount()==null?0:Double.parseDouble(pcb.getPassPcbCount()));
                        //pad 缺陷趋势
                        lstngPadCountSeriesData.add(pcb.getNgpadCount()==null?0.0:pcb.getNgpadCount()+0.0);
                    } else {
                        //直通率
                        lstGoodSeriesData.add(0.0);
                        //不良率
                        lstNgSeriesData.add(0.0);
                        //误判率
                        lstPassSeriesData.add(0.0);
                        //pcb count
                        lstpcbCountSeriesData.add(0.0);
                        //pass pcb count
                        lstpassPcbCountSeriesData.add(0.0);
                        //pad 缺陷趋势
                        lstngPadCountSeriesData.add(0.0);
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
                goodSeries.setColor("#211cff");
                ngSeries.setType("areaspline");
                ngSeries.setData(lstNgSeriesData);
                ngSeries.setColor("#ff4c39");
                passSeries.setType("areaspline");
                passSeries.setData(lstPassSeriesData);
                passSeries.setColor("#92ff4e");

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


    @ResponseBody()
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
                                           @RequestParam("inspectEndtime") String inspectEndtime){
        ModelAndView mv = new ModelAndView("spi/pcbDataDetails");
        mv.addObject("lineNo",lineNo);
        mv.addObject("inspectStarttime",inspectStarttime);
        mv.addObject("inspectEndtime",inspectEndtime);

        return mv;
    }

    @ResponseBody
    @GetMapping("lineDetailsLeftChart")
    public ApiResponse getJsonlineDetailsLeftChart(@RequestParam("lineNo") String lineNo,
                                                   @RequestParam("inspectStarttime") String inspectStarttime,
                                                   @RequestParam("inspectEndtime") String inspectEndtime,
                                                   @RequestParam("pcbResult") String pcbResult)
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
                if(lstPcb.get(i).getInspectResult().contains("0")){
                    totleGoodPcb++;
                }else if(lstPcb.get(i).getInspectResult().contains("1")){
                    totleNgPcb++;
                }else if(lstPcb.get(i).getInspectResult().contains("2")){
                    totlePassPcb++;
                }
            }
        }
        strPieServies+= "[\"REPASS\","+totlePassPcb+"]," +
                        "[\"NG\","+totleNgPcb+"],"+
                        "[\"PASS\","+totleGoodPcb +"]" +
                        "]" ;

        System.gc();
        return  new ApiResponse(true,null,strPieServies,lstPcb);
    }


}

