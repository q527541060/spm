/**
  * Copyright 2020 bejson.com 
  */
package com.sinictek.spm.model.JsonchartModel;

/**
 * @Author sinictek-pd
 * @Date 2020/9/4 16:56
 * @Version 1.0
 */
public class Column {

    private double pointPadding;
    private int borderWidth;
    private DataLabels dataLabels;
    private String stacking;


    public DataLabels getDataLabels() {
        return dataLabels;
    }

    public void setDataLabels(DataLabels dataLabels) {
        this.dataLabels = dataLabels;
    }

    public void setPointPadding(double pointPadding) {
         this.pointPadding = pointPadding;
     }
     public double getPointPadding() {
         return pointPadding;
     }

    public void setBorderWidth(int borderWidth) {
         this.borderWidth = borderWidth;
     }
     public int getBorderWidth() {
         return borderWidth;
     }

    public String getStacking() {
        return stacking;
    }

    public void setStacking(String stacking) {
        this.stacking = stacking;
    }
}