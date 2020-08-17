package com.sinictek.spm.api;


import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.JsonchartModel.*;
import com.sinictek.spm.model.SDefaultsetting;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.SStatus;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.StringTimeUtils;
import com.sinictek.spm.service.SDefaultsettingService;
import com.sinictek.spm.service.SLineService;
import com.sinictek.spm.service.SPcbService;
import com.sinictek.spm.service.SStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * spi-设备状态 前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@Controller
@RequestMapping("/Status")
public class SStatusController {

    @Autowired
    SDefaultsettingService sDefaultsettingService;
    @Autowired
    SStatusService sStatusService;

    @Autowired
    SLineService sLineService;

    @Autowired
    SPcbService pcbService;

    @GetMapping("pcbMonitorview")
    public ModelAndView ShowPcbMonitorView(){
        ModelAndView mv = new ModelAndView("spi/pcbMonitorview");
        // ini defaultsetting
        iniDefaultParamSetting();
        return  mv;
    }

    @GetMapping("sigSpiPcbMonitorview")
    public ModelAndView ShowSigSpiPcbMonitorview(@RequestParam("lineNo")String lineNo){
        ModelAndView mv = new ModelAndView("spi/sigSpiPcbMonitorview");
        // ini defaultsetting
        iniDefaultParamSetting();
        mv.addObject("lineNo",lineNo);
        return  mv;
    }
    private void iniDefaultParamSetting(){

        if(ConstParam.DEFAULTSETTING_boardMachineTimeLimit==0|
                ConstParam.DEFAULTSETTING_boardMachineRefreshTime==0|
                ConstParam.DEFAULTSETTING_autoDeleteDays==0|
                ConstParam.DEFAULTSETTING_FPY==0|
                ConstParam.DEFAULTSETTING_CPK==0|
                ConstParam.DEFAULTSETTING_Product==0|
                ConstParam.DEFAULTSETTING_DefaultTop5==0|
                ConstParam.DEFAULTSETTING_autoDelete==0|
                ConstParam.DEFAULTSETTING_defaultType==""|
                ConstParam.DEFAULTSETTING_FrequencyStart==0|
                ConstParam.DEFAULTSETTING_standCPK==0
        ){
            List<SDefaultsetting> lstDefaultSetting = sDefaultsettingService.selectList(null);
            if(lstDefaultSetting!=null&&lstDefaultSetting.size()>0){
                for (int i = 0; i < lstDefaultSetting.size(); i++) {
                    String strSettingName=lstDefaultSetting.get(i).getSettingName(),strSettingValue=lstDefaultSetting.get(i).getSettingValue();
                    if("boardMachineTimeLimit".equals(strSettingName)){
                        ConstParam.DEFAULTSETTING_boardMachineTimeLimit=strSettingValue==null?5:Integer.parseInt(strSettingValue);
                    }
                    if("FPY".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_FPY=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("CPK".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_CPK=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("Product".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_Product=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("DefaultTop5".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_DefaultTop5=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                    if("defaultType".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_defaultType=strSettingValue==null?"0;1;2;3;4":strSettingValue;
                    }
                    if("boardMachineRefreshTime".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_boardMachineRefreshTime=strSettingValue==null?10:Integer.parseInt(strSettingValue);
                    }
                    if("autoDelete".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_autoDelete=strSettingValue==null?0:Integer.parseInt(strSettingValue);
                    }
                    if("autoDeleteDays".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_autoDeleteDays=strSettingValue==null?30:Integer.parseInt(strSettingValue);
                    }

                    if("Frequency-start".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_FrequencyStart=strSettingValue==null?8:Integer.parseInt(strSettingValue);
                    }
                    if("standCPK".equals(strSettingName)) {
                        ConstParam.DEFAULTSETTING_standCPK=strSettingValue==null?1:Integer.parseInt(strSettingValue);
                    }
                }
            }
        }


    }

    @GetMapping("pcbMonitorview_realLineView")
    public ModelAndView ShowpcbMonitorview_realLineView(){
        ModelAndView mv = new ModelAndView("spi/pcbMonitorview_realLineView");
        // ini defaultsetting
        iniDefaultParamSetting();
        mv.addObject("boardMachineRefreshTime",ConstParam.DEFAULTSETTING_boardMachineRefreshTime);
        return  mv;
    }

    @ResponseBody
    @GetMapping("pcbMonitorview_realLineViewJson")
    public ApiResponse GetpcbMonitorview_realLineViewJson(@RequestParam("aValue") String aValue){
        //后台获取数据有:    FPY  全线的PCB的直通率, NG板个数,PASS板个数,REPASS板个数
        //                  CPK  全线的PCB的CPK值, SETTING内设置ckp标准值
        //                  TOP5  全线的PCB内计算  每条线所有不良项排列前5位的不良项
        /*int iStandCPK =1;
        int iHourCount =8;
        if(ConstParam.DEFAULTSETTING_standCPK!=0){
            iStandCPK = ConstParam.DEFAULTSETTING_standCPK;
        }
        if(ConstParam.DEFAULTSETTING_FrequencyStart!=0){
            iHourCount = ConstParam.DEFAULTSETTING_FrequencyStart;
        }*/
        double dAreaCPK = 0;
        double dVolCPK = 0;
        double dHeightCPK = 0;
        double dShiftXCPK = 0;
        double dShiftYCPK = 0;
        String stratTime = StringTimeUtils.addHourTimeStrNow(Calendar.getInstance(),-ConstParam.DEFAULTSETTING_FrequencyStart);
        String endTime = StringTimeUtils.getTimeDateToString(new Date());
        //获取到每条线计算后总数据
        List<SPcb> lstPcb = pcbService.getPcbListWithALLLine(stratTime,endTime);
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
        Series standCpkSeries = new Series();

        List<Series> lstTop5Series = new ArrayList<Series>();
        //Series    = new Series();
        //Series standCpkSeries = new Series();

        XAxis xAxisFPYProduct = new XAxis();
        List<String> lstFPYProductCategories = new ArrayList<String>();
        List<Data> goodFPYProductSeriesList = new ArrayList<Data>();
        List<Data> passFPYProductSeriesList = new ArrayList<Data>();
        List<Data> ngFPYProductSeriesList = new ArrayList<Data>();
        List<Data> goodFPYProductSplineSeriesList = new ArrayList<Data>();
        List<Data> cpkSeriesList = new ArrayList<Data>();
        List<Data> standCPKSeriesList = new ArrayList<Data>();
        Data data =null;
        double doubleTmp=0;
        if(lstPcb!=null && lstPcb.size()>0){
            for (int i = 0; i < lstPcb.size(); i++){
                data = new Data();
                lstFPYProductCategories.add(lstPcb.get(i).getLineNo()+"");
                data.setY((lstPcb.get(i).getGoodPcbCount()==null || lstPcb.get(i).getGoodPcbCount()== "0")? null:Double.parseDouble(lstPcb.get(i).getGoodPcbCount()));
                goodFPYProductSeriesList.add(data);
                data = new Data();
                data.setY((lstPcb.get(i).getPassPcbCount()==null || lstPcb.get(i).getPassPcbCount()=="0")?null:Double.parseDouble(lstPcb.get(i).getPassPcbCount()));
                passFPYProductSeriesList.add(data);
                data = new Data();
                data.setY((lstPcb.get(i).getNgPcbCount()==null || lstPcb.get(i).getNgPcbCount()=="0")?null:Double.parseDouble(lstPcb.get(i).getNgPcbCount()));
                ngFPYProductSeriesList.add(data);
                doubleTmp =(double)(Math.round(Double.parseDouble(lstPcb.get(i).getGoodPcbYeild()) *100)/100.0);
                data= new Data();
                data.setY(doubleTmp);
                goodFPYProductSplineSeriesList.add(data);
                data= new Data();
                //CPK
                if(doubleTmp >= ConstParam.DEFAULTSETTING_standCPK){
                    data.setColor("#25dd19");
                }else{
                    data.setColor("#dd1127");
                }
                dAreaCPK = lstPcb.get(i).getaCpk()==null?0: (double)(Math.round(lstPcb.get(i).getaCpk()*100)/100.0);
                dHeightCPK = lstPcb.get(i).gethCpk()==null?0:(double)(Math.round(lstPcb.get(i).gethCpk()*100)/100.0);
                dVolCPK = lstPcb.get(i).getVcpk()==null?0:(double)(Math.round(lstPcb.get(i).getVcpk()*100)/100.0);
                dShiftXCPK = lstPcb.get(i).getShithxCpk()==null?0: (double)(Math.round(lstPcb.get(i).getShithxCpk()*100)/100.0);
                dShiftYCPK = lstPcb.get(i).getShithyCpk()==null?0: (double)(Math.round(lstPcb.get(i).getShithyCpk()*100)/100.0);
                if(aValue!=null && aValue.contains("0")){
                    data.setY(dAreaCPK);
                    cpkSeriesList.add(data);
                }else if(aValue!=null && aValue.contains("1")){
                    data.setY(dHeightCPK);
                    cpkSeriesList.add(data);
                }else if(aValue!=null && aValue.contains("2")){
                    data.setY(dVolCPK);
                    cpkSeriesList.add(data);
                }else if(aValue!=null && aValue.contains("3")){
                    data.setY(dShiftXCPK);
                    cpkSeriesList.add(data);
                }else if(aValue!=null && aValue.contains("4")){
                    data.setY(dShiftYCPK);
                    cpkSeriesList.add(data);
                }else {
                    data.setY(dAreaCPK);
                    cpkSeriesList.add(data);
                }
                //standCPK
                data = new Data();
                data.setY(ConstParam.DEFAULTSETTING_standCPK);
                standCPKSeriesList.add(data);

                //top5

            }
        }

        //top5
        for (int i = 0; i < 15; i++) {
            if (lstPcb != null && lstPcb.size() > 0) {
                for (int y = 0; y < lstPcb.size(); y++) {


                }
            }
        }
        xAxisFPYProduct.setCategories(lstFPYProductCategories);
        xAxisFPYProduct.setMin(0);
        xAxisFPYProduct.setMax(lstFPYProductCategories.size()<=0?0:(lstFPYProductCategories.size()-1));
        jsonChartsBean_FPYProduct.setXAxis(xAxisFPYProduct);

        Tooltip productTooltip = new Tooltip();
        productTooltip.setValueSuffix("pcs");
        productGoodSeries.setType("column");
        productGoodSeries.setName("PCB->PASS");
        productGoodSeries.setData(goodFPYProductSeriesList);
        productGoodSeries.setTooltip(productTooltip);
        productGoodSeries.setStacking("normal");
        productGoodSeries.setColor("#13dd15");

        productNGSeries.setType("column");
        productNGSeries.setName("PCB->NG");
        productNGSeries.setData(ngFPYProductSeriesList);
        productNGSeries.setTooltip(productTooltip);
        productNGSeries.setStacking("normal");
        productNGSeries.setColor("#dd0f31");

        productPassSeries.setType("column");
        productPassSeries.setName("PCB->REPASS");
        productPassSeries.setData(passFPYProductSeriesList);
        productPassSeries.setTooltip(productTooltip);
        productPassSeries.setStacking("normal");
        productPassSeries.setColor("#4449dd");

        Tooltip productSplineTooltip = new Tooltip();
        productSplineTooltip.setValueSuffix("%");
        productSplineSeries.setType("spline");
        productSplineSeries.setName("直通率");
        productSplineSeries.setData(goodFPYProductSplineSeriesList);
        productSplineSeries.setTooltip(productSplineTooltip);
        productSplineSeries.setColor("#7bdd18");
        lstFPYProductSeries.add(productSplineSeries);
        lstFPYProductSeries.add(productGoodSeries);
        lstFPYProductSeries.add(productNGSeries);
        lstFPYProductSeries.add(productPassSeries);

        jsonChartsBean_FPYProduct.setSeries(lstFPYProductSeries);

        cpkSeries.setName("CPK");
        cpkSeries.setType("line");
        cpkSeries.setData(cpkSeriesList);
        cpkSeries.setLineWidth(0);
        cpkSeries.setConnectEnds(false);

        standCpkSeries.setName("StandCPK");
        standCpkSeries.setType("line");
        standCpkSeries.setData(standCPKSeriesList);
        standCpkSeries.setLineWidth(0.4);
        standCpkSeries.setConnectEnds(true);

        lstCPKSeries.add(cpkSeries);
        lstCPKSeries.add(standCpkSeries);
        jsonChartsBean_CPK.setSeries(lstCPKSeries);

        List<JsonChartsBean> lstJsonChartsBean = new ArrayList<JsonChartsBean>();
        lstJsonChartsBean.add(jsonChartsBean_FPYProduct);
        lstJsonChartsBean.add(jsonChartsBean_CPK);
        return  new ApiResponse(true,null,lstJsonChartsBean);
    }

    @ResponseBody
    @GetMapping("pcbMonitorJson")
    public ApiResponse<List<SStatus>> GetPcbMonitorListJson(){
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
        return new ApiResponse<List<SStatus>>(null,sStatusService.getAllStatusWithLineNoLimt());
    }

    @ResponseBody
    @GetMapping("sigSpiPcbMonitorJson")
    public ApiResponse<List<SStatus>> getSigSpiPcbMonitorJson(@RequestParam("lineNo") String lineNo){

        SStatus sStatus = sStatusService.getStatusWithLineNo(lineNo);
        List<SStatus> lstStatus = new ArrayList<SStatus>();
        lstStatus.add(sStatus);

        return  new ApiResponse<List<SStatus>>(null,lstStatus);

    }

}

