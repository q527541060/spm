package com.sinictek.spm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2021-03-25
 */
@Data
@TableName("s_realTimeConfig")
public class SRealtimeconfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String JobName;
    private String LineNo;
    private Integer LaneNo;
    private String PadGroup;
    private String Pad;
    private String RealTimeType;
    private Double StandCPK;
    private Integer PCBSubGroup;
    private String Rule;
    private String GetPCBByTime;
    private String usl;
    private String msl;
    private String lsl;
    private Integer GetPCBTimeInterver;
    private String LastUpdateTime;
    @TableField("create_time")
    private String create_time;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;


}
