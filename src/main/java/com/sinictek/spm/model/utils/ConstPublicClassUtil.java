package com.sinictek.spm.model.utils;

import com.sinictek.spm.model.ConstClasses.ConstParam;
import com.sinictek.spm.model.SPcb;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
}
