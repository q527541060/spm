package com.sinictek.spm.model;

import java.sql.Blob;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 焊盘
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-23
 */
@TableName("s_pad")
public class SPad implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableField(exist = false)
    //private long id;
    private String padId;
    private String pcbidLine;
    private Long padIndex;
    private String componentId;
    private String arrayId;
    private String padInspectResult;
    private String defectTypeCode;
    private String defectTypeName;
    private String padImagePath;
   // @TableField(el = "pad2dImage,jdbcType=BLOB")
    private byte[] pad2dImage;
    private Blob pad3dImage;
    private Double height;
    private Double area;
    private Double volume;
    private Double offsetx;
    private Double offsety;
    private Double perHeight;
    private Double perArea;
    private Double perVolume;
    private Double perOffsetx;
    private Double perOffsety;
    private Double shape;
    private Integer bridgeType;
    private Double uHeight;
    private Double lHeight;
    private Double uArea;
    private Double lArea;
    private Double uVolume;
    private Double lVolume;
    private Double uOffsetx;
    private Double uOffsety;
    private Double uPerHeight;
    private Double lPerHeight;
    private Double uPerArea;
    private Double lPerArea;
    private Double uPerVolume;
    private Double lPerVolume;
    private Double uPerOffsetx;
    private Double uPerOffsety;
    private Long padTableID;
    private Long componentTableID;
    private String remark;


    public String getPadId() {
        return padId;
    }

    public void setPadId(String padId) {
        this.padId = padId;
    }

    public String getPcbidLine() {
        return pcbidLine;
    }

    public void setPcbidLine(String pcbidLine) {
        this.pcbidLine = pcbidLine;
    }

    public Long getPadIndex() {
        return padIndex;
    }

    public void setPadIndex(Long padIndex) {
        this.padIndex = padIndex;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getArrayId() {
        return arrayId;
    }

    public void setArrayId(String arrayId) {
        this.arrayId = arrayId;
    }

    public String getPadInspectResult() {
        return padInspectResult;
    }

    public void setPadInspectResult(String padInspectResult) {
        this.padInspectResult = padInspectResult;
    }

    public String getDefectTypeCode() {
        return defectTypeCode;
    }

    public void setDefectTypeCode(String defectTypeCode) {
        this.defectTypeCode = defectTypeCode;
    }

    public String getDefectTypeName() {
        return defectTypeName;
    }

    public void setDefectTypeName(String defectTypeName) {
        this.defectTypeName = defectTypeName;
    }

    public String getPadImagePath() {
        return padImagePath;
    }

    public void setPadImagePath(String padImagePath) {
        this.padImagePath = padImagePath;
    }

    public byte[] getPad2dImage() {
        return pad2dImage;
    }

    public void setPad2dImage(byte[] pad2dImage) {
        this.pad2dImage = pad2dImage;
    }

    public Blob getPad3dImage() {
        return pad3dImage;
    }

    public void setPad3dImage(Blob pad3dImage) {
        this.pad3dImage = pad3dImage;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getOffsetx() {
        return offsetx;
    }

    public void setOffsetx(Double offsetx) {
        this.offsetx = offsetx;
    }

    public Double getOffsety() {
        return offsety;
    }

    public void setOffsety(Double offsety) {
        this.offsety = offsety;
    }

    public Double getPerHeight() {
        return perHeight;
    }

    public void setPerHeight(Double perHeight) {
        this.perHeight = perHeight;
    }

    public Double getPerArea() {
        return perArea;
    }

    public void setPerArea(Double perArea) {
        this.perArea = perArea;
    }

    public Double getPerVolume() {
        return perVolume;
    }

    public void setPerVolume(Double perVolume) {
        this.perVolume = perVolume;
    }

    public Double getPerOffsetx() {
        return perOffsetx;
    }

    public void setPerOffsetx(Double perOffsetx) {
        this.perOffsetx = perOffsetx;
    }

    public Double getPerOffsety() {
        return perOffsety;
    }

    public void setPerOffsety(Double perOffsety) {
        this.perOffsety = perOffsety;
    }

    public Double getShape() {
        return shape;
    }

    public void setShape(Double shape) {
        this.shape = shape;
    }

    public Integer getBridgeType() {
        return bridgeType;
    }

    public void setBridgeType(Integer bridgeType) {
        this.bridgeType = bridgeType;
    }

    public Double getuHeight() {
        return uHeight;
    }

    public void setuHeight(Double uHeight) {
        this.uHeight = uHeight;
    }

    public Double getlHeight() {
        return lHeight;
    }

    public void setlHeight(Double lHeight) {
        this.lHeight = lHeight;
    }

    public Double getuArea() {
        return uArea;
    }

    public void setuArea(Double uArea) {
        this.uArea = uArea;
    }

    public Double getlArea() {
        return lArea;
    }

    public void setlArea(Double lArea) {
        this.lArea = lArea;
    }

    public Double getuVolume() {
        return uVolume;
    }

    public void setuVolume(Double uVolume) {
        this.uVolume = uVolume;
    }

    public Double getlVolume() {
        return lVolume;
    }

    public void setlVolume(Double lVolume) {
        this.lVolume = lVolume;
    }

    public Double getuOffsetx() {
        return uOffsetx;
    }

    public void setuOffsetx(Double uOffsetx) {
        this.uOffsetx = uOffsetx;
    }

    public Double getuOffsety() {
        return uOffsety;
    }

    public void setuOffsety(Double uOffsety) {
        this.uOffsety = uOffsety;
    }

    public Double getuPerHeight() {
        return uPerHeight;
    }

    public void setuPerHeight(Double uPerHeight) {
        this.uPerHeight = uPerHeight;
    }

    public Double getlPerHeight() {
        return lPerHeight;
    }

    public void setlPerHeight(Double lPerHeight) {
        this.lPerHeight = lPerHeight;
    }

    public Double getuPerArea() {
        return uPerArea;
    }

    public void setuPerArea(Double uPerArea) {
        this.uPerArea = uPerArea;
    }

    public Double getlPerArea() {
        return lPerArea;
    }

    public void setlPerArea(Double lPerArea) {
        this.lPerArea = lPerArea;
    }

    public Double getuPerVolume() {
        return uPerVolume;
    }

    public void setuPerVolume(Double uPerVolume) {
        this.uPerVolume = uPerVolume;
    }

    public Double getlPerVolume() {
        return lPerVolume;
    }

    public void setlPerVolume(Double lPerVolume) {
        this.lPerVolume = lPerVolume;
    }

    public Double getuPerOffsetx() {
        return uPerOffsetx;
    }

    public void setuPerOffsetx(Double uPerOffsetx) {
        this.uPerOffsetx = uPerOffsetx;
    }

    public Double getuPerOffsety() {
        return uPerOffsety;
    }

    public void setuPerOffsety(Double uPerOffsety) {
        this.uPerOffsety = uPerOffsety;
    }

    public Long getPadTableID() {
        return padTableID;
    }

    public void setPadTableID(Long padTableID) {
        this.padTableID = padTableID;
    }

    public Long getComponentTableID() {
        return componentTableID;
    }

    public void setComponentTableID(Long componentTableID) {
        this.componentTableID = componentTableID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SPad{" +
        ", padId=" + padId +
        ", pcbidLine=" + pcbidLine +
        ", padIndex=" + padIndex +
        ", componentId=" + componentId +
        ", arrayId=" + arrayId +
        ", padInspectResult=" + padInspectResult +
        ", defectTypeCode=" + defectTypeCode +
        ", defectTypeName=" + defectTypeName +
        ", padImagePath=" + padImagePath +
        ", pad2dImage=" + pad2dImage +
        ", pad3dImage=" + pad3dImage +
        ", height=" + height +
        ", area=" + area +
        ", volume=" + volume +
        ", offsetx=" + offsetx +
        ", offsety=" + offsety +
        ", perHeight=" + perHeight +
        ", perArea=" + perArea +
        ", perVolume=" + perVolume +
        ", perOffsetx=" + perOffsetx +
        ", perOffsety=" + perOffsety +
        ", shape=" + shape +
        ", bridgeType=" + bridgeType +
        ", uHeight=" + uHeight +
        ", lHeight=" + lHeight +
        ", uArea=" + uArea +
        ", lArea=" + lArea +
        ", uVolume=" + uVolume +
        ", lVolume=" + lVolume +
        ", uOffsetx=" + uOffsetx +
        ", uOffsety=" + uOffsety +
        ", uPerHeight=" + uPerHeight +
        ", lPerHeight=" + lPerHeight +
        ", uPerArea=" + uPerArea +
        ", lPerArea=" + lPerArea +
        ", uPerVolume=" + uPerVolume +
        ", lPerVolume=" + lPerVolume +
        ", uPerOffsetx=" + uPerOffsetx +
        ", uPerOffsety=" + uPerOffsety +
        ", padTableID=" + padTableID +
        ", componentTableID=" + componentTableID +
        ", remark=" + remark +
        "}";
    }
}
