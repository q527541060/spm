package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * aoi-设备状态
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-08-05
 */
@TableName("a_status")
public class AStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * aoi机器状态数据ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * aoi机器线体
     */
    private String lineNo;
    /**
     * 当前条码或上一片条码
     */
    private String barcode;
    /**
     * 当前设备局域网IP
     */
    private String ipAddress;
    /**
     * 设备编号
     */
    private String equipmentNo;
    /**
     * 设备楼层
     */
    private String loft;
    /**
     * 设备厂区
     */
    private String factory;
    /**
     * 设备轨道号
     */
    private Integer lane;
    /**
     * 客户名
     */
    private String customer;
    /**
     * 设备开启
     */
    private Integer start;
    /**
     * 设备运行
     */
    private Integer run;
    /**
     * 设备停止
     */
    private Integer stop;
    /**
     * 设备闲置
     */
    private Integer idle;
    /**
     * 设备初始化
     */
    private Integer init;
    /**
     * 设备报警
     */
    private Integer error;
    /**
     * 红灯
     */
    private Integer towerR;
    /**
     * 绿灯
     */
    private Integer towerG;
    /**
     * 黄灯
     */
    private Integer towerY;
    /**
     * 预留
     */
    private Integer status;
    /**
     * 报警详细信息
     */
    private String errContent;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * remark
     */
    private String remark;


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

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AStatus{" +
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
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
