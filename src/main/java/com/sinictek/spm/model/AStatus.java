package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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
    /**
     * aoiMode
     */
    private Integer aoiMode;
    private  String create_time;
}
