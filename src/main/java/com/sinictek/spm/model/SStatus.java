package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * spi-设备状态
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-06-09
 */
@TableName("s_status")
public class SStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机器状态数据
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String lineNo;
    private String barcode;
    private String ipAddress;
    private String equipmentNo;
    private String loft;
    private String factory;
    private Integer lane;
    private String customer;
    private Integer start;
    private Integer run;
    private Integer stop;
    private Integer idle;
    private Integer init;
    private String error;
    private Integer towerR;
    private Integer towerG;
    private Integer towerY;
    private Integer status;
    private String errContent;
    private String remark;
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getEquipmentNo() {
        return equipmentNo;
    }

    public void setEquipmentNo(String equipmentNo) {
        this.equipmentNo = equipmentNo;
    }

    public String getLoft() {
        return loft;
    }

    public void setLoft(String loft) {
        this.loft = loft;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Integer getLane() {
        return lane;
    }

    public void setLane(Integer lane) {
        this.lane = lane;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRun() {
        return run;
    }

    public void setRun(Integer run) {
        this.run = run;
    }

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public Integer getIdle() {
        return idle;
    }

    public void setIdle(Integer idle) {
        this.idle = idle;
    }

    public Integer getInit() {
        return init;
    }

    public void setInit(Integer init) {
        this.init = init;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getTowerR() {
        return towerR;
    }

    public void setTowerR(Integer towerR) {
        this.towerR = towerR;
    }

    public Integer getTowerG() {
        return towerG;
    }

    public void setTowerG(Integer towerG) {
        this.towerG = towerG;
    }

    public Integer getTowerY() {
        return towerY;
    }

    public void setTowerY(Integer towerY) {
        this.towerY = towerY;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrContent() {
        return errContent;
    }

    public void setErrContent(String errContent) {
        this.errContent = errContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SStatus{" +
        ", id=" + id +
        ", lineNo=" + lineNo +
        ", barcode=" + barcode +
        ", ipAddress=" + ipAddress +
        ", equipmentNo=" + equipmentNo +
        ", loft=" + loft +
        ", factory=" + factory +
        ", lane=" + lane +
        ", customer=" + customer +
        ", start=" + start +
        ", run=" + run +
        ", stop=" + stop +
        ", idle=" + idle +
        ", init=" + init +
        ", error=" + error +
        ", towerR=" + towerR +
        ", towerG=" + towerG +
        ", towerY=" + towerY +
        ", status=" + status +
        ", errContent=" + errContent +
        ", remark=" + remark +
                ", dateTime="+ updateTime+
        "}";
    }
}
