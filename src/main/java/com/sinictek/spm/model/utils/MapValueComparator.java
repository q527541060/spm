package com.sinictek.spm.model.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * @Author sinictek-pd
 * @Date 2020/10/20 17:43
 * @Version 1.0
 */
public class MapValueComparator implements Comparator<Map.Entry<Integer, Integer>> {

    @Override
    public int compare(Map.Entry<Integer, Integer> me1, Map.Entry<Integer, Integer> me2) {
        return me1.getValue().compareTo(me2.getValue());
    }
}
