package com.sinictek.spm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2021-03-25
 */
@Data
@TableName("s_realTimeAutoRefresh")
public class SRealtimeautorefresh implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String LineNo;
    private String PCBIDs;
    private Double x1;
    private Double x2;
    private Double x3;
    private Double x4;
    private Double x5;
    private Double Average;
    private Double alpha;
    private Double r;
    private Double Ca;
    private Double Cp;
    private Double Cpk;
    private String PCBBarcodes;
    private Double xucl;
    private Double xcl;
    private Double xlcl;
    private Double usl;
    private Double lsl;
    private Double rucl;
    private Double rcl;
    private Double rlcl;
    @TableField("create_time")
    private Double createTime;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;


}
