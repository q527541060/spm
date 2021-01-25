package com.sinictek.spm.api;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.Condition;
import com.sinictek.spm.model.APcb;
import com.sinictek.spm.model.AStatus;
import com.sinictek.spm.model.ConstClasses.ConstController;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.ConstClasses.ConstPublicClassUtil;
import com.sinictek.spm.model.JsonchartModel.*;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.SStatus;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.APcbService;
import com.sinictek.spm.service.AStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @Author sinictek-pd
 * @Date 2020/7/23 17:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/aStatus")
public class AStatusController {

    @Autowired
    AStatusService aStatusService;
    @Autowired
    APcbService aPcbService;

    @RequestMapping("pcbMonitorview_realLineView")
    public ModelAndView ShowPcbMonitorView_AOI(@RequestParam("aoiType")String aoiType){
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "aoi/pcbMonitorview_realLineView_aoi";
        if(bCmBoxs){
        }else{
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("aoiType",aoiType);
        mv.addObject("boardMachineRefreshTime", ConstParam.DEFAULTSETTING_boardMachineRefreshTime);
        mv.addObject("Frequency_start",ConstParam.DEFAULTSETTING_FrequencyStart);
        mv.addObject("hChartColor",ConstParam.DEFAULTSETTING_hChartColor);
        mv.addObject("backgroundColor",ConstParam.DEFAULTSETTING_backgroundColor);
        mv.addObject("passPcbYeild",ConstParam.DEFAULTSETTING_passPcbYeild);
        mv.addObject("boardView_chartMove",ConstParam.DEFAULTSETTING_boardViewChartMove);

        return  mv;
    }

    @ResponseBody
    @RequestMapping("aoi/pcbMonitorJson")
    public ApiResponse GetPcbMonitorListJson_AOI(){

        //获取所有线体;
        //List<SLine> lineList = sLineService.selectList(null);
        //List<SStatus> statusList = sStatusService.getAllStatusWithLineNoLimt();
        //sStatusService.selectByMap()
        //ApiResponse<ModelMap> api = new ApiResponse<ModelMap>();
        //String strJson =
               /* "{\"total\":\"364\", rows:[{\"equipmentNo\":\"adf\"," +
                "\"lineNo\":\"SPI-01\",\"status\":\"stop\",\"factory\":\"Ssssss\" ,\"errContent\":\"eeeeee\"" +
                "}]";*/
        //JSON.toJSONString(statusList);
        return new ApiResponse(null,aStatusService.getAllStatusWithLineNoLimt_AOI());
    }


    @ResponseBody
    @GetMapping("pcbMonitorJson")
    public ApiResponse<List<AStatus>> GetPcbMonitorListJson(@RequestParam("lane") String lane,@RequestParam("aoiType")String aoiType){

        //获取所有线体;
        //List<SLine> lineList = sLineService.selectList(null);
        //List<SStatus> statusList = sStatusService.getAllStatusWithLineNoLimt();
        //sStatusService.selectByMap()
        //ApiResponse<ModelMap> api = new ApiResponse<ModelMap>();
        //String strJson =
               /* "{\"total\":\"364\", rows:[{\"equipmentNo\":\"adf\"," +
                "\"lineNo\":\"SPI-01\",\"status\":\"stop\",\"factory\":\"Ssssss\" ,\"errContent\":\"eeeeee\"" +
                "}]";*/
        //JSON.toJSONString(statusList);
        return new ApiResponse<List<AStatus>>(null,aStatusService.getAllStatusWithLineNoLimt(lane,aoiType));
    }

    @ResponseBody
    @GetMapping("pcbMonitorview_realLineViewJson")
    public ApiResponse GetpcbMonitorview_realLineViewJson(@RequestParam("aValue") String aValue,
                                                          @RequestParam("mode") String iMode,
                                                          @RequestParam("aoiType")String aoiType){
        double dAreaCPK = 0;
        double dVolCPK = 0;
        double dHeightCPK = 0;
        double dShiftXCPK = 0;
        double dShiftYCPK = 0;
        Date sDate = new Date();
        //sDate.setHours(ConstParam.DEFAULTSETTING_FrequencyStart);
        int iSettingHour = ConstParam.DEFAULTSETTING_FrequencyStart;
        Calendar instance = Calendar.getInstance();
        String stratTime=null;
        String endTime=null;
        if(iSettingHour>=100){
            String tmpHour = ConstParam.DEFAULTSETTING_FrequencyStart+"";
            stratTime =instance.get(instance.YEAR)+"-"+(instance.get(instance.MONTH)+1)+"-"+(instance.get(instance.DAY_OF_MONTH)-1)+
                    " "+tmpHour.substring(0,tmpHour.length()-2) + ":00:00";//
            endTime = StringTimeUtils.getTimeDateToString(sDate);
        }else{
            stratTime =instance.get(instance.YEAR)+"-"+(instance.get(instance.MONTH)+1)+"-"+instance.get(instance.DAY_OF_MONTH)+
                    " "+ConstParam.DEFAULTSETTING_FrequencyStart + ":00:00";//
            endTime = StringTimeUtils.getTimeDateToString(sDate);
        }
        //记得改回班次
       /* Calendar instance = Calendar.getInstance();
        String stratTime =instance.get(instance.YEAR)+"-"+(instance.get(instance.MONTH)+1)+"-"+instance.get(instance.DAY_OF_MONTH)+
                " "+ConstParam.DEFAULTSETTING_FrequencyStart + ":00:00";//StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-ConstParam.DEFAULTSETTING_FrequencyStart);

        String endTime = StringTimeUtils.getTimeDateToString(sDate);*/
        //获取到每条线计算后总数据
        List<APcb> lstPcb = aPcbService.getPcbListWithALLLine(stratTime,endTime,aoiType);
        //FPY、PROFUCT
        JsonChartsBean jsonChartsBean_FPYProduct = new JsonChartsBean();
        //CPK
        JsonChartsBean jsonChartsBean_CPK = new JsonChartsBean();
        //Top5
        JsonChartsBean jsonChartsBean_Top5 = new JsonChartsBean();


        List<Series> lstFPYProductSeries = new ArrayList<Series>();
        Series productGoodSeries = new Series();
        Series productPassSeries = new Series();
        Series productNGSeries = new Series();
        Series productSplineSeries = new Series();

        List<Series> lstCPKSeries = new ArrayList<Series>();
        Series cpkSeries = new Series();
        //Series standCpkSeries = new Series();

        List<Series> lstTop5Series = new ArrayList<Series>();
        Series  top5Series  = null;//new Series();
        //Series standCpkSeries = new Series();

        XAxis xAxisFPYProduct = new XAxis();
        XAxis xAxisCPK = new XAxis();
        List<String> lstFPYProductCategories = new ArrayList<String>();
        List<Data> goodFPYProductSeriesList = new ArrayList<Data>();
        List<Data> passFPYProductSeriesList = new ArrayList<Data>();
        List<Data> ngFPYProductSeriesList = new ArrayList<Data>();
        List<Data> goodFPYProductSplineSeriesList = new ArrayList<Data>();
        List<Data> cpkSeriesList = new ArrayList<Data>();
        // List<Data> standCPKSeriesList = new ArrayList<Data>();
        List<Data> top5SeriesList = new ArrayList<Data>();

        List<PlotBands> lstPlotBands = new ArrayList<PlotBands>();
        Data data =null;
        double doubleTmp=0;
        //FPY、PRODUCT、CPK
        double maxCpk =0;
        long iTotal =0,iPcbTmp=0;
        if(lstPcb!=null && lstPcb.size()>0){
            for (int i = 0; i < lstPcb.size(); i++){
                data = new Data();
                lstFPYProductCategories.add(lstPcb.get(i).getLineNo()+"");
                double goodarrayCount = 0,ngarrayCount = 0,passarrayCount = 0;
                if(StringUtils.equals("1",iMode)){
                    data.setY((lstPcb.get(i).getGoodPcbCountAoi()==null )? 0:Double.parseDouble(lstPcb.get(i).getGoodPcbCountAoi()));
                }else if(StringUtils.equals("2",iMode)){
                    goodarrayCount = (lstPcb.get(i).getGoodArrayCount()==null)? 0:Double.parseDouble(lstPcb.get(i).getGoodArrayCount()+"");
                    data.setY(goodarrayCount);
                }else{
                    data.setY((lstPcb.get(i).getGoodComponentCount()==null )? 0:lstPcb.get(i).getGoodComponentCount());
                }
                goodFPYProductSeriesList.add(data);
                data = new Data();
                if(StringUtils.equals("1",iMode)){
                    data.setY((lstPcb.get(i).getPassPcbCountAoi()==null)?0:Double.parseDouble(lstPcb.get(i).getPassPcbCountAoi()));
                }else if(StringUtils.equals("2",iMode)){
                    passarrayCount =(lstPcb.get(i).getPassArrayCount()==null)? 0:Double.parseDouble(lstPcb.get(i).getPassArrayCount()+"");
                    data.setY(passarrayCount);
                }else{
                    data.setY((lstPcb.get(i).getPassComponentCount()==null )? null:lstPcb.get(i).getPassComponentCount());
                }

                passFPYProductSeriesList.add(data);
                data = new Data();
                if(StringUtils.equals("1",iMode)){
                    data.setY((lstPcb.get(i).getNgPcbCountAoi()==null )?0:Double.parseDouble(lstPcb.get(i).getNgPcbCountAoi()));
                }else if(StringUtils.equals("2",iMode)){
                    ngarrayCount=(lstPcb.get(i).getNgArrayCount()==null )? 0:Double.parseDouble(lstPcb.get(i).getNgArrayCount()+"");
                    data.setY(ngarrayCount);
                }else{
                    data.setY((lstPcb.get(i).getNgComponentCount()==null )? null:lstPcb.get(i).getNgComponentCount());
                }

                ngFPYProductSeriesList.add(data);

                if(StringUtils.equals("1",iMode)){
                    doubleTmp =(double)(Math.round(Double.parseDouble(lstPcb.get(i).getGoodPcbYeildAoi()) *100)/100.0);
                }else if(StringUtils.equals("2",iMode)){

                    doubleTmp = (double)(Math.round(goodarrayCount*100/(goodarrayCount+ngarrayCount+passarrayCount)*100)/100.0);
                }else{
                    doubleTmp =(double)(Math.round(Double.parseDouble(lstPcb.get(i).getGoodcomponentYeildAoi()) *100)/100.0);
                }

                data= new Data();
                if(doubleTmp >= (double)ConstParam.DEFAULTSETTING_passPcbYeild){
                    data.setColor("#25dd19");//"#25dd19"
                }else{
                    data.setColor("#dd1127");
                    //描绘直通率报警

                    PlotBands plotBands = new PlotBands();
                    plotBands.setFrom(i-0.5);
                    plotBands.setTo(i+0.5);
                    plotBands.setColor("#ED1C24");
                    lstPlotBands.add(plotBands);
                }

                if(StringUtils.equals("1",iMode)){
                    iPcbTmp = lstPcb.get(i).getTotalAoi()==null?0:Integer.parseInt(lstPcb.get(i).getTotalAoi());
                }else if(StringUtils.equals("2",iMode)){
                    iPcbTmp = Long.parseLong(Math.round(goodarrayCount+ngarrayCount+passarrayCount)+"");
                }else{
                    iPcbTmp = lstPcb.get(i).getTotalComponentCount()==null?0:lstPcb.get(i).getTotalComponentCount();
                }
                if(iTotal<iPcbTmp){
                    iTotal=iPcbTmp;
                }


                data.setY(doubleTmp);
                goodFPYProductSplineSeriesList.add(data);
                data= new Data();
                //CPK
                dAreaCPK = lstPcb.get(i).getACpk()==null?0: (double)(Math.round(lstPcb.get(i).getACpk()*100)/100.0);

                dHeightCPK = lstPcb.get(i).getHCpk()==null?0:(double)(Math.round(lstPcb.get(i).getHCpk()*100)/100.0);
                dVolCPK = lstPcb.get(i).getVcpk()==null?0:(double)(Math.round(lstPcb.get(i).getVcpk()*100)/100.0);
                dShiftXCPK = lstPcb.get(i).getShithxCpk()==null?0: (double)(Math.round(lstPcb.get(i).getShithxCpk()*100)/100.0);
                dShiftYCPK = lstPcb.get(i).getShithyCpk()==null?0: (double)(Math.round(lstPcb.get(i).getShithyCpk()*100)/100.0);
                if(maxCpk<dAreaCPK){
                    maxCpk = dAreaCPK;
                }
                if(maxCpk<dHeightCPK){
                    maxCpk = dHeightCPK;
                }
                if(maxCpk<dVolCPK){
                    maxCpk = dVolCPK;
                }
                if(maxCpk<dShiftXCPK){
                    maxCpk = dShiftXCPK;
                }
                if(maxCpk<dShiftYCPK){
                    maxCpk = dShiftYCPK;
                }
                if(aValue!=null && aValue.contains("0")){
                    data.setY(dAreaCPK);
                    if(dAreaCPK >= (double) ConstParam.DEFAULTSETTING_standCPK){
                        data.setColor("#25dd19");
                    }else{
                        data.setColor("#dd1127");
                    }
                    cpkSeriesList.add(data);
                }else if(aValue!=null && aValue.contains("1")){
                    if(dHeightCPK >= (double) ConstParam.DEFAULTSETTING_standCPK){
                        data.setColor("#25dd19");
                    }else{
                        data.setColor("#dd1127");
                    }
                    data.setY(dHeightCPK);
                    cpkSeriesList.add(data);
                }else if(aValue!=null && aValue.contains("2")){
                    if(dVolCPK >= (double) ConstParam.DEFAULTSETTING_standCPK){
                        data.setColor("#25dd19");
                    }else{
                        data.setColor("#dd1127");
                    }
                    data.setY(dVolCPK);
                    cpkSeriesList.add(data);
                }else if(aValue!=null && aValue.contains("3")){
                    if(dShiftXCPK >= (double) ConstParam.DEFAULTSETTING_standCPK){
                        data.setColor("#25dd19");
                    }else{
                        data.setColor("#dd1127");
                    }
                    data.setY(dShiftXCPK);
                    cpkSeriesList.add(data);
                }else if(aValue!=null && aValue.contains("4")){
                    if(dShiftYCPK >= (double) ConstParam.DEFAULTSETTING_standCPK){
                        data.setColor("#25dd19");
                    }else{
                        data.setColor("#dd1127");
                    }
                    data.setY(dShiftYCPK);
                    cpkSeriesList.add(data);
                }else {
                    if(dAreaCPK >= (double) ConstParam.DEFAULTSETTING_standCPK){
                        data.setColor("#25dd19");
                    }else{
                        data.setColor("#dd1127");
                    }
                    data.setY(dAreaCPK);
                    cpkSeriesList.add(data);
                }
                //standCPK
                /*data = new Data();
                data.setY(ConstParam.DEFAULTSETTING_standCPK);
                standCPKSeriesList.add(data);*/
            }
        }else{
            lstFPYProductCategories.add("当前时间段无设备检测信息  "+stratTime+"-"+endTime);
        }
        //new  top5
        Map<String,String> mapData = new HashMap<String,String>();
        //int iTotal =0,iPcbTmp=0;
        List<Map<Integer,Integer>> realLst = new ArrayList<Map<Integer,Integer>>();
        List<Integer> lstComto = new ArrayList<Integer>();
        if (lstPcb != null && lstPcb.size() > 0) {
            for (int i = 0; i < lstPcb.size(); i++) {
                Map<Integer,Integer> mapsort = new HashMap<Integer, Integer>();
                Map<Integer,Integer> realMap = new HashMap<Integer, Integer>();
                for (int j = 0; j <20 ; j++) {
                    mapsort.put(j,ConstPublicClassUtil.getPadErrorCodeCountAoi(lstPcb.get(i),j));
                }
                //mapsort = ConstPublicClassUtil.sortMapByValue(mapsort);
                mapsort = ConstPublicClassUtil.sortByValue(mapsort,true);
                //lst.add(mapsort);
                int iTmp =0;
                for(Integer key : mapsort.keySet()){
                    iTmp++;
                    if(iTmp>5){
                        continue;
                    }
                    realMap.put(key,mapsort.get(key));
                }
                realLst.add(realMap);
            }
        }
        //top5
        for (int i = 0; i < 20; i++) {
            top5Series = new Series();
            if (lstPcb != null && lstPcb.size() > 0) {
                top5Series.setType("column");
                top5Series.setName(ConstPublicClassUtil.getErrorCodeChinase(i));
                top5Series.setStacking("normal");
                top5SeriesList = new ArrayList<Data>();
                for (int y = 0; y < lstPcb.size(); y++) {
                    data = new Data();
                    if(realLst.get(y).keySet().contains(i)) {
                        data.setY(ConstPublicClassUtil.getPadErrorCodeCountAoi(lstPcb.get(y), i));//ConstPublicClassUtil.getPadErrorCodeCount(lstPcb.get(y), i));
                        data.setColor(ConstPublicClassUtil.getErrorCodeColorAoi(i));
                    }
                    top5SeriesList.add(data);
                }
            }
            top5Series.setData(top5SeriesList);
            //top5Series.setColor("#F5A96A");
            lstTop5Series.add(top5Series);
        }
        xAxisFPYProduct.setCategories(lstFPYProductCategories);
        xAxisFPYProduct.setMin(0);
        xAxisFPYProduct.setMax(lstFPYProductCategories.size()<=0?0:(lstFPYProductCategories.size()-1));
        xAxisCPK.setCategories(lstFPYProductCategories);
        xAxisCPK.setMin(0);
        xAxisCPK.setMax(lstFPYProductCategories.size()<=0?0:(lstFPYProductCategories.size()-1));
        if(lstPlotBands.size()>0) {
            xAxisFPYProduct.setPlotBands(lstPlotBands);
        }
        //xAxisFPYProduct.set
        //xAxisFPYProduct.setLineWidth(2);
        jsonChartsBean_FPYProduct.setXAxis(xAxisFPYProduct);
        jsonChartsBean_CPK.setXAxis(xAxisCPK);

        Tooltip productTooltip = new Tooltip();
        productTooltip.setValueSuffix("p");
        productGoodSeries.setType("column");
        productGoodSeries.setName("良好");
        productGoodSeries.setData(goodFPYProductSeriesList);
        productGoodSeries.setTooltip(productTooltip);
        productGoodSeries.setStacking("normal");
        productGoodSeries.setColor("#22B14C");//22B14C 13dd15


        productNGSeries.setType("column");
        productNGSeries.setName("不良");
        productNGSeries.setData(ngFPYProductSeriesList);
        productNGSeries.setTooltip(productTooltip);
        productNGSeries.setStacking("normal");
        productNGSeries.setColor("#B84A5B"); //F47378 F7A35C FF7F27
        //productNGSeries.setLineWidth(5);

        productPassSeries.setType("column");
        productPassSeries.setName("误判");
        productPassSeries.setData(passFPYProductSeriesList);
        productPassSeries.setTooltip(productTooltip);
        productPassSeries.setStacking("normal");
        productPassSeries.setColor("#007AAE");  //60C5F1  4449dd  C8EBFA


        Tooltip productSplineTooltip = new Tooltip();
        productSplineTooltip.setValueSuffix("%");
        productSplineSeries.setType("spline");
        productSplineSeries.setName("直通率");
        productSplineSeries.setData(goodFPYProductSplineSeriesList);
        productSplineSeries.setTooltip(productSplineTooltip);
        productSplineSeries.setColor("#22B14C"); //22B14C 7bdd18
        productSplineSeries.setLineWidth(1.2);
        productSplineSeries.setConnectEnds(true);
        productSplineSeries.setyAxis(1);

        lstFPYProductSeries.add(productSplineSeries);
        lstFPYProductSeries.add(productNGSeries);
        lstFPYProductSeries.add(productGoodSeries);

        lstFPYProductSeries.add(productPassSeries);



        cpkSeries.setName("CPK");
        cpkSeries.setType("line");
        cpkSeries.setData(cpkSeriesList);
        cpkSeries.setLineWidth(0);
        cpkSeries.setConnectEnds(false);

        //standCpkSeries.setName("StandCPK");
        //standCpkSeries.setType("line");
        //standCpkSeries.setData(standCPKSeriesList);
        //standCpkSeries.setLineWidth(0);
        //standCpkSeries.setConnectEnds(true);


        lstCPKSeries.add(cpkSeries);
        // lstCPKSeries.add(standCpkSeries);


        jsonChartsBean_FPYProduct.setSeries(lstFPYProductSeries);
        jsonChartsBean_CPK.setSeries(lstCPKSeries);
        jsonChartsBean_Top5.setSeries(lstTop5Series);

        List<JsonChartsBean> lstJsonChartsBean = new ArrayList<JsonChartsBean>();
        lstJsonChartsBean.add(jsonChartsBean_FPYProduct);
        lstJsonChartsBean.add(jsonChartsBean_CPK);
        lstJsonChartsBean.add(jsonChartsBean_Top5);
        mapData.put("iTotal",iTotal+"");
        mapData.put("standCPK",ConstParam.DEFAULTSETTING_standCPK+"");
        //如果最大cpk低于标准值
        if(maxCpk<ConstParam.DEFAULTSETTING_standCPK){
            maxCpk = ConstParam.DEFAULTSETTING_standCPK;
        }
        mapData.put("maxCpk",maxCpk+"");
        return  new ApiResponse(true,null,lstJsonChartsBean,mapData);
    }


    /***
     *  AOI设备状态历史记录
     */
    @RequestMapping("historicalList_aoi")
    public ModelAndView showSpiStatusView(@RequestParam("lineNo")String lineNo,@RequestParam("aoiMode") String aoiMode){
        ConstController.constController.iniDefaultParamSetting();
        boolean bCmBoxs = ConstPublicClassUtil.loadCmBoxs();bCmBoxs=true;
        String viewName = "aoi/historicalList_aoi";
        if(bCmBoxs){
        }else{
            viewName = "error/comBoxExpire";
        }
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("aoiMode",aoiMode);
        mv.addObject("lineNo",lineNo);
        return  mv;

    }

    @GetMapping("historicalMonitorJson_aoi")
    @ResponseBody
    public ApiResponse getSpiStatusWithInspectTime(@RequestParam("inspectStartTime")String inspectStartTime,
                                                   @RequestParam("inspectEndTime") String inspectEndTime,
                                                   @RequestParam("lineNo")String lineNo,@RequestParam("aoiMode") String aoiMode){

        List<SStatus> aStatusList = aStatusService.selectList(Condition.create().eq("lineNo",lineNo).eq("aoiMode",aoiMode)
                .ge("updateTime",inspectStartTime)
                .le("updateTime",inspectEndTime));
        return new ApiResponse(true,null,null,aStatusList);
    }


}
