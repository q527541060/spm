package com.sinictek.spm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-23
 */
@TableName("s_component")
public class SComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableField(exist = false)
    //private  long id;
    private String pcbIdLine;
    private String componentName;
    private String arrayId;
    private String componentInspectResult;
    private String remark;


    public String getPcbIdLine() {
        return pcbIdLine;
    }

    public void setPcbIdLine(String pcbIdLine) {
        this.pcbIdLine = pcbIdLine;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getArrayId() {
        return arrayId;
    }

    public void setArrayId(String arrayId) {
        this.arrayId = arrayId;
    }

    public String getComponentInspectResult() {
        return componentInspectResult;
    }

    public void setComponentInspectResult(String componentInspectResult) {
        this.componentInspectResult = componentInspectResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SComponent{" +
        ", pcbIdLine=" + pcbIdLine +
        ", componentName=" + componentName +
        ", arrayId=" + arrayId +
        ", componentInspectResult=" + componentInspectResult +
        ", remark=" + remark +
        "}";
    }
}
