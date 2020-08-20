package com.sinictek.spm.model.JsonchartModel;

/**
 * @Author sinictek-pd
 * @Date 2020/8/19 20:16
 * @Version 1.0
 */
public class Marker {
    private int radius;

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
