/**
  * Copyright 2020 bejson.com 
  */
package com.sinictek.spm.model.JsonchartModel;

/**
 * @Author sinictek-pd
 * @Date 2020/9/4 16:56
 * @Version 1.0
 */
public class PlotOptions {

    private Column column;
    private Series series;
    public void setColumn(Column column) {
         this.column = column;
     }
     public Column getColumn() {
         return column;
     }

    public Series getSeries() {
        return series;
    }


    public void setSeries(Series series) {
        this.series = series;
    }
}