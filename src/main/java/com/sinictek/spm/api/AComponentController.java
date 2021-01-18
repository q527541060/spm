package com.sinictek.spm.api;


import com.sinictek.spm.model.AComponent;
import com.sinictek.spm.model.APcb;
import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.SPad;
import com.sinictek.spm.model.SPcb;
import com.sinictek.spm.model.apiResponse.ApiResponse;
import com.sinictek.spm.model.utils.QuickLZ;
import com.sinictek.spm.service.AComponentService;
import com.sinictek.spm.service.APcbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-30
 */
@Controller
@RequestMapping("/aComponent")
public class AComponentController {

    @Autowired
    APcbService aPcbService;
    @Autowired
    AComponentService aComponentService;

    @ResponseBody
    @PostMapping("padImage")
    public ApiResponse getComponent2DImage(@RequestParam("pcbidLine") String pcbidLine,
                                     @RequestParam("padId") String padId,
                                     @RequestParam("aoiType") String aoiType)throws IOException, DataFormatException {

        pcbidLine = pcbidLine.replace("=====","#");
        Map<String,Object> pcbListMap = new HashMap<String,Object>();
        pcbListMap.put("pcbIdLine",pcbidLine);
        pcbListMap.put("aoiMode",aoiType);
        List<APcb> aPcblst = aPcbService.selectByMap(pcbListMap);
        String componentTableName =null;
        if(aPcblst!=null&&aPcblst.size()>0)
        {
            componentTableName = aPcblst.get(0).getComponentTableName();

            AComponent aComponent = aComponentService.getComponentWithPCbidLineDao(componentTableName,pcbidLine,padId,aoiType);
            if(aComponent!=null )
            {
                return new ApiResponse(true,null,aComponent.getComImageBase64(),null);
            }
            else{
                return new ApiResponse(false,"IMAGE_IS_NOT_EXIST",null,null);
            }

        }else{
            return new ApiResponse(true,null,null,null);
        }
    }


    @ResponseBody
    @GetMapping("padList")
    public ApiResponse getComponentListWithPcbIDline(@RequestParam("pcbIdLine") String pcbIdLine,
                                               @RequestParam("defectTypeCode") String defectTypeCode,
                                                     @RequestParam("aoiType")String aoiType){

        pcbIdLine = pcbIdLine.replace("=====","#");
        Map<String,Object> pcbListMap = new HashMap<String,Object>();
        pcbListMap.put("pcbIdLine",pcbIdLine);
        pcbListMap.put("aoiMode",aoiType);
        List<APcb> aPcblst = aPcbService.selectByMap(pcbListMap);
        String componentTableName =null;
        if(aPcblst!=null&&aPcblst.size()>0){
            componentTableName = aPcblst.get(0).getComponentTableName();
        }else{
            return new ApiResponse(true,null,null,null);
        }
        List<AComponent> lstComponent = aComponentService.getComponentListWithPCbidLineDao(componentTableName ,pcbIdLine,defectTypeCode,aoiType);

        return  new ApiResponse(true,null,null,lstComponent);
    }

    @ResponseBody
    @GetMapping("componentDefaultTypeInfo")
    public ApiResponse getComponentDefaultTypeInfoWithPCBtable(@RequestParam("id") Integer id){
        APcb aPcb = aPcbService.selectById(id);
        String padSeriesData=null;
        padSeriesData = getcomponentDefaultTypeContent(aPcb);
        return  new ApiResponse(true,null,padSeriesData);
    }

    private String getcomponentDefaultTypeContent(APcb sPcb){
        String padSeriesData="";
        padSeriesData+="[";
        if(sPcb!=null ){
            if(sPcb.getMissingCount()!=null && sPcb.getMissingCount()>0){
                padSeriesData+="[\"Missing\","+    sPcb.getMissingCount()+"],";
            }else{
                padSeriesData+="[\"Missing\","+    null+"],";
            }
            if(sPcb.getCustomCount()!=null && sPcb.getCustomCount()>0){
                padSeriesData+="[\"Custom\","+    sPcb.getCustomCount()+"],";
            }else{
                padSeriesData+="[\"Custom\","+    null+"],";
            }
            if(sPcb.getDefaultCount()!=null && sPcb.getDefaultCount()>0){
                padSeriesData+="[\"Default\","+    sPcb.getDefaultCount()+"],";
            }else{
                padSeriesData+="[\"Default\","+    null+"],";
            }
            if(sPcb.getShiftXCount()!=null && sPcb.getShiftXCount()>0){
                padSeriesData+="[\"ShiftX\","+    sPcb.getShiftXCount()+"],";
            }else{
                padSeriesData+="[\"ShiftX\","+    null+"],";
            }
            if(sPcb.getShiftYCount()!=null && sPcb.getShiftYCount()>0){
                padSeriesData+="[\"ShiftY\","+    sPcb.getShiftYCount()+"],";
            }else{
                padSeriesData+="[\"ShiftY\","+    null+"],";
            }
            if(sPcb.getRotationCount()!=null && sPcb.getRotationCount()>0){
                padSeriesData+="[\"Rotation\","+    sPcb.getRotationCount()+"],";
            }else{
                padSeriesData+="[\"Rotation\","+    null+"],";
            }
            if(sPcb.getBridgeCount()!=null && sPcb.getBridgeCount()>0){
                padSeriesData+="[\"Bridge\","+    sPcb.getBridgeCount()+"],";
            }else{
                padSeriesData+="[\"Bridge\","+    null+"],";
            }
            if(sPcb.getVoidCount()!=null && sPcb.getVoidCount()>0){
                padSeriesData+="[\"Void\","+    sPcb.getVoidCount()+"],";
            }else{
                padSeriesData+="[\"Void\","+    null+"],";
            }
            if(sPcb.getTombStoneCount()!=null && sPcb.getTombStoneCount()>0){
                padSeriesData+="[\"TombStone\","+    sPcb.getTombStoneCount()+"],";
            }else{
                padSeriesData+="[\"TombStone\","+    null+"],";
            }
            if(sPcb.getPinLiftCount()!=null && sPcb.getPinLiftCount()>0){
                padSeriesData+="[\"PinLift\","+    sPcb.getPinLiftCount()+"],";
            }else{
                padSeriesData+="[\"PinLift\","+    null+"],";
            }
            if(sPcb.getSolderBeadCount()!=null && sPcb.getSolderBeadCount()>0){
                padSeriesData+="[\"SolderBead\","+    sPcb.getSolderBeadCount()+"],";
            }else{
                padSeriesData+="[\"SolderBead\","+    null+"],";
            }
            if(sPcb.getSmearCount()!=null && sPcb.getSmearCount()>0){
                padSeriesData+="[\"Smear\","+    sPcb.getSmearCount()+"],";
            }else{
                padSeriesData+="[\"Smear\","+    null+"],";
            }
            if(sPcb.getPolarityCount()!=null && sPcb.getPolarityCount()>0){
                padSeriesData+="[\"Polarity\","+    sPcb.getPolarityCount()+"],";
            }else{
                padSeriesData+="[\"Polarity\","+    null+"],";
            }
            if(sPcb.getReverseCount()!=null && sPcb.getReverseCount()>0){
                padSeriesData+="[\"Reverse\","+    sPcb.getReverseCount()+"],";
            }else{
                padSeriesData+="[\"Reverse\","+    null+"],";
            }
            if(sPcb.getWrongPartCount()!=null && sPcb.getWrongPartCount()>0){
                padSeriesData+="[\"WrongPart\","+    sPcb.getWrongPartCount()+"],";
            }else{
                padSeriesData+="[\"WrongPart\","+    null+"],";
            }

            if(sPcb.getNoSolderCount()!=null && sPcb.getNoSolderCount()>0){
                padSeriesData+="[\"NoSolder\","+    sPcb.getNoSolderCount()+"],";
            }else{
                padSeriesData+="[\"NoSolder\","+    null+"],";
            }
            if(sPcb.getCopperExposureCount()!=null && sPcb.getCopperExposureCount()>0){
                padSeriesData+="[\"CopperExposure\","+    sPcb.getCopperExposureCount()+"],";
            }else{
                padSeriesData+="[\"CopperExposure\","+    null+"],";
            }
            if(sPcb.getExcessPartsCount()!=null && sPcb.getExcessPartsCount()>0){
                padSeriesData+="[\"ExcessParts\","+    sPcb.getExcessPartsCount()+"],";
            }else{
                padSeriesData+="[\"ExcessParts\","+    null+"],";
            }
            if(sPcb.getSolderingCount()!=null && sPcb.getSolderingCount()>0){
                padSeriesData+="[\"Soldering\","+    sPcb.getSolderingCount()+"],";
            }else{
                padSeriesData+="[\"Soldering\","+    null+"],";
            }
            if(sPcb.getExcessPartsCount()!=null && sPcb.getExcessPartsCount()>0){
                padSeriesData+="[\"ExcessParts\","+    sPcb.getExcessPartsCount()+"]";
            }else{
                padSeriesData+="[\"ExcessParts\","+    null+"]";
            }
            /*if(sPcb.get()!=null && sPcb.getWrongPartCount()>0){
                padSeriesData+="[\"WrongPart\","+    sPcb.getWrongPartCount()+"],";
            }else{
                padSeriesData+="[\"WrongPart\","+    null+"],";
            }*/

          //  padSeriesData+="[\"WarpError\","+    null+"]";  //板弯错误  新增

        }
        padSeriesData+="]";

        return padSeriesData;
    }

}

