package com.sinictek.spm.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
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
    private  String create_time;


}
