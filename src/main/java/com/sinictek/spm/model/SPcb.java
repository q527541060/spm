package com.sinictek.spm.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@Data
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
    private  String create_time;

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

    @TableField(exist = false)
    private String ngpadYeild;
    @TableField(exist = false)
    private String goodpadYeild;
    @TableField(exist = false)
    private String passpadYeild;
    //ngarrayCount
    @TableField(exist = false)
    private String ngarrayCount;
    @TableField(exist = false)
    private String passarrayCount;
    @TableField(exist = false)
    private String goodarrayCount;


}
