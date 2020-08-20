/**
  * Copyright 2020 bejson.com 
  */
package com.sinictek.spm.model.JsonchartModel;
import java.util.List;

/**
 * Auto-generated: 2020-06-13 19:1:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonChartsBean {

    private Chart chart;
    private String title;
    private String tooltip;
    private XAxis xAxis;
    private YAxis yAxis;
    private List<Series> series;
    private PlotOptions plotOptions;
    private Credits credits;


    public void setChart(Chart chart) {
         this.chart = chart;
     }
     public Chart getChart() {
         return chart;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setTooltip(String tooltip) {
         this.tooltip = tooltip;
     }
     public String getTooltip() {
         return tooltip;
     }

    public void setXAxis(XAxis xAxis) {
         this.xAxis = xAxis;
     }
     public XAxis getXAxis() {
         return xAxis;
     }

    public void setYAxis(YAxis yAxis) {
         this.yAxis = yAxis;
     }
     public YAxis getYAxis() {
         return yAxis;
     }

    public void setSeries(List<Series> series) {
         this.series = series;
     }
     public List<Series> getSeries() {
         return series;
     }

    public void setPlotOptions(PlotOptions plotOptions) {
         this.plotOptions = plotOptions;
     }
     public PlotOptions getPlotOptions() {
         return plotOptions;
     }

    public void setCredits(Credits credits) {
         this.credits = credits;
     }
     public Credits getCredits() {
         return credits;
     }

}