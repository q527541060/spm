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
public class Series {

    private String type;
    private String name;
    private List<Double> data;
    private String stack;
    private String stacking;
    private String color;
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setData(List<Double> data) {
         this.data = data;
     }
     public List<Double> getData() {
         return data;
     }

    public String getStack() {
        return stack;
    }
    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getStacking() {
        return stacking;
    }

    public void setStacking(String stacking) {
        this.stacking = stacking;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}