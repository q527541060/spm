package com.sinictek.spm.model.ConstClasses;

import com.alibaba.druid.util.StringUtils;
import com.sinictek.spm.model.APcb;
import com.sinictek.spm.model.SPcb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import com.google.common.collect.Maps;
import com.sinictek.spm.model.utils.MapValueComparator;
import com.wibu.CodeMeter.CodeMeter;
import com.wibu.CodeMeter.CodeMeter.CMACCESS2;
import com.wibu.CodeMeter.CodeMeter.CMBOXENTRY;
import com.wibu.CodeMeter.CodeMeter.CMBOXINFO;
import com.wibu.CodeMeter.CodeMeter.CMTIME;

/**
 * @Author sinictek-pd
 * @Date 2020/8/18 10:15
 * @Version 1.0
 */
public class ConstPublicClassUtil {


    public static int getPadErrorCodeCount(SPcb sPcbList, int iJuResult){
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

    public static int getPadErrorCodeCountAoi(APcb sPcbList, int iJuResult){

        int i=0;
        switch (iJuResult){
            case 0:{
                i = sPcbList.getMissingCount()==null?0:sPcbList.getMissingCount();
                break;
            }
            case 1:
            {
                i = sPcbList.getCustomCount()==null?0:sPcbList.getCustomCount();
                break;
            }
            case 2:
            {
                i = sPcbList.getDefaultCount()==null?0:sPcbList.getDefaultCount();
                break;
            }
            case 3:
            {
                i = sPcbList.getShiftXCount()==null?0:sPcbList.getShiftXCount();
                break;
            }
            case 4:
            {
                i = sPcbList.getShiftYCount()==null?0:sPcbList.getShiftYCount();
                break;
            }
            case 5:
            {
                i = sPcbList.getRotationCount()==null?0:sPcbList.getRotationCount();
                break;
            }
            case 6:
            {
                i = sPcbList.getBridgeCount()==null?0:sPcbList.getBridgeCount();
                break;
            }
            case 7:
            {
                i = sPcbList.getVoidCount()==null?0:sPcbList.getVoidCount();
                break;
            }
            case 8:
            {
                i = sPcbList.getTombStoneCount()==null?0:sPcbList.getTombStoneCount();
                break;
            }
            case 9:
            {
                i = sPcbList.getPinLiftCount()==null?0:sPcbList.getPinLiftCount();
                break;
            }
            case 10:
            {
                i = sPcbList.getSolderBeadCount()==null?0:sPcbList.getSolderBeadCount();
                break;
            }
            case 11:
            {
                i = sPcbList.getSmearCount()==null?0:sPcbList.getSmearCount();
                break;
            }
            case 12:
            {
                i = sPcbList.getPolarityCount()==null?0:sPcbList.getPolarityCount();
                break;
            }
            case 13:
            {
                i = sPcbList.getReverseCount()==null?0:sPcbList.getReverseCount();
                break;
            }
            case 14:
            {
                i = sPcbList.getWrongPartCount()==null?0:sPcbList.getWrongPartCount();
                break;
            }
            case 15:
            {
                i = sPcbList.getNoSolderCount()==null?0:sPcbList.getNoSolderCount();
                break;
            }
            case 16:
            {
                i = sPcbList.getCopperExposureCount()==null?0:sPcbList.getCopperExposureCount();
                break;
            }
            case 17:
            {
                i = sPcbList.getExcessPartsCount()==null?0:sPcbList.getExcessPartsCount();
                break;
            }
            case 18:
            {
                i = sPcbList.getSolderingCount()==null?0:sPcbList.getSolderingCount();
                break;
            }
            case 19:
            {
                i = sPcbList.getExcessPartsCount()==null?0:sPcbList.getExcessPartsCount();
                break;
            }
            default:{
                i = sPcbList.getMissingCount()==null?0:sPcbList.getMissingCount();
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


    public static String[] getDefaultTypeArray(){
        if(ConstParam.DEFAULTSETTING_defaultType==""){
            ConstParam.DEFAULTSETTING_defaultType="0;1;2;3;4";
        }
        return ConstParam.DEFAULTSETTING_defaultType.split(";");
    }
    public static String getErrorCodeString(int iJuResult) {
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

    public static String getErrorCodeStringAoi(int iJuResult) {
        String str = "";
        switch (iJuResult) {
            case 0: {
                str = "Missing";
                break;
            }
            case 1: {
                str = "Custom";
                break;
            }
            case 2: {
                str = "Default";
                break;
            }
            case 3: {
                str = "ShiftX";
                break;
            }
            case 4: {
                str = "ShiftY";
                break;
            }
            case 5: {
                str = "Rotation";
                break;
            }
            case 6: {
                str = "Bridge";
                break;
            }
            case 7: {
                str = "Void";
                break;
            }
            case 8: {
                str = "TombStone";
                break;
            }
            case 9: {
                str = "PinLift";
                break;
            }
            case 10: {
                str = "SolderBead";
                break;
            }
            case 11: {
                str = "Smear";
                break;
            }
            case 12: {
                str = "Polarity";
                break;
            }
            case 13: {
                str = "Reverse";
                break;
            }
            case 14: {
                str = "WrongPart";
                break;
            }
            case 15: {
                str = "NoSolder";
                break;
            }case 16: {
                str = "CopperExposure";
                break;
            }case 17: {
                str = "ExcessParts";
                break;
            }case 18: {
                str = "Soldering";
                break;
            }case 19: {
                str = "ExcessParts";
                break;
            }
            default: {
                str = "Missing";
                break;
            }

        }
        return  str;
    }

    public static String getErrorCodeChinase(int iJuResult) {
        String str = "";
        switch (iJuResult) {
            case 0: {
                str = "无锡";
                break;
            }
            case 1: {
                str = "少锡";
                break;
            }
            case 2: {
                str = "多锡";
                break;
            }
            case 3: {
                str = "高度偏高";
                break;
            }
            case 4: {
                str = "高度偏低";
                break;
            }
            case 5: {
                str = "面积偏高";
                break;
            }
            case 6: {
                str = "面积偏低";
                break;
            }
            case 7: {
                str = "X偏移";
                break;
            }
            case 8: {
                str = "Y偏移";
                break;
            }
            case 9: {
                str = "短路";
                break;
            }
            case 10: {
                str = "锡型共面";
                break;
            }
            case 11: {
                str = "锡型错误";
                break;
            }
            case 12: {
                str = "锡型错误";
                break;
            }
            case 13: {
                str = "短路";
                break;
            }
            case 14: {
                str = "焊盘面积错误";
                break;
            }
            case 15: {
                str = "短路";
                break;
            }
            default: {
                str = "无锡";
                break;
            }

        }
        return  str;
    }

    public static String getErrorCodeChinaseAoi(int iJuResult) {
        String str = "";
        switch (iJuResult) {
            case 0: {
                str = "Missing";
                break;
            }
            case 1: {
                str = "Custom";
                break;
            }
            case 2: {
                str = "Default";
                break;
            }
            case 3: {
                str = "ShiftX";
                break;
            }
            case 4: {
                str = "ShiftY";
                break;
            }
            case 5: {
                str = "Rotation";
                break;
            }
            case 6: {
                str = "Bridge";
                break;
            }
            case 7: {
                str = "Void";
                break;
            }
            case 8: {
                str = "TombStone";
                break;
            }
            case 9: {
                str = "PinLift";
                break;
            }
            case 10: {
                str = "SolderBead";
                break;
            }
            case 11: {
                str = "Smear";
                break;
            }
            case 12: {
                str = "Polarity";
                break;
            }
            case 13: {
                str = "Reverse";
                break;
            }
            case 14: {
                str = "WrongPart";
                break;
            }
            case 15: {
                str = "NoSolder";
                break;
            }case 16: {
                str = "CopperExposure";
                break;
            }case 17: {
                str = "ExcessParts";
                break;
            }case 18: {
                str = "Soldering";
                break;
            }case 19: {
                str = "ExcessParts";
                break;
            }
            default: {
                str = "Missing";
                break;
            }

        }
        return  str;
    }



    public static Map<Integer, Integer> sortMapByValue(Map<Integer, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(entryList, new MapValueComparator());
        Iterator<Map.Entry<Integer, Integer>> iter = entryList.iterator();
        Map.Entry<Integer, Integer> tmpEntry = null;

        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    /**
     * 根据map的value排序
     *
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     * @author zero 2019/04/08
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue().reversed())
                    .forEach(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }
    /**
     * 根据map的key排序
     *
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     * @author zero 2019/04/08
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }


    public static boolean  loadCmBoxs() {
        boolean validate = false;
        StringBuffer[] sb = getBoxList();
        if (sb != null) {
            validate = listFirmItem(102124, 0, 69);  //产品号
        }
        return validate;
    }
    private static boolean listFirmItem(long firmCode, int boxIndex, long productCode) {
        long handle;
        if ((arrayBoxInfo == null) | (boxIndex == -1)) {
            return false;
        }
        if (boxIndex >= arrayBoxInfo.length) {
            return false;
        }

        // Create an "Firm Item Access"
        CMACCESS2 cmacc = new CMACCESS2();
        cmacc.ctrl = CodeMeter.CM_ACCESS_FIRMITEM;
        cmacc.firmCode = firmCode;
        cmacc.boxMask = arrayBoxInfo[boxIndex].boxMask;
        cmacc.serialNumber = arrayBoxInfo[boxIndex].serialNumber;
        handle = CodeMeter.cmAccess2(CodeMeter.CM_ACCESS_LOCAL, cmacc);
        if (0 == handle) {
            int error = CodeMeter.cmGetLastErrorCode();
            if (error == 200) {
                return false;
            }
            return false;
        }

        int numberOfEntries = CodeMeter.cmGetBoxContents(handle,
                CodeMeter.CM_GBC_FI | CodeMeter.CM_GBC_BOX, firmCode,
                arrayBoxInfo[boxIndex], null);
        if (numberOfEntries == 0) {
            return false;
        } else {
            CMBOXENTRY[] cmboxentry = new CMBOXENTRY[numberOfEntries];
            int ret = CodeMeter.cmGetBoxContents(handle, CodeMeter.CM_GBC_FI
                            | CodeMeter.CM_GBC_BOX, firmCode, arrayBoxInfo[boxIndex],
                    cmboxentry);
            if ((ret == 0) || (ret != numberOfEntries)) {
                return false;
            }
            // Format FirmPreciseTime
            CMTIME firmPreciseTime = new CMTIME();
            firmPreciseTime.setTimeInSeconds(cmboxentry[0].firmPreciseTime);
            if (cmboxentry[0].setPios == 0) {
                return true;
            } // if
            for (int i = 0; i < ret; i++) {
                if ((cmboxentry[i].setPios & CodeMeter.CM_GF_PRODUCTCODE) == CodeMeter.CM_GF_PRODUCTCODE) {
                    if (cmboxentry[i].productCode == productCode) {
                        return true;

                    }
                }
            }
        }
        return false;
    }


    private static CMBOXINFO[] arrayBoxInfo = null;
    private static StringBuffer[] getBoxList() {
        long handle;
        int numberOfCmBoxes;
        StringBuffer[] boxList = null;

        // clean up old stored list of Boxes
        arrayBoxInfo = null;
        // Create a "Subsystem Access" to the local subsystem
        CMACCESS2 cmacc = new CMACCESS2();
        cmacc.ctrl = CodeMeter.CM_ACCESS_SUBSYSTEM;
        handle = CodeMeter.cmAccess2(CodeMeter.CM_ACCESS_LOCAL, cmacc);
        if (0 == handle) {
            return null;
        }
        // get information of connected CmContainers
        // first determine the number of CmContainers
        numberOfCmBoxes = CodeMeter.cmGetBoxes(handle,
                CodeMeter.CM_GB_ALLPORTS, null);
        if (numberOfCmBoxes > 0) {
            arrayBoxInfo = new CMBOXINFO[numberOfCmBoxes];
            numberOfCmBoxes = CodeMeter.cmGetBoxes(handle,
                    CodeMeter.CM_GB_ALLPORTS, arrayBoxInfo);
            boxList = new StringBuffer[numberOfCmBoxes];
            for (int i = 0; i < Math.min(boxList.length, numberOfCmBoxes); i++) {
                boxList[i] = new StringBuffer(arrayBoxInfo[i].boxMask + "-"
                        + arrayBoxInfo[i].serialNumber);
            }
            StringBuffer output = new StringBuffer("    " + numberOfCmBoxes
                    + " CmContainers found.\n");
            output.append("    Found serial numbers: ");
            for (int i = 0; i < numberOfCmBoxes; i++) {
                if (i > 0)
                    output.append("|");
                output.append(boxList[i]);
            }

        } else {
            return null;
        }
        CodeMeter.cmRelease(handle);

        return boxList;
    }


    public static byte[] getFileByte(String filePath) throws IOException {

        if(StringUtils.isEmpty(filePath)) return null;
        File file = new File(filePath);
        byte[] arrBy = null;
        if(file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(filePath);

            arrBy = new byte[fileInputStream.available()];
            fileInputStream.read(arrBy, 0, fileInputStream.available());
            fileInputStream.close();
        }
        return arrBy;
    }

}
