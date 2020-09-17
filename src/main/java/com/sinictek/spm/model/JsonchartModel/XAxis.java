/**
  * Copyright 2020 bejson.com 
  */
package com.sinictek.spm.model.JsonchartModel;
import java.util.List;

/**
 * @Author sinictek-pd
 * @Date 2020/9/4 16:56
 * @Version 1.0
 */
public class XAxis {

    private List<String> categories;
    private boolean crosshair;
    private int min;
    private int max;
    private int lineWidth;
    private List<PlotBands> plotBands;

    public List<PlotBands> getPlotBands() {
        return plotBands;
    }

    public void setPlotBands(List<PlotBands> plotBands) {
        this.plotBands = plotBands;
    }

    public boolean isCrosshair() {
        return crosshair;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setCategories(List<String> categories) {
         this.categories = categories;
     }
     public List<String> getCategories() {
         return categories;
     }

    public void setCrosshair(boolean crosshair) {
         this.crosshair = crosshair;
     }
     public boolean getCrosshair() {
         return crosshair;
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

}