/**
  * Copyright 2020 bejson.com 
  */
package com.sinictek.spm.model.JsonchartModel;

/**
 * @Author sinictek-pd
 * @Date 2020/9/4 16:56
 * @Version 1.0
 */
public class YAxis {


    private int min;
    private int max;
    private Title title;
    private boolean allowDecimals;
    private int minorGridLineWidth;

    public int getMinorGridLineWidth() {
        return minorGridLineWidth;
    }

    public void setMinorGridLineWidth(int minorGridLineWidth) {
        this.minorGridLineWidth = minorGridLineWidth;
    }

    public void setMin(int min) {
         this.min = min;
     }
     public int getMin() {
         return min;
     }

    public void setMax(int max) {
         this.max = max;
     }
     public int getMax() {
         return max;
     }

    public void setTitle(Title title) {
         this.title = title;
     }
     public Title getTitle() {
         return title;
     }

    public boolean isAllowDecimals() {
        return allowDecimals;
    }

    public void setAllowDecimals(boolean allowDecimals) {
        this.allowDecimals = allowDecimals;
    }
}