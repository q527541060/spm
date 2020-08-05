package com.sinictek.spm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * spi-pcb表
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@TableName("s_pcb")
public class SPcb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String pcbIdLine;
    private String lineNo;
    private String jobName;
    private Integer laneNo;
    private String inspectResult;
    private Date inspectStarttime;
    private Date inspectEndtime;
    private Double boardWidth;
    private Double boardLength;
    private String boardBarcode;
    private String arrayBarcode;
    private String arrayWidth;
    private String arrayLength;
    private String arrayinspectResult;
    private Integer totalpadCount;
    private Integer passpadCount;
    private Integer ngpadCount;
    private Integer goodpadCount;
    private Integer shiftyCount;
    private Integer bridgeCount;
    private Integer shapeerrorCount;
    private Integer smearedCount;
    private Integer coplanarityCount;
    private Integer prebridgeCount;
    private Integer padareapercentCount;
    private Integer shiftxCount;
    private Integer otherCount;
    private Integer lowareaCount;
    private Integer overareaCount;
    private Integer lowheightCount;
    private Integer overheightCount;
    private Integer excessCount;
    private Integer insufficientCount;
    private Integer missingCount;
    private Double hCpk;
    private Double aCpk;
    private Double vcpk;
    private Double shithxCpk;
    private Double shithyCpk;
    private String ucl;
    private String lcl;
    private String remark;

    private String padTableName;
    private String componentTableName;

    @TableField(exist = false)
    private String total;
    @TableField(exist = false)
    private String ngPcbYeild;
    @TableField(exist = false)
    private String goodPcbYeild;
    @TableField(exist = false)
    private String passPcbYeild;
    @TableField(exist = false)
    private String ngPcbCount;
    @TableField(exist = false)
    private String goodPcbCount;
    @TableField(exist = false)
    private String passPcbCount;

    public String getNgPcbCount() {
        return ngPcbCount;
    }

    public void setNgPcbCount(String ngPcbCount) {
        this.ngPcbCount = ngPcbCount;
    }

    public String getGoodPcbCount() {
        return goodPcbCount;
    }

    public void setGoodPcbCount(String goodPcbCount) {
        this.goodPcbCount = goodPcbCount;
    }

    public String getPassPcbCount() {
        return passPcbCount;
    }

    public void setPassPcbCount(String passPcbCount) {
        this.passPcbCount = passPcbCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPcbIdLine() {
        return pcbIdLine;
    }

    public void setPcbIdLine(String pcbIdLine) {
        this.pcbIdLine = pcbIdLine;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getLaneNo() {
        return laneNo;
    }

    public void setLaneNo(Integer laneNo) {
        this.laneNo = laneNo;
    }

    public String getInspectResult() {
        return inspectResult;
    }

    public void setInspectResult(String inspectResult) {
        this.inspectResult = inspectResult;
    }

    public Date getInspectStarttime() {
        return inspectStarttime;
    }

    public void setInspectStarttime(Date inspectStarttime) {
        this.inspectStarttime = inspectStarttime;
    }

    public Date getInspectEndtime() {
        return inspectEndtime;
    }

    public void setInspectEndtime(Date inspectEndtime) {
        this.inspectEndtime = inspectEndtime;
    }

    public Double getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(Double boardWidth) {
        this.boardWidth = boardWidth;
    }

    public Double getBoardLength() {
        return boardLength;
    }

    public void setBoardLength(Double boardLength) {
        this.boardLength = boardLength;
    }

    public String getBoardBarcode() {
        return boardBarcode;
    }

    public void setBoardBarcode(String boardBarcode) {
        this.boardBarcode = boardBarcode;
    }

    public String getArrayBarcode() {
        return arrayBarcode;
    }

    public void setArrayBarcode(String arrayBarcode) {
        this.arrayBarcode = arrayBarcode;
    }

    public String getArrayWidth() {
        return arrayWidth;
    }

    public void setArrayWidth(String arrayWidth) {
        this.arrayWidth = arrayWidth;
    }

    public String getArrayLength() {
        return arrayLength;
    }

    public void setArrayLength(String arrayLength) {
        this.arrayLength = arrayLength;
    }

    public String getArrayinspectResult() {
        return arrayinspectResult;
    }

    public void setArrayinspectResult(String arrayinspectResult) {
        this.arrayinspectResult = arrayinspectResult;
    }

    public Integer getTotalpadCount() {
        return totalpadCount;
    }

    public void setTotalpadCount(Integer totalpadCount) {
        this.totalpadCount = totalpadCount;
    }

    public Integer getPasspadCount() {
        return passpadCount;
    }

    public void setPasspadCount(Integer passpadCount) {
        this.passpadCount = passpadCount;
    }

    public Integer getNgpadCount() {
        return ngpadCount;
    }

    public void setNgpadCount(Integer ngpadCount) {
        this.ngpadCount = ngpadCount;
    }

    public Integer getGoodpadCount() {
        return goodpadCount;
    }

    public void setGoodpadCount(Integer goodpadCount) {
        this.goodpadCount = goodpadCount;
    }

    public Integer getShiftyCount() {
        return shiftyCount;
    }

    public void setShiftyCount(Integer shiftyCount) {
        this.shiftyCount = shiftyCount;
    }

    public Integer getBridgeCount() {
        return bridgeCount;
    }

    public void setBridgeCount(Integer bridgeCount) {
        this.bridgeCount = bridgeCount;
    }

    public Integer getShapeerrorCount() {
        return shapeerrorCount;
    }

    public void setShapeerrorCount(Integer shapeerrorCount) {
        this.shapeerrorCount = shapeerrorCount;
    }

    public Integer getSmearedCount() {
        return smearedCount;
    }

    public void setSmearedCount(Integer smearedCount) {
        this.smearedCount = smearedCount;
    }

    public Integer getCoplanarityCount() {
        return coplanarityCount;
    }

    public void setCoplanarityCount(Integer coplanarityCount) {
        this.coplanarityCount = coplanarityCount;
    }

    public Integer getPrebridgeCount() {
        return prebridgeCount;
    }

    public void setPrebridgeCount(Integer prebridgeCount) {
        this.prebridgeCount = prebridgeCount;
    }

    public Integer getPadareapercentCount() {
        return padareapercentCount;
    }

    public void setPadareapercentCount(Integer padareapercentCount) {
        this.padareapercentCount = padareapercentCount;
    }

    public Integer getShiftxCount() {
        return shiftxCount;
    }

    public void setShiftxCount(Integer shiftxCount) {
        this.shiftxCount = shiftxCount;
    }

    public Integer getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(Integer otherCount) {
        this.otherCount = otherCount;
    }

    public Integer getLowareaCount() {
        return lowareaCount;
    }

    public void setLowareaCount(Integer lowareaCount) {
        this.lowareaCount = lowareaCount;
    }

    public Integer getOverareaCount() {
        return overareaCount;
    }

    public void setOverareaCount(Integer overareaCount) {
        this.overareaCount = overareaCount;
    }

    public Integer getLowheightCount() {
        return lowheightCount;
    }

    public void setLowheightCount(Integer lowheightCount) {
        this.lowheightCount = lowheightCount;
    }

    public Integer getOverheightCount() {
        return overheightCount;
    }

    public void setOverheightCount(Integer overheightCount) {
        this.overheightCount = overheightCount;
    }

    public Integer getExcessCount() {
        return excessCount;
    }

    public void setExcessCount(Integer excessCount) {
        this.excessCount = excessCount;
    }

    public Integer getInsufficientCount() {
        return insufficientCount;
    }

    public void setInsufficientCount(Integer insufficientCount) {
        this.insufficientCount = insufficientCount;
    }

    public Integer getMissingCount() {
        return missingCount;
    }

    public void setMissingCount(Integer missingCount) {
        this.missingCount = missingCount;
    }

    public Double gethCpk() {
        return hCpk;
    }

    public void sethCpk(Double hCpk) {
        this.hCpk = hCpk;
    }

    public Double getaCpk() {
        return aCpk;
    }

    public void setaCpk(Double aCpk) {
        this.aCpk = aCpk;
    }

    public Double getVcpk() {
        return vcpk;
    }

    public void setVcpk(Double vcpk) {
        this.vcpk = vcpk;
    }

    public Double getShithxCpk() {
        return shithxCpk;
    }

    public void setShithxCpk(Double shithxCpk) {
        this.shithxCpk = shithxCpk;
    }

    public Double getShithyCpk() {
        return shithyCpk;
    }

    public void setShithyCpk(Double shithyCpk) {
        this.shithyCpk = shithyCpk;
    }

    public String getUcl() {
        return ucl;
    }

    public void setUcl(String ucl) {
        this.ucl = ucl;
    }

    public String getLcl() {
        return lcl;
    }

    public void setLcl(String lcl) {
        this.lcl = lcl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPadTableName() {
        return padTableName;
    }

    public void setPadTableName(String padTableName) {
        this.padTableName = padTableName;
    }

    public String getComponentTableName() {
        return componentTableName;
    }

    public void setComponentTableName(String componentTableName) {
        this.componentTableName = componentTableName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNgPcbYeild() {
        return ngPcbYeild;
    }

    public void setNgPcbYeild(String ngPcbYeild) {
        this.ngPcbYeild = ngPcbYeild;
    }

    public String getGoodPcbYeild() {
        return goodPcbYeild;
    }

    public void setGoodPcbYeild(String goodPcbYeild) {
        this.goodPcbYeild = goodPcbYeild;
    }

    public String getPassPcbYeild() {
        return passPcbYeild;
    }

    public void setPassPcbYeild(String passPcbYeild) {
        this.passPcbYeild = passPcbYeild;
    }

    @Override
    public String toString() {
        return "SPcb{" +
        ", id=" + id +
        ", pcbIdLine=" + pcbIdLine +
        ", lineNo=" + lineNo +
        ", jobName=" + jobName +
        ", laneNo=" + laneNo +
        ", inspectResult=" + inspectResult +
        ", inspectStarttime=" + inspectStarttime +
        ", inspectEndtime=" + inspectEndtime +
        ", boardWidth=" + boardWidth +
        ", boardLength=" + boardLength +
        ", boardBarcode=" + boardBarcode +
        ", arrayBarcode=" + arrayBarcode +
        ", arrayWidth=" + arrayWidth +
        ", arrayLength=" + arrayLength +
        ", arrayinspectResult=" + arrayinspectResult +
        ", totalpadCount=" + totalpadCount +
        ", passpadCount=" + passpadCount +
        ", ngpadCount=" + ngpadCount +
        ", goodpadCount=" + goodpadCount +
        ", shiftyCount=" + shiftyCount +
        ", bridgeCount=" + bridgeCount +
        ", shapeerrorCount=" + shapeerrorCount +
        ", smearedCount=" + smearedCount +
        ", coplanarityCount=" + coplanarityCount +
        ", prebridgeCount=" + prebridgeCount +
        ", padareapercentCount=" + padareapercentCount +
        ", shiftxCount=" + shiftxCount +
        ", otherCount=" + otherCount +
        ", lowareaCount=" + lowareaCount +
        ", overareaCount=" + overareaCount +
        ", lowheightCount=" + lowheightCount +
        ", overheightCount=" + overheightCount +
        ", excessCount=" + excessCount +
        ", insufficientCount=" + insufficientCount +
        ", missingCount=" + missingCount +
        ", hCpk=" + hCpk +
        ", aCpk=" + aCpk +
        ", vcpk=" + vcpk +
        ", shithxCpk=" + shithxCpk +
        ", shithyCpk=" + shithyCpk +
        ", ucl=" + ucl +
        ", lcl=" + lcl +
        ", remark=" + remark +
                ", padTableName=" + padTableName +
                ", componentTableName=" +componentTableName +
        "}";
    }
}
